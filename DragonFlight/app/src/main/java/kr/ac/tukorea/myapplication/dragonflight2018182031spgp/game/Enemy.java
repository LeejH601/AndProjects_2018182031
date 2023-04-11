package kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game;

import android.graphics.RectF;

import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.R;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.AnimSprite;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.BaseScene;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.Sprite;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.Metrics;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.IBoxCollidable;


public class Enemy extends Sprite {
    private static final int[] resIds = {
            R.mipmap.f_01_01, R.mipmap.f_02_01, R.mipmap.f_03_01, R.mipmap.f_04_01, R.mipmap.f_05_01,
            R.mipmap.f_06_01, R.mipmap.f_07_01, R.mipmap.f_08_01, R.mipmap.f_09_01, R.mipmap.f_10_01,
    };
    private static final float SPEED = 2.0f;
    public static final float SIZE = 1.8f;
    public Enemy(int index, int level) {
        super(resIds[level], (Metrics.game_width / 10) * (2 * index + 1), -SIZE, SIZE, SIZE);
    }

    @Override
    public void update() {
        super.update();
        y += SPEED * BaseScene.frameTime;
        fixDstRect();

        if (dstRect.top > 16.0) {
            BaseScene.getTopScene().remove(this);
        }
    }


}