package ru.solandme.universal_congratulator;

import android.content.Intent;
import android.net.Uri;
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

    Holiday holiday = new Holiday();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        holiday.setHolidayName(getHolidayNameFromIntent());
        holiday.setCongratulates(getCongratulateArray());

        initViews();
    }

    private String[] getCongratulateArray() {
        // TODO реализовать выборку из базы данных
        switch (holiday.getHolidayName()) {
            case "Birthday":
                return getResources().getStringArray(R.array.birthday);
            case "NewYear":
                return getResources().getStringArray(R.array.newYear);
            case "Valentine":
                return getResources().getStringArray(R.array.valentine);
            case "WomanDay":
                return getResources().getStringArray(R.array.womansDay);
            case "MansDay":
                return getResources().getStringArray(R.array.mansDay);
            default:
                return null;
        }
    }

    private void initViews() {
        btnNext = (Button) findViewById(R.id.btnNext);
        textCongratulate = (TextView) findViewById(R.id.textCongratulate);

        if (holiday.getHolidayName() != null) {
            textCongratulate.setText(getRndTextCongratulate());
        }
    }

    private String getHolidayNameFromIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString(MainActivity.EXTRA_MESSAGE);
        }
        return null;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                textCongratulate.setText(getNextTextCongratulate());
                break;
            case R.id.btnPrev:
                textCongratulate.setText(getPrevTextCongratulate());
                break;
            case R.id.btnSend:
                String message = textCongratulate.getText().toString();
                sendSMS("", message);
                break;
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        String toSms = "smsto:" + phoneNumber;
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
        sms.putExtra("sms_body", message);
        startActivity(sms);
    }

    private String getNextTextCongratulate() {
        return holiday.congratulates[getNextPosition()];
    }

    private String getPrevTextCongratulate() {
        return holiday.getCongratulates()[getPrevPosition()];

    }

    private int getNextPosition() {
        if (currentPosition < holiday.getCongratulates().length - 1) {
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
            currentPosition = holiday.getCongratulates().length - 1;
        }
        return currentPosition;
    }

    private String getRndTextCongratulate() {
        //TODO реализовать работу с базой данных  SQLite
        int maxPosition = holiday.getCongratulates().length;
        int rndPosition = new Random().nextInt(maxPosition);
        currentPosition = rndPosition;
        return holiday.getCongratulates()[rndPosition];
    }
}
