package ru.solandme.universal_congratulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initViews();
    }

    private void initViews() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String text = extras.getString(MainActivity.EXTRA_MESSAGE);
            TextView textCongratulate = (TextView) findViewById(R.id.textCongratulate);
            textCongratulate.setText(text);
        }
    }
}
