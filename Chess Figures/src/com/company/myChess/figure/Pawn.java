package com.company.myChess.figure;

import com.company.myChess.Constants;
import com.company.myChess.FigureType;
import com.company.myChess.Move;

/**
 * Created by ����� on 12.01.2016.
 */
final public class Pawn extends Figure implements Move {

    public Pawn(int x, int y, FigureType type) {

        super(x, y, type);
    }

    //TODO: �������� ����������� ����� �� ����������� ������ ������ �� ���� ��� ��� ������
    @Override
    public void move(int x, int y) throws Exception {
        if (this.figureType == FigureType.BLACK) {//����� ��������� ������ ����
            if (this.y - y > 0) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;//��� ������ �� ���� ��������� ����, ���������, ��� ������� ��� ��������
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        } else {//����� ��������� ������ �����
            if (this.y - y < 0) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        }
    }
}