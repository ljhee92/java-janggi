package dto;

import domain.board.Point;
import domain.piece.Piece;
import domain.piece.Team;

import java.util.Map;

public record BoardDto(Map<Point, Piece> board, Team team) {

}
