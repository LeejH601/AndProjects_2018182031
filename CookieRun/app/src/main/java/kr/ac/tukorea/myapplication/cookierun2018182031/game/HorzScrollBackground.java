package kr.ac.tukorea.myapplication.cookierun2018182031.game;

import android.graphics.Canvas;

import kr.ac.tukorea.myapplication.cookierun2018182031.framework.Sprite;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.Metrics;

public class HorzScrollBackground extends Sprite {
    private final float speed;

    public HorzScrollBackground(int bitmapResId, float speed) {
        setBitmapResource(bitmapResId);
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        this.speed = speed;
    }
    @Override
    public void update() {
        x += speed * BaseScene.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float curr = x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.game_width) {
            dstRect.set(curr, 0, curr + width, Metrics.game_height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }
}