package com.progmatic.labyrinthproject.exceptions;

import com.progmatic.labyrinthproject.Coordinate;

public class InvalidMoveException extends Exception {

    private final int row;
    private final int column;
    private final String message;

    public InvalidMoveException(int row, int col, String message) {
        this.row = row;
        this.column = col;
        this.message = message;
    }

    public InvalidMoveException(Coordinate c, String message) {
        this.row = c.getRow();
        this.column = c.getCol();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Problem with the coordinates you want to move to: (" + row + ", " + column + "). The problem is: " + message;
    }
}
