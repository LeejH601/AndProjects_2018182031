package kr.ac.tukorea.myapplication.imageswitcher2018182031;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int pageIndex = 0;

    private static final int[] IMG_RES_IDS = new int[]{
            R.mipmap.cat_1,
            R.mipmap.cat_2,
            R.mipmap.cat_3,
            R.mipmap.cat_4,
            R.mipmap.cat_5,
            R.mipmap.cat_6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imgView = findViewById(R.id.MainImageView);
        this.tv = findViewById(R.id.pageTextView);
        tv.setText((pageIndex+1) + " / " + MAX_IMG_COUNT);
        this.PrevButton = findViewById(R.id.PrevBtn);
        this.NextButton = findViewById(R.id.NextBtn);
        PrevButton.setEnabled(this.pageIndex > 0);
        NextButton.setEnabled(this.pageIndex < MAX_IMG_COUNT-1);
    }

    public void onBtnPrev(View view) {
        setPage(pageIndex - 1);
        Log.d(TAG, "Prev Presssed");
    }

    public void onBtnNext(View view) {
        setPage(pageIndex + 1);
        Log.d(TAG, "Next Presssed");
    }
    private ImageView imgView;
    private TextView tv;
    private Button PrevButton;
    private Button NextButton;
    private final int MAX_IMG_COUNT = 6;

    private void setPage(int index){
        if(index < 0 || index > MAX_IMG_COUNT-1) return;
        int resId = IMG_RES_IDS[index];
        imgView.setImageResource(resId);
        this.pageIndex = index;
        tv.setText((pageIndex+1) + " / " + MAX_IMG_COUNT);
        PrevButton.setEnabled(index > 0);
        NextButton.setEnabled(index < MAX_IMG_COUNT-1);
    }
}