package com.company.myChess.figure;

import com.company.myChess.Constants;
import com.company.myChess.FigureType;
import com.company.myChess.Move;

/**
 * Created by Диана on 13.01.2016.
 */
public class Knight extends Figure implements Move {
    public Knight(int x, int y, FigureType figureType) {
        super(x, y, figureType);
    }

    @Override
    public void move(int x, int y) throws Exception {
        if (((this.y - y == 2 || this.y - y == -2) && (this.x - x == 1 || this.x - x == -1)) || ((this.x - x == 2 || this.x - x == -2) && (this.x - x == 1 || this.x - x == -1) )) {
            this.x = x;
            this.y = y;
        } else {
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }
    }
}
