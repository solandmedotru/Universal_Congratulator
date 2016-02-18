package ru.solandme.universal_congratulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class TextActivity extends AppCompatActivity {
    TextView textCongratulate;
    Button btnNext;
    int currentPosition = 0;
    String[] congratulates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initViews();
    }

    private void initViews() {
        btnNext = (Button) findViewById(R.id.btnNext);
        textCongratulate = (TextView) findViewById(R.id.textCongratulate);

        String holiday = getHoliday();
        if (holiday != null) {
            String text = getRndTextCongratulate(holiday);
            textCongratulate.setText(text);
        }
    }

    private String getHoliday() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString(MainActivity.EXTRA_MESSAGE);
        }
        return null;
    }

    public void onClick(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.btnNext:
                // кнопка Next
                textCongratulate.setText(getNextTextCongratulate(getHoliday()));
                break;
            case R.id.btnPrev:
                // кнопка Prev
                textCongratulate.setText(getPrevTextCongratulate(getHoliday()));
                break;
        }
    }

    private String getNextTextCongratulate(String holiday) {

        switch (holiday) {
            case "Birthday":
                congratulates = getResources().getStringArray(R.array.birthday);
                return congratulates[getNextPosition()];
            case "NewYear":
                congratulates = getResources().getStringArray(R.array.newYear);
                return congratulates[getNextPosition()];
            case "Valentine":
                congratulates = getResources().getStringArray(R.array.valentine);
                return congratulates[getNextPosition()];
            case "WomanDay":
                congratulates = getResources().getStringArray(R.array.womansDay);
                return congratulates[getNextPosition()];
            case "MansDay":
                congratulates = getResources().getStringArray(R.array.mansDay);
                return congratulates[getNextPosition()];
            default:
                return "";
        }
    }

    private String getPrevTextCongratulate(String holiday) {

        switch (holiday) {
            case "Birthday":
                congratulates = getResources().getStringArray(R.array.birthday);
                return congratulates[getPrevPosition()];
            case "NewYear":
                congratulates = getResources().getStringArray(R.array.newYear);
                return congratulates[getPrevPosition()];
            case "Valentine":
                congratulates = getResources().getStringArray(R.array.valentine);
                return congratulates[getPrevPosition()];
            case "WomanDay":
                congratulates = getResources().getStringArray(R.array.womansDay);
                return congratulates[getPrevPosition()];
            case "MansDay":
                congratulates = getResources().getStringArray(R.array.mansDay);
                return congratulates[getPrevPosition()];
            default:
                return "";
        }
    }

    private int getNextPosition() {
        if (currentPosition < congratulates.length-1) {
            currentPosition = currentPosition + 1;
        } else {
            currentPosition = 0;
        }
        return currentPosition;
    }

    private int getPrevPosition() {
        if (currentPosition > 0) {
            currentPosition = currentPosition - 1;
        } else {
            currentPosition = congratulates.length-1;
        }
        return currentPosition;
    }

    private String getRndTextCongratulate(String holiday) {
        //TODO реализовать работу с базой данных SQLite
        String[] congratulates;
        int rndPosition;
        switch (holiday) {
            case "Birthday":
                congratulates = getResources().getStringArray(R.array.birthday);
                rndPosition = new Random().nextInt(congratulates.length);
                currentPosition = rndPosition;
                return congratulates[rndPosition];
            case "NewYear":
                congratulates = getResources().getStringArray(R.array.newYear);
                rndPosition = new Random().nextInt(congratulates.length);
                currentPosition = rndPosition;
                return congratulates[rndPosition];
            case "Valentine":
                congratulates = getResources().getStringArray(R.array.valentine);
                rndPosition = new Random().nextInt(congratulates.length);
                currentPosition = rndPosition;
                return congratulates[rndPosition];
            case "WomanDay":
                congratulates = getResources().getStringArray(R.array.womansDay);
                rndPosition = new Random().nextInt(congratulates.length);
                currentPosition = rndPosition;
                return congratulates[rndPosition];
            case "MansDay":
                congratulates = getResources().getStringArray(R.array.mansDay);
                rndPosition = new Random().nextInt(congratulates.length);
                currentPosition = rndPosition;
                return congratulates[rndPosition];
            default:
                return "";
        }
    }
}
