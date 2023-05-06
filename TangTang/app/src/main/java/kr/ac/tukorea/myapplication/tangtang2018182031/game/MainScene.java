package kr.ac.tukorea.myapplication.tangtang2018182031.game;

import android.view.MotionEvent;

import kr.ac.tukorea.myapplication.tangtang2018182031.R;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.Button;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.Metrics;
import kr.ac.tukorea.myapplication.tangtang2018182031.game.Player;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;
    public enum Layer {
        bg, platform, item, player, ui, touch, controller, COUNT
    }
    public MainScene() {
        Metrics.setGameSize(9.0f, 16.0f);
        initLayers(Layer.COUNT);

        player = new Player();
        add(Layer.player, player);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            player.jump();
//        }
//        return super.onTouchEvent(event);
//    }
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                player.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}