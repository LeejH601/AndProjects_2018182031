package kr.ac.tukorea.myapplication.memoryCardGame2018182031;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private int[] resIds = new int[]{
            R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,R.mipmap.card_5s,
            R.mipmap.card_as,R.mipmap.card_jc,R.mipmap.card_kd,R.mipmap.card_qh,
            R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,R.mipmap.card_5s,
            R.mipmap.card_as,R.mipmap.card_jc,R.mipmap.card_kd,R.mipmap.card_qh,
    };

    private static final int[] BUTTON_IDS = new int[]{
            R.id.card_00,R.id.card_01,R.id.card_02,R.id.card_03,
            R.id.card_10,R.id.card_11,R.id.card_12,R.id.card_13,
            R.id.card_20,R.id.card_21,R.id.card_22,R.id.card_23,
            R.id.card_30,R.id.card_31,R.id.card_32,R.id.card_33,
    };

    private static HashMap<Integer, Integer> idMap;
    static{
        idMap = new HashMap<>();
        for (int i =0;i<BUTTON_IDS.length;++i){
            idMap.put(BUTTON_IDS[i], i);
        }
    }

    private static int getIndeWithId(int id) {
        Integer index = idMap.get(id);
        if(index == null){
            Log.e(TAG, "Cannot find the button with id : " + id);
            return -1;
        }
        return index;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0;i< BUTTON_IDS.length;++i){
            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setTag(resIds[i]);
        }
    }

    private ImageButton previousButton;
    public void onBtnCard(View view){
        Log.d(TAG, "Card ID = " +getIndeWithId(view.getId()));
        ImageButton btn = (ImageButton) view;
        btn.setImageResource(R.mipmap.card_blue_back);
        if(previousButton != null){
            int resId = (Integer)previousButton.getTag();
            previousButton.setImageResource(resId);
        }
        previousButton = btn;
    }
}