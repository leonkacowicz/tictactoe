package io.github.leonkacowicz.tictactoe.core;

public class Move {

    public final int row;
    public final int column;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        return row == move.row && column == move.column;
    }

    @Override
    public int hashCode() {
        return 31 * row + column;
    }
}
