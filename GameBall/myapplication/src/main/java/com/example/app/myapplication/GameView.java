package com.example.app.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{
    Thread t;
    SurfaceHolder holder;
    Paint p, p1;
    boolean dragging, drawing = true;
    float cx, cy, r, cx1, cy1;
    float vx, vy, height, wight;

    public GameView(Context context) {
        super(context);
        t = new Thread(this);
        holder = getHolder();
        p = new Paint();
        p1 = new Paint();
        p.setColor(Color.YELLOW);
        p1.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p1.setStyle(Paint.Style.FILL);
        cx = 100;
        cy = 150;
        cx1 = 150;
        cy1 = 100;
        r = 100;
        vx = 30;
        vy = 30;
    }
private void update (){
    cx += vx;
    cy += vy;

    if (cx < r) {
        cx = r;
        vx = -vx;
    } else if (cx > wight - r) {
        cx = wight - r;
        vx = -vx;
    }

    if (cy < r) {
        cy = r;
        vy = -vy;
    } else if (cy > height - r) {
        cy = height - r;
        vy = -vy;
    }

}

    public void resume(){
        drawing = true;
        t = new Thread(this);
        t.start();
    }

    public void pause(){
        drawing = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (isInCircle((int)event.getX(), (int)event.getY())) {
                    dragging = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (dragging) {
                    cx1 = event.getX();
                    cy1 = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                dragging = false;
                break;
        }
        return true;
    }
    private boolean isInCircle(int eventX, int eventY) {
        return Math.sqrt(Math.pow(cx1 - eventX, 2) + Math.pow(cy1 - eventY, 2)) < r;
    }
    @Override
    public void run() {
        while (true) {
            SurfaceHolder holder = getHolder();
            if (holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                update();
                paint(canvas);
                height = canvas.getHeight();
                wight = canvas.getWidth();
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
    private void paint (Canvas canvas) {
        canvas.drawARGB(250, 127, 199, 255); //заливка
        canvas.drawCircle(cx, cy, r, p);
        canvas.drawCircle(cx1, cy1, r, p1);
        Log.i("Game ", "painting");
    }
}
