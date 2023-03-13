package kr.ac.tukorea.myapplication.myfirstproject2018182031;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.pushMeButton);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.another);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView tv;
        switch (view.getId()){
            case R.id.pushMeButton:
                tv = findViewById(R.id.snumTextView);
                tv.setText("PushMe");
                break;
            case R.id.another:
                tv = findViewById(R.id.snumTextView);
                tv.setText("Another");
                    break;
        }

    }
}