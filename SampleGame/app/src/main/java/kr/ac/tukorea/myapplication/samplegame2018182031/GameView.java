package kr.ac.tukorea.myapplication.samplegame2018182031;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    private ArrayList<IGameObject> objects = new ArrayList<>();
    private Fighter fighter;
    private float scale;


    public GameView(Context context) {
        super(context);
        init(null, 0);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        Resources res = getResources();

        Bitmap soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        Ball.setBitmap(soccerBitmap);
        Bitmap fighterBitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
        Fighter.setBitmap(fighterBitmap);

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            float dx = r.nextFloat() * 0.05f + 0.03f;
            float dy = r.nextFloat() * 0.05f + 0.03f;
            objects.add(new Ball(dx, dy));
        }

        fighter = new Fighter();
        objects.add(fighter);

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long nanos) {
        update();
        invalidate();
        if (isShown()) {
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    private void update() {
        for (IGameObject gobj : objects) {
            gobj.update();
        }
        //fighter.update();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        scale = w / 10.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.scale(scale, scale);

        for (IGameObject gobj : objects) {
            gobj.draw(canvas);
        }

//        fighter.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
       switch (action){
           case MotionEvent.ACTION_DOWN:
           case MotionEvent.ACTION_MOVE:
               float x = (float) event.getX() / scale;
               float y = (float) event.getY() / scale;
               fighter.setPosition(x, y);
               return true;
       }
        return super.onTouchEvent(event);
    }
}