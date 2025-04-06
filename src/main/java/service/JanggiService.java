package service;

import dao.BoardDao;
import dao.BoardDaoImpl;
import db.DatabaseConnector;
import domain.board.Point;
import domain.piece.Piece;
import domain.piece.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class JanggiService {

    private final DatabaseConnector databaseConnector;
    private final BoardDao boardDao;

    public JanggiService(final DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
        this.boardDao = new BoardDaoImpl(databaseConnector);
    }

    public boolean hasSavedGame() {
        return boardDao.hasRecords();
    }

    public Map<Point, Piece> findBoard(final int boardId) {
        return boardDao.load(boardId).board();
    }

    public Team findTurn(final int boardId) {
        return boardDao.load(boardId).team();
    }

    public void saveAllData(final Map<Point, Piece> board, final Team turn, final int boardId) {
        try (Connection connection = databaseConnector.getConnection()) {
            connection.setAutoCommit(false);

            removeAllData(boardId);
            for (Point point : board.keySet()) {
                boardDao.save(connection, boardId, point, board.get(point), turn);
            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] DB 작업이 실패했습니다. " + e.getMessage(), e);
        }
    }

    public void removeAllData(final int boardId) {
        boardDao.remove(boardId);
    }
}
