package com.company.myChess.figure;

import com.company.myChess.Constants;
import com.company.myChess.FigureType;
import com.company.myChess.Move;

/**
 * Created by Диана on 13.01.2016.
 */
public class Queen extends Figure implements Move {

    public Queen(int x, int y, FigureType figureType) {
        super(x, y, figureType);
    }

    @Override
    public void move(int x, int y) throws Exception {
       this.x = x;
       this.y = y;
    }
}
