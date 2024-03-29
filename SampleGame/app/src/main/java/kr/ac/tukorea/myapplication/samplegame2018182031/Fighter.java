package kr.ac.tukorea.myapplication.samplegame2018182031;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Fighter implements IGameObject {
    private static final float RADIUS = 1.25f;
    private static Bitmap bitmap;
    private RectF dstRect = new RectF();

    private float x, y;
    private float angle;

    public Fighter() {
        x = 5.0f;
        y = 12.0f;
        dstRect.set(x-RADIUS, y, x+RADIUS, y+2*RADIUS);
    }

    public static void setBitmap(Bitmap bitmap) {
        Fighter.bitmap = bitmap;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        canvas.drawBitmap(bitmap, null, dstRect, null);
        canvas.restore();
    }

    public void setPosition(float x, float y) {
        float dx = x - this.x;
        float dy = y - this.y;
        double radian = Math.atan2(dy, dx);
        angle = (float) Math.toDegrees(radian) + 90;
        dstRect.set(x-RADIUS, y-RADIUS, x+RADIUS, y+RADIUS);
        this.x = x;
        this.y = y;
    }
}
