package com.company;

/**
 * Created by Диана on 21.12.2015.
 */
public class Situation {

    public Situation[] direction;
    public String subject;
    public String text;
    public int dK;
    public int dA;
    public int dR;

    public Situation (String subject, String text, int variants, int dk,int da,int dr) {
        this.subject=subject;
        this.text=text;
        dK=dk;
        dA=da;
        dR=dr;
        direction=new Situation[variants];
    }
}
