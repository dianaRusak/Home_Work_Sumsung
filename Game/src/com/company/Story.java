package com.company;

/**
 * Created by ƒиана on 21.12.2015.
 */
public class Story {

    private Situation start_story;
    public Situation current_situation;
    Story() {
        start_story = new Situation("перва€ сделка (Windows)","“олько вы начали работать и тут-же удача! ¬ы нашли клиента и продаете ему " + "партию ѕќ ћ— ¬индовс. ≈му в принципе достаточно вз€ть 100 коробок версии HOME.\n"
                + "(1)у клиента денег много, а у мен€ нет - вы выпишете ему счет на 120 коробок ULTIMATE по 50тр\n"
                + "(2)чуть дороже сделаем, кто там заметит - вы выпишете ему счет на 100 коробок PRO по 10тр\n"
                + "(3)как надо так и сделаем - вы выпишете ему счет на 100 коробок HOME по 5тр ",3, 0, 0, 0);
        start_story.direction[0]=new Situation("корпоратив", "Ќеудачное начало, ну чтож, сегодн€ в конторе копроратив! "
                + "ѕознакомлюсь с коллегами, людей так сказать посмотрю, себ€ покажу", 0, 0, -10, -10);
        start_story.direction[1]=new Situation("совещание, босс доволен", "—егодн€ будет совещание, мен€ неожиданно вызвали,"
                + "есть сведени€ что \n босс доволен сегодн€шней сделкой.", 0, 1, 100, 0);
        start_story.direction[2]=new Situation("мой первый ло€льный клиент", "ћой первый клиент доволен скоростью и качеством "
                + "моей работы. —ейчас мне звонил лично \nдиреткор компании и сообщил что скоро состоитс€ еще более крупна€ сделка"
                + " и он хотел чтобы по ней работал именно €!", 0, 0, 50, 1);
        current_situation=start_story;
    }
    public void go(int num) {
        if(num<=current_situation.direction.length)
            current_situation=current_situation.direction[num-1];
        else System.out.println("¬ы можете выбирать из "+current_situation.direction.length+" вариантов");
    }
    public boolean isEnd(){
        return current_situation.direction.length==0;
    }
}
