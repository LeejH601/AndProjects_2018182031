package kr.ac.tukorea.myapplication.cookierun2018182031.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.BaseScene;
import kr.ac.tukorea.myapplication.cookierun2018182031.framework.GameView;
import kr.ac.tukorea.myapplication.cookierun2018182031.game.MainScene;

import kr.ac.tukorea.myapplication.cookierun2018182031.R;

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