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
        for (IGameObject o1 : enemies) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
//            boolean removed = false;
            for (IGameObject o2 : bullets) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
                    scene.remove(bullet); // is this recyclable?
                    boolean dead = enemy.decreaseLife(bullet.getPower());
                    if (dead) {
                        scene.remove(enemy);
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