package kr.ac.tukorea.myapplication.tangtang2018182031.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.myapplication.tangtang2018182031.R;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.AnimSprite;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.IBoxCollidable;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.IGameObject;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.Metrics;

public class Player extends AnimSprite implements IBoxCollidable {
    private float jumpSpeed;
    private static final float JUMP_POWER = 9.0f;
    private static final float GRAVITY = 17.0f;
    private RectF collisionRect = new RectF();
    private static final float PLAYER_WIDTH = 72 * 0.0243f; //1.75f;
    private static final float PLAYER_HEIGHT = 80 * 0.0243f; //1.75f;
    private static final float PLAYER_LEFT = PLAYER_WIDTH / 2;
    private static final float PLAYER_RIGHT = 9.0f - PLAYER_WIDTH / 2;

    private static final float PLAYER_TOP = PLAYER_HEIGHT / 2;
    private static final float PLAYER_BOTTOM = 16.0f - PLAYER_HEIGHT / 2;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 10.0f;
    private RectF targetRect = new RectF();
    private float tx;
    private float ty;

    public Player() {
        super(R.mipmap.pngegg, 2.0f, 3.0f, 2.0f, 2.0f, 8, 1);
        fixCollisionRect();
    }
    protected static float[][] edgeInsetRatios = {
            { 0.1f, 0.01f, 0.1f, 0.0f }, // State.running
            { 0.1f, 0.20f, 0.1f, 0.0f }, // State.jump
            { 0.2f, 0.20f, 0.2f, 0.0f }, // State.doubleJump
            { 0.2f, 0.01f, 0.2f, 0.0f }, // State.falling
            { 0.00f, 0.50f, 0.00f, 0.00f }, // slide
    };

    protected enum State {
        walk_right, walk_left, COUNT
    }
    protected State state = State.walk_left;

    protected static Rect[][] srcRects = {
            makeRects(700, 701, 702, 703,704,705,706,707,708,709), // State.idle
            makeRects(500, 501, 502, 503,504,505,506,507,508,509), // State.idle
    };
    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = (idx % 100) * 120;
            int t = (idx / 100) * 130;
            rects[i] = new Rect(l, t, l + 120, t + 130);
        }
        return rects;
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        this.ty = ty;
        targetRect.set(
                tx - TARGET_RADIUS, ty - TARGET_RADIUS,
                tx + TARGET_RADIUS, ty + TARGET_RADIUS);
    }

    @Override
    public void update() {
        float time = BaseScene.frameTime;
        if (tx >= x) {
            x += SPEED * time;
            if (x > tx) {
                x = tx;
            }
        } else {
            x -= SPEED * time;
            if (x < tx) {
                x = tx;
            }
        }
        if (ty >= y) {
            y += SPEED * time;
            if (y > ty) {
                y = ty;
            }
        } else {
            y -= SPEED * time;
            if (y < ty) {
                y = ty;
            }
        }
        if (x < PLAYER_LEFT) x = tx = PLAYER_LEFT;
        if (x > PLAYER_RIGHT) x = tx = PLAYER_RIGHT;
        if (y < PLAYER_TOP) y = ty = PLAYER_TOP;
        if (y > PLAYER_BOTTOM) y = ty = PLAYER_BOTTOM;

        fixDstRect();
        fixCollisionRect();

        int sign = tx < x ? -1 : x < tx ? 1 : 0;
        if(sign < 0){
            state = state.walk_left;
        }
        else if(sign > 0){
            state = state.walk_right;
        }
    }



    private void fixCollisionRect() {
        float[] insets = edgeInsetRatios[state.ordinal()];
        collisionRect.set(
                dstRect.left + width * insets[0],
                dstRect.top + height * insets[1],
                dstRect.right - width * insets[2],
                dstRect.bottom - height * insets[3]);
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
}
