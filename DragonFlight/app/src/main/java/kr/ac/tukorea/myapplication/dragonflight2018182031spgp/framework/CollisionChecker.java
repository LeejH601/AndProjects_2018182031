package kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;


import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game.Bullet;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game.CollisionHelper;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game.Enemy;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game.MainScene;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> enemies = scene.getObjectsAt(MainScene.Layer.enemy);
        ArrayList<IGameObject> bullets = scene.getObjectsAt(MainScene.Layer.bullet);
        for (int ei = enemies.size() - 1; ei >= 0; ei--) {
            Enemy enemy = (Enemy) enemies.get(ei);
//            boolean removed = false;
            for (int bi = bullets.size() - 1; bi >= 0; bi--) {
                Bullet bullet = (Bullet) bullets.get(bi);
                if (CollisionHelper.collides(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
                    scene.remove(MainScene.Layer.enemy, enemy, false);
                    boolean dead = enemy.decreaseLife(bullet.getPower());
                    if (dead) {
                        scene.remove(MainScene.Layer.enemy, enemy);
                        scene.addScore(enemy.getScore());
                    }
//                    removed = true;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
