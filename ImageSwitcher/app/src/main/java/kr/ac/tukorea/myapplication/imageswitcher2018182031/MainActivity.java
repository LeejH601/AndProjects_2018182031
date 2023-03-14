package kr.ac.tukorea.myapplication.imageswitcher2018182031;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnPrev(View view) {
        ImageView imgView = findViewById(R.id.MainSrcImg);
        Log.d("MainActivity", "Prev Presssed");
    }

    public void onBtnNext(View view) {
        Log.d("MainActivity", "Next Presssed");
    }
}