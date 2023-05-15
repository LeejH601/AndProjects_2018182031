package kr.ac.tukorea.myapplication.tangtang2018182031.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.myapplication.tangtang2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.IGameObject;

public class EnemyGenerator implements IGameObject {
    private static final float GEN_INTERVAL = 5.0f;
    private static final String TAG = Enemy.class.getSimpleName();
    private float time;
    private int wave;
    @Override
    public void update() {
        time += BaseScene.frameTime;
        if (time > GEN_INTERVAL) {
            generate();
            time -= GEN_INTERVAL;
        }
    }

    private void generate() {
        wave++;
        Log.v(TAG, "Generating: wave " + wave);
        Random r = new Random();
        BaseScene scene = BaseScene.getTopScene();
        for (int i = 0; i < 5; i++) {
            scene.add(MainScene.Layer.enemy, Enemy.get(i, 0));
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}
