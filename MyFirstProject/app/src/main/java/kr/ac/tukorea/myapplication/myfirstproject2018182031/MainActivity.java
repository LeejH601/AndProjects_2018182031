package kr.ac.tukorea.myapplication.myfirstproject2018182031;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnPushMe(View view){
        TextView tv = findViewById(R.id.snumTextView);
        tv.setText("PushMe");
    }

    public void onBtnAnother(View view) {
        TextView tv = findViewById(R.id.snumTextView);
        tv.setText("Another");
    }
}