package kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.R;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.BitmapPool;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.IGameObject;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.Metrics;

public class Score implements IGameObject {
    private final Bitmap bitmap;
    private final int srcCharWidth, srcCharHeight;
    private final float right, top;
    private final float dstCharWidth, dstCharHeight;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private int score, displayScore;

    public Score() {
        this.bitmap = BitmapPool.get(R.mipmap.number_24x32);
        this.right = Metrics.game_width - 0.5f;
        this.top = 0.5f;
        this.dstCharWidth = 0.6f;
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
        this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    @Override
    public void update() {
        int diff = score - displayScore;
        if (diff == 0) return;
        if (-10 < diff && diff < 0) {
            displayScore--;
        } else if (0 < diff && diff < 10) {
            displayScore++;
        } else {
            displayScore += diff / 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        float x = right;
        while (value > 0) {
            int digit = value % 10;
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            value /= 10;
        }
    }
    public void add(int amount) {
        score += amount;
    }
}