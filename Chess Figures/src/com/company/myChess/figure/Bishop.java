package com.company.myChess.figure;

import com.company.myChess.Constants;
import com.company.myChess.FigureType;
import com.company.myChess.Move;

/**
 * Created by Диана on 12.01.2016.
 */
public class Bishop extends Figure implements Move {

    public Bishop(int x, int y, FigureType type) {
        super(x, y, type);//вызов родительского конструктора
    }

    public void move(int x, int y) throws Exception {
        if (Math.abs(this.x - x) == Math.abs(this.y - y)) {
            this.x = x;
            this.y = y;
        } else {
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }
    }
}
