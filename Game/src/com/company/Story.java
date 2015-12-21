package com.company;

/**
 * Created by ����� on 21.12.2015.
 */
public class Story {

    private Situation start_story;
    public Situation current_situation;
    Story() {
        start_story = new Situation("������ ������ (Windows)","������ �� ������ �������� � ���-�� �����! �� ����� ������� � �������� ��� " + "������ �� �� �������. ��� � �������� ���������� ����� 100 ������� ������ HOME.\n"
                + "(1)� ������� ����� �����, � � ���� ��� - �� �������� ��� ���� �� 120 ������� ULTIMATE �� 50��\n"
                + "(2)���� ������ �������, ��� ��� ������� - �� �������� ��� ���� �� 100 ������� PRO �� 10��\n"
                + "(3)��� ���� ��� � ������� - �� �������� ��� ���� �� 100 ������� HOME �� 5�� ",3, 0, 0, 0);
        start_story.direction[0]=new Situation("����������", "��������� ������, �� ����, ������� � ������� ����������! "
                + "������������ � ���������, ����� ��� ������� ��������, ���� ������", 0, 0, -10, -10);
        start_story.direction[1]=new Situation("���������, ���� �������", "������� ����� ���������, ���� ���������� �������,"
                + "���� �������� ��� \n ���� ������� ����������� �������.", 0, 1, 100, 0);
        start_story.direction[2]=new Situation("��� ������ �������� ������", "��� ������ ������ ������� ��������� � ��������� "
                + "���� ������. ������ ��� ������ ����� \n�������� �������� � ������� ��� ����� ��������� ��� ����� ������� ������"
                + " � �� ����� ����� �� ��� ������� ������ �!", 0, 0, 50, 1);
        current_situation=start_story;
    }
    public void go(int num) {
        if(num<=current_situation.direction.length)
            current_situation=current_situation.direction[num-1];
        else System.out.println("�� ������ �������� �� "+current_situation.direction.length+" ���������");
    }
    public boolean isEnd(){
        return current_situation.direction.length==0;
    }
}
