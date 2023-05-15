package kr.ac.tukorea.myapplication.tangtang2018182031.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.myapplication.tangtang2018182031.R;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.AnimSprite;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.BitmapPool;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.Gauge;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.IBoxCollidable;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.IRecyclable;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.Metrics;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.RecycleBin;


public class Enemy extends AnimSprite implements IBoxCollidable, IRecyclable {
    private static final String TAG = Enemy.class.getSimpleName();
    private static final int[] resIds = {
            R.mipmap.pngwing,
    };
    public static final int MAX_LEVEL = resIds.length - 1;
    private static final float SPEED = 2.0f;
    public static final float SIZE = 1.8f;
    private int level;
    protected int life, maxLife;
    protected enum State {
        idle, COUNT
    }
    protected State state = State.idle;

    protected static Rect[][] srcRects = {
            makeRects(101, 101, 102, 103,104,105), // State.idle
    };

    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = (idx % 100) * 119;
            int t = (idx / 100) * 130;
            rects[i] = new Rect(l, t, l + 120, t + 130);
        }
        return rects;
    }

    protected RectF collisionRect = new RectF();
   // protected static ArrayList<Enemy> recycleBin = new ArrayList<>();

    static Enemy get(int index, int level) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if (enemy != null) {
            enemy.x = (Metrics.game_width / 10) * (2 * index + 1);
            enemy.y = -SIZE/2;
            enemy.fixDstRect();
            enemy.init(level);
            return enemy;
        }
        return new Enemy(index, level);
    }
    private Enemy(int index, int level) {
        super(resIds[level], (Metrics.game_width / 10) * (2 * index + 1), -SIZE/2, SIZE, SIZE, 10, 0);
        init(level);
    }

    private void init(int level) {
        this.bitmap = BitmapPool.get(resIds[level]);
        this.life = this.maxLife = (1) * 10;
    }

    @Override
    public void update() {
        super.update();
        y += SPEED * BaseScene.frameTime;
        fixDstRect();

        if (dstRect.top > 16.0) {
            BaseScene.getTopScene().remove(MainScene.Layer.enemy, this);
        }
        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = srcRects[state.ordinal()];
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }


    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
    @Override
    public void onRecycle() {
    }

    public int getScore() {
        return 10 * (level + 1);
    }
    public boolean decreaseLife(int power) {
        life -= power;
        if (life <= 0) return true;
        return false;
    }
}