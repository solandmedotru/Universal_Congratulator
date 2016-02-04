package ru.solandme.universal_congratulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNewYear(View view){
        Toast.makeText(this, "Click to New Year", Toast.LENGTH_SHORT).show();

    }

    public void onClickValentine(View view){
        Toast.makeText(this, "Click to Valentine", Toast.LENGTH_SHORT).show();

    }

}
