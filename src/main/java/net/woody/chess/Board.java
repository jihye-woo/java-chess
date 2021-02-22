package net.woody.chess;

import static net.woody.utils.StringUtils.appendNewLine;

import net.woody.pieces.Color;
import net.woody.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int BOARD_LENGTH = 8;

    private final List<Rank> board = new ArrayList<>(BOARD_LENGTH);
    private int size = 0;

    public Board() {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            board.add(new Rank());
        }
    }

    public void add(Piece piece) {
        int rank = getPawnRank(piece);
        getRank(rank).add(piece);
        size++;
    }

    private int getPawnRank(Piece newPiece) {
        return (newPiece.getColor() == Color.WHITE) ? Piece.WHITE_PAWN_RANK : Piece.BLACK_PAWN_RANK;
    }

    public Rank getRank(int rank) {
        if (rank < 0 || board.size() <= rank) {
            throw new ArrayIndexOutOfBoundsException("Rank number " + rank + " is out of range!");
        }
        return board.get(rank);
    }

    public Piece findPawn(int rank, int file) {
        return getRank(rank).find(file);
    }

    public void initialize() {
        Rank blackPawns = getRank(Piece.BLACK_PAWN_RANK);
        Rank whitePawns = getRank(Piece.WHITE_PAWN_RANK);

        for (int i = 0; i < BOARD_LENGTH; i++) {
            blackPawns.add(new Piece(Color.BLACK.toString()));
            whitePawns.add(new Piece());
            size += 2;
        }
    }

    public String getWhitePawnsResult() {
        return getRank(Piece.WHITE_PAWN_RANK).toString();
    }

    public String getBlackPawnsResult() {
        return getRank(Piece.BLACK_PAWN_RANK).toString();
    }

    public int size() {
        return size;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : board) {
            sb.append(appendNewLine(rank.toString()));
        }
        return sb.toString();
    }
}
