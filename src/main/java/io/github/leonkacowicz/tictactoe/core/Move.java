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
}
