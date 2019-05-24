package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private Coordinate playerPosition;
    private final CellType[][] labirinth;
    private int row;
    private int col;

    public LabyrinthImpl(int row, int col, Coordinate playerPosition) {
        this.labirinth = new CellType[col][row];
        this.row = row;
        this.col = col;
        this.playerPosition = null;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());
            LabyrinthImpl lab = new LabyrinthImpl(height, width, playerPosition);
            Coordinate c;

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    c = new Coordinate(ww, hh);
                    switch (line.charAt(ww)) {
                        case 'W':
                            lab.setCellType(c, CellType.WALL);
                            break;
                        case 'E':
                            lab.setCellType(c, CellType.END);
                            break;
                        case 'S':
                            lab.setCellType(c, CellType.START);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        } catch (CellException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int getWidth() {

        if (col > 0) {
            return this.col;
        }
        return -1;
    }

    @Override
    public int getHeight() {

        if (row > 0) {
            return this.row;
        }
        return -1;
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {

        if ((c.getCol() < 0 || c.getCol() > labirinth.length) || (c.getRow() < 0 || c.getRow() > labirinth[0].length)) {
            throw new CellException(c, "This coordinate does not exist!");
        }
        if (this.labirinth[c.getCol()][c.getRow()].equals(CellType.EMPTY)) {
            return CellType.EMPTY;
        }
        if (this.labirinth[c.getCol()][c.getRow()].equals(CellType.END)) {
            return CellType.END;
        }
        if (this.labirinth[c.getCol()][c.getRow()].equals(CellType.START)) {
            return CellType.START;
        }
        if (this.labirinth[c.getCol()][c.getRow()].equals(CellType.WALL)) {
            return CellType.WALL;
        }
        return null;
    }

    @Override
    public void setSize(int width, int height) {
        this.col = width;
        this.row = height;
    }   

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {

        if ((c.getCol() < 0 || c.getCol() > labirinth.length) || (c.getRow() < 0 || c.getRow() > labirinth[0].length)) {
            throw new CellException(c, "This coordinate does not exist!");
        }
        this.labirinth[c.getCol()][c.getRow()] = type;

        if (type.equals(CellType.START)) {
            playerPosition = c;
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        return this.playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {

        try {
            if (getCellType(playerPosition).equals(CellType.END)) {
                return true;
            }
        } catch (CellException ex) {
            ex.getMessage();
        }
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> moves = new ArrayList<>();

        if ((playerPosition.getRow() > 0) && (this.labirinth[playerPosition.getCol()][playerPosition.getRow() - 1] == CellType.EMPTY || this.labirinth[playerPosition.getCol()][playerPosition.getRow() - 1] == CellType.END)) {
            moves.add(Direction.NORTH);
        }
        if ((playerPosition.getRow() < labirinth[0].length - 1) && (this.labirinth[playerPosition.getCol()][playerPosition.getRow() + 1] == CellType.EMPTY || this.labirinth[playerPosition.getCol()][playerPosition.getRow() + 1] == CellType.END)) {
            moves.add(Direction.SOUTH);
        }
        if ((playerPosition.getCol() > 0) && (this.labirinth[playerPosition.getCol() - 1][playerPosition.getRow()] == CellType.EMPTY || this.labirinth[playerPosition.getCol() - 1][playerPosition.getRow()] == CellType.END)) {
            moves.add(Direction.WEST);
        }
        if ((playerPosition.getCol() < this.labirinth.length - 1) && (this.labirinth[playerPosition.getCol() + 1][playerPosition.getRow()] == CellType.EMPTY || this.labirinth[playerPosition.getCol() + 1][playerPosition.getRow()] == CellType.END)) {
            moves.add(Direction.WEST);
        }
        return moves;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {

        if (direction.equals(Direction.EAST)) {
            playerPosition.getCol();
        }
    }
}
