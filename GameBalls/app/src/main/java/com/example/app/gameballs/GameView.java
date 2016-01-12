package com.example.app.gameballs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    private int viewWidth;//ширина экрана
    private int viewHeight;//высота экрана
    private int points = 0;//очки игрока
    private float newX = 0;//координата нажатия
    private float newY = 0;//координата нажатия
    private Paint p;//кисть
    private final int timerInterval = 1000;//Интервал, через который будет появляться новый круг в милисекундах. 1s=1000ms
    private boolean hit = false;//флаг, равен true если игрок попал по кругу
    private boolean[] hht = new boolean[3];
    private float radius = 100;//радиус круга
    private float[] newCX = new float[3];//координата круга
    private float[] newCY = new float[3];//координата круга
    private Bitmap[] balls = new Bitmap[3];//мячики

    //    инициализируем кисть и таймер в констуркторе.
    public GameView(Context context) {
        super(context);
        for (int i = 0; i < 3; i++) {
            balls[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
            balls[i] = getCircle(balls[i], (int) radius);
        }
        p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        new Timer().start();//сразу запускаем таймер
    }

    //используем этот переопределенный метод чтобы получить размеры экрана, вместо того чтобы получать его
    //каждый раз при отрисовке. Небольшое, но улушчение быстродействия
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    //метод который происходит, когда система решит, что пора перерисовать экран. Срабатывает, например, при изменении экрана или при вызове метода invalidate()
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint(canvas);
    }

    //метод который срабатывает при нажатии пользователем по экрану. Нажатие может быть разным и мы можем обработать различные типы прикосновения
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_UP) {//нас интересует момент отпускания пальцем экран. Решил поменять с нажатия, потому что метод отрабатывал два раза
            newX = event.getX();
            newY = event.getY();
        }
        for (int j = 0; j < 3; j++) {
            hht[j] = Math.sqrt(Math.pow(newCX[j] - newX, 2) + Math.pow(newCY[j] - newY, 2)) <= radius;
            if (hht[j]) {//проверяем попал ли палец в круг. Дополнительно проверяем флаг, чтобы игрок не читерил иначе за время пока не пояится новый круг можно нафармить себе очков
                if (!hit) {
                    points += 10;//увеличиваем очки
                } else {
                    if (points != 0) {
                        points -= 10;//уменьшаем очки
                    } else {
                        points = 0;//не уходим в минус
                    }
                }
            }
            hit = true;//ставим флаг в true
            invalidate();//принуждаем систему перерисовать экран
        }
        return true;//возвращаем true, чтобы дальнейшая иерархия классов не пыталась обработать нажатие
    }

    //приватный метод, который все и отрисовывает
    private void paint(Canvas canvas) {
        canvas.drawARGB(250, 127, 199, 255); // заливаем цветом
        for (int j = 0; j < 3; j++) {
            if (newCX[j] < radius) {//если координаты круга меньше поля, изменяем координаты
                newCX[j] = radius;
            }
            if (newCY[j] < radius) {//если координаты круга меньше поля, изменяем координаты
                newCY[j] = radius;
            }
            if (newCX[j] > viewWidth) {//если координаты круга больше поля, изменяем координаты
                newCX[j] = viewWidth - radius;
            }
            if (newCY[j] > viewHeight) {//если координаты круга больше поля, изменяем координаты
                newCY[j] = viewHeight - radius;
            }
            if (hit) {//отрисовываем новый круг только если не было попадания
                canvas.drawBitmap(balls[j], newCX[j], newCY[j], p);
            }
            canvas.drawText(points + "", viewWidth - 100, 70, p);//отрисовываем очки
            Log.i("GAME", "painting...");
        }
    }


    //вызываем этот метод по таймеру, чтобы сгенерировать новые координаты круга
    private void update() {
        hit = false;
        for (int j = 0; j < 3; j++) {
            newCX[j] = (float) (Math.random()) * (viewWidth - radius) + radius;
            newCY[j] = (float) (Math.random()) * (viewHeight - radius) + radius;
            Log.i("GAME", "tick: x:" + newCX + "; y:" + newCY);
        }
        invalidate();
    }
/*
    // проверяем попала ли точка в круг. Да, АЛГЕБРА и геометрия зачастую нужны и в реальной жизни
    private boolean[] isInCircle() {
        boolean[] result = new Boolean [3]
        for (int j = 0; j <= 3; j++) {

        }
    }*/


    //рисование круглого изображения вместо круга
    public static Bitmap getCircle(Bitmap source, int radius) {
        int diam = radius << 1;
        Bitmap scaledBitmap = scaleTo(source, diam);

        final Path path = new Path();
        path.addCircle(radius, radius, radius, Path.Direction.CCW);

        Bitmap targetBitmap = Bitmap.createBitmap(diam, diam, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);
        canvas.clipPath(path);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(scaledBitmap, 0, 0, paint);
        return targetBitmap;
    }

    public static Bitmap scaleTo(Bitmap source, int size) {
        int destWidth = source.getWidth();

        int destHeight = source.getHeight();

        destHeight = destHeight * size / destWidth;
        destWidth = size;

        if (destHeight < size) {
            destWidth = destWidth * size / destHeight;
            destHeight = size;
        }

        Bitmap destBitmap = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(destBitmap);
        canvas.drawBitmap(source, new Rect(0, 0, source.getWidth(), source.getHeight()), new Rect(0, 0, destWidth, destHeight), new Paint(Paint.ANTI_ALIAS_FLAG));
        return destBitmap;
    }

    //вложенный класс, такое бывает. Так делают, когда класс нужен только внутри одного другого класса. Ему доступны все приватные методы и поля нашего основного класса GameView
    public class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);//передаем максимальное значение до которого он будет работать и интервал с которым будет срабатывать 1 тик
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update();//при срабатывании тика вызываем обновление нашего круга
        }

        @Override
        public void onFinish() {
        }
    }
}