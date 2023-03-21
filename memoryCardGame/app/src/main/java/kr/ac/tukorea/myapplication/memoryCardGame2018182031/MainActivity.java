package kr.ac.tukorea.myapplication.memoryCardGame2018182031;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

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

        scoreTextView = findViewById(R.id.scoreTextView);

        startGame();
    }

    private void startGame() {
        setFlips(0); // 이것도 잊지말고 하자.
        openCardCount = BUTTON_IDS.length;

        Random r = new Random();
        for (int i = 0; i < resIds.length; i++) {
            int t = r.nextInt(resIds.length);
            int id = resIds[t];
            resIds[t] = resIds[i];
            resIds[i] = id;
        }

        for (int i = 0;i< BUTTON_IDS.length;++i){
            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setTag(resIds[i]);
            btn.setVisibility(View.VISIBLE);
            btn.setImageResource(R.mipmap.card_blue_back);
        }
    }

    private ImageButton previousButton;
    private TextView scoreTextView;
    private int flips = 0;
    private int openCardCount = 0;
    public void onBtnCard(View view){
        Log.d(TAG, "Card ID = " +getIndeWithId(view.getId()));
        ImageButton btn = (ImageButton) view;
        if (btn == previousButton) {
            // 같은 카드가 눌리면 무시하고 Toast를 보여줌
            Toast.makeText(this, R.string.same_card_toast, Toast.LENGTH_SHORT).show();
            return;
        }

        int resId = (Integer)btn.getTag(); // 이미지 리소스 아이디가 Tag 로 매달려 있다
        btn.setImageResource(resId);

        if (previousButton != null) {
            int prevResId = (Integer)previousButton.getTag(); // 이전 카드의 tag 를 살펴본다
            if (resId == prevResId) {
                btn.setVisibility(View.INVISIBLE);
                previousButton.setVisibility(View.INVISIBLE);
                previousButton = null;
                openCardCount -= 2;
                if (openCardCount == 0) {
                    askRetry();
                }
                return;
            } else {
                // 이전의 카드는 뒷면이 보이도록 되돌려둔다
                previousButton.setImageResource(R.mipmap.card_blue_back);
            };
        }
        setFlips(flips + 1);
        previousButton = btn;
    }

    private void setFlips(int flips) {
        this.flips = flips;
        //scoreTextView.setText("Flips: " + flips);
        String text = getString(R.string.score_flips_fmt, flips);
        scoreTextView.setText(text);
    }

    public void onBtnRestart(View view) {
        askRetry();
    }

    private void askRetry() {
        new AlertDialog.Builder(this).setTitle(R.string.restart_dlg_title)
                .setMessage(R.string.restart_dlg_msg)
                .setPositiveButton(R.string.common_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();
                        Log.d(TAG, "Restart Here");
                    }
                })
                .setNegativeButton(R.string.common_no, null)
                .create()
                .show();
    }
}