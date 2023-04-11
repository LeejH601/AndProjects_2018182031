package kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.R;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.BaseScene;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.IBoxCollidable;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.IGameObject;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.Sprite;

public class Bullet extends Sprite implements IBoxCollidable {
    private static final float BULLET_WIDTH = 28 * 0.0243f;
    private static final float BULLET_HEIGHT = 40 * 0.0243f;
    protected static float SPEED = 20.0f;
    protected static Paint paint;

    public Bullet(float x, float y) {
        super(R.mipmap.laser_1, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {
        float frameTime = BaseScene.frameTime;

        y += -SPEED * frameTime;
        fixDstRect();

        if (dstRect.bottom < 0) {
            BaseScene.getTopScene().remove(this);
        }
    }
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
}
