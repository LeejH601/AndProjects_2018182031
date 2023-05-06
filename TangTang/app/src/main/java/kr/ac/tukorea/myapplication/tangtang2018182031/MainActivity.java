package kr.ac.tukorea.myapplication.tangtang2018182031;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.myapplication.tangtang2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.tangtang2018182031.framework.GameView;
import kr.ac.tukorea.myapplication.tangtang2018182031.game.MainScene;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new MainScene().pushScene();
    }


    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
}