package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

public class RandomPlayer implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {
        int moves = l.possibleMoves().size();
        int rand = (int) (Math.random() * moves + 1);
        return l.possibleMoves().get(rand);
    }
}
