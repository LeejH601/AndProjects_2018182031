package kr.ac.tukorea.myapplication.cookierun2018182031.game;

import kr.ac.tukorea.myapplication.cookierun2018182031.framework.IRecyclable;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.Sprite;

import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.myapplication.cookierun2018182031.framework.IBoxCollidable;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.Sprite;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.BaseScene;

public class MapObject extends Sprite implements IBoxCollidable, IRecyclable {
    private static final String TAG = MapObject.class.getSimpleName();
    public static final float SPEED = 2.0f;
    @Override
    public void update() {
        float dx = -SPEED * BaseScene.frameTime;
        dstRect.offset(dx, 0);
        if (dstRect.right < 0) {
//            Log.d(TAG, "Removing:" + this);
            BaseScene.getTopScene().remove(getLayer(), this);
        }
    }

    protected MainScene.Layer getLayer() {
        return MainScene.Layer.platform;
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
    @Override
    public void onRecycle() {}
}
