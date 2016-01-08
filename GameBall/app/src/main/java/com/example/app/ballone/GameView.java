package com.example.app.ballone;


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
    private float newCX = 0;//координата круга
    private float newCY = 0;//координата круга
    private Paint p;//кисть
    private final int timerInterval = 1000;//Интервал, через который будет появляться новый круг в милисекундах. 1s=1000ms
    private boolean hit = false;//флаг, равен true если игрок попал по кругу
    private float radius = 50;//радиус круга
    private Bitmap ball;

    //    инициализируем кисть и таймер в констуркторе.
    public GameView(Context context) {
        super(context);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        ball = getCircle(ball, 50);
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

    //метод который срабатывает при нажатии пользователем по экрану. Нажатие может быть разым и мы можем обработать различные типы прикосновения
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_UP) {//нас интересует момент отпускания пальцем экран. Решил поменять с нажатия, потому что метод отрабатывал два раза
            newX = event.getX();
            newY = event.getY();
        }
        if (!hit && isInCircle()) {//проверяем попал ли палец в круг. Дополнительно проверяем флаг, чтобы игрок не читерил иначе за время пока не пояится новый круг можно нафармить себе очков
            hit = true;//ставим флаг в true
            points += 10;//увеличиваем очки
            invalidate();//принуждаем систему перерисовать экран
        }
        return true;//возвращаем true, чтобы дальнейшая иерархия классов не пыталась обработать нажатие
    }

    //приватный метод, который все и отрисовывает
    private void paint(Canvas canvas) {
        canvas.drawARGB(250, 127, 199, 255); // заливаем цветом
        if (newCX < radius) {//если координаты круга меньше поля, изменяем координаты
            newCX = radius;
        }
        if (newCY < radius) {//если координаты круга меньше поля, изменяем координаты
            newCY = radius;
        }
        if (newCX  > viewWidth - radius*2) {//если координаты круга больше поля, изменяем координаты
            newCX  = viewWidth - radius*2;
        }
        if (newCY  > viewHeight - radius*2) {//если координаты круга больше поля, изменяем координаты
            newCY = viewHeight - radius*2;
        }
        if (!hit) {//отрисовываем новый круг только если не было попадания
            canvas.drawBitmap(ball, newCX, newCY, p);
        }
        canvas.drawText(points + "", viewWidth - 100, 70, p);//отрисовываем очки
        Log.i("GAME", "painting...");
    }
    //вызываем этот метод по таймеру, чтобы сгенерировать новые координаты круга
    private void update() {
        hit = false;
        newCX = (float) (Math.random()) * viewWidth;
        newCY = (float) (Math.random()) * viewHeight;
        Log.i("GAME", "tick: x:" + newCX + "; y:" + newCY);
        invalidate();
    }
    // проверяем попала ли точка в круг. Да, математика и геометрия зачастую нужны и в реальной жизни
    private boolean isInCircle() {
        return Math.sqrt(Math.pow(newCX - newX, 2) + Math.pow(newCY - newY, 2)) <= radius;
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

}
