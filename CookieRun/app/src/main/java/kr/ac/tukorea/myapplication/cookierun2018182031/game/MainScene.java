package kr.ac.tukorea.myapplication.cookierun2018182031.game;

import android.view.MotionEvent;
import java.util.Random;

import kr.ac.tukorea.myapplication.cookierun2018182031.R;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.Metrics;

public class MainScene extends BaseScene {
    private final Player player;
    public enum Layer {
        bg, platform, item, player, controller, COUNT
    }
    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);

        add(Layer.bg, new VertScrollBackground(R.mipmap.cookie_run_bg_1, 1.0f));
        add(Layer.bg, new VertScrollBackground(R.mipmap.cookie_run_bg_2, 2.0f));
        add(Layer.bg, new VertScrollBackground(R.mipmap.cookie_run_bg_3, 3.0f));

        add(Layer.controller, new MapLoader());

        player = new Player();
        add(Layer.player, player);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump();
        }
        return super.onTouchEvent(event);
    }
}