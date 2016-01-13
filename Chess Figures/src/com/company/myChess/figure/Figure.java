package com.company.myChess.figure;

import com.company.myChess.FigureType;
import com.company.myChess.Move;

public abstract class Figure implements Move {
    protected int x;//������� ��������� ������. Protected - ������ ���������� ����� ���������� �������� ��� ��������
    protected int y;//������� ��������� ������
    protected FigureType figureType;//������ ��� �����
    protected boolean isInitPosition = true;//����, ��������� �� ������� � ������ ��� �� ��� ��������. ����� ������ ��� ��� ���������
    protected boolean isKilled = false;//����, ������ � true ����� ���� ��� ������ ������

    //�����������, ������� ������� ��������� ��� ����������. ��� ��������� ������� � ����� ��������� ������ �� ����� ������
    public Figure(int x, int y, FigureType figureType) {
        this.x = x;
        this.y = y;
        this.figureType = figureType;
    }

    //��������� ������ �������, ����� ������ ���� �������� ������� ������ � ������� ���� �� ����� ������������ ��� � ����� ������ move
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public boolean isInitPosition() {
        return isInitPosition;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setIsKilled(boolean isKilled) {
        this.isKilled = isKilled;
    }
}