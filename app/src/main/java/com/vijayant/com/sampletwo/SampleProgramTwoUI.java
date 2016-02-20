package com.vijayant.com.sampletwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SampleProgramTwoUI extends AppCompatActivity implements StoreResponse {

    TextView frmadd,lat,lng,placeid;

    Button go;

    SampleProgramTwo spt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frmadd = (TextView)findViewById(R.id.frmadd);
        lat = (TextView)findViewById(R.id.lat);
        lng = (TextView)findViewById(R.id.lng);
        placeid =(TextView)findViewById(R.id.placeid);

        go = (Button)findViewById(R.id.go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
            }
        });
    }

    private void getValues() {
            spt = new SampleProgramTwo(this);
            spt.delegate = this;
            spt.execute();
        }


    @Override
    public void store(String[] result) {

        frmadd.setText(result[0]);
        lat.setText(result[1]);
        lng.setText(result[2]);
        placeid.setText(result[3]);
    }

}
