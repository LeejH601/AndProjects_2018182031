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

        Button btn = findViewById(R.id.pushMeButton);
        btn.setOnClickListener(pushMeOnClickHandler);

        btn = findViewById(R.id.another);
        btn.setOnClickListener(anotherOnClickHandler);
    }

    private View.OnClickListener pushMeOnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.snumTextView);
            tv.setText("PushMe");
        }
    };
    private View.OnClickListener anotherOnClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.snumTextView);
            tv.setText("Another");
        }
    };
}