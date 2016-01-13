package com.company.myChess.figure;

import com.company.myChess.Constants;
import com.company.myChess.FigureType;
import com.company.myChess.Move;

public class King extends Figure implements Move {
    public King(int x, int y, FigureType figureType) {
        super(x, y, figureType);
    }

    @Override
    public void move(int x, int y) throws Exception {
        if (Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1){
            this.x = x;
            this.y = y;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }
}