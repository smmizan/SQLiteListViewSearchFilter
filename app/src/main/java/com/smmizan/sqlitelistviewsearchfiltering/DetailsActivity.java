package com.smmizan.sqlitelistviewsearchfiltering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        String TempItem = getIntent().getStringExtra("name");
        String TempItem2 = getIntent().getStringExtra("code");


        t1 = (TextView) findViewById(R.id.text11);
        t2 = (TextView) findViewById(R.id.text22);




        t1.setText(TempItem);
        t2.setText(TempItem2);
        ;


    }
}
