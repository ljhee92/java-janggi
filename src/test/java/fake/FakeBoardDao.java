package fake;

import dao.BoardDao;
import domain.board.Point;
import domain.piece.Piece;
import domain.piece.Team;
import domain.piece.Wang;
import dto.BoardDto;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class FakeBoardDao implements BoardDao {

    private final Map<Integer, BoardDto> board = new HashMap<>(
            Map.of(1, new BoardDto(
                    new HashMap<>(Map.of(
                            Point.of(2, 5), new Wang(Team.HAN),
                            Point.of(9, 5), new Wang(Team.CHO)
                    )),
                    Team.CHO
            ))
    );

    @Override
    public boolean hasRecords() {
        return !board.isEmpty();
    }

    @Override
    public BoardDto load(final int boardId) {
        return board.get(boardId);
    }

    @Override
    public void save(final Connection connection, final int boardId, final Point point, final Piece piece, final Team turn) {
        board.put(boardId, new BoardDto(Map.of(point, piece), turn));
    }

    @Override
    public void remove(final int boardId) {
        board.remove(boardId);
    }

    @Override
    public void removeAll(final Connection connection) {
        board.clear();
    }
}
