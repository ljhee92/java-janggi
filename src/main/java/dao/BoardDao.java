package dao;

import domain.board.Point;
import domain.piece.Piece;
import domain.piece.Team;
import dto.BoardDto;

import java.sql.Connection;

public interface BoardDao {

    boolean hasRecords();

    BoardDto load(final int boardId);

    void save(final Connection connection, final int boardId, final Point point, final Piece piece, final Team turn);

    void remove(final int boardId);

    void removeAll(final Connection connection);
}
