package kr.ac.tukorea.myapplication.dragonflight2018182031spgp.game;


import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.BaseScene;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.Metrics;
import kr.ac.tukorea.myapplication.dragonflight2018182031spgp.framework.Sprite;

public class Background extends Sprite {
    private final float speed;
    private final float height;
    public Background(int bitmapResId, float speed) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        setSize(Metrics.game_width, height);
        this.speed = speed;
    }
    @Override
    public void update() {
        this.y += speed * BaseScene.frameTime;
        if (this.y > Metrics.game_height) {
            this.y = 0;
        }
        setSize(Metrics.game_width, height);
    }
}