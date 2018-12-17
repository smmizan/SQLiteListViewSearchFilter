package com.smmizan.sqlitelistviewsearchfiltering;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //https://androidjson.com/android-add-search-box-filter-sqlite/

    SQLiteDatabase sqLiteDatabase;
    Boolean EditTextEmptyHold;
    EditText etName, etCodes;
    Button SubmitData, ShowData;
    String name, phoneNumber, query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SubmitData = (Button)findViewById(R.id.bInsert);
        etCodes = (EditText)findViewById(R.id.editText2);
        ShowData = (Button)findViewById(R.id.bRead);
        etName = (EditText)findViewById(R.id.editText);

        SubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EditTextInsetClean();


            }
        });

        ShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SearchSQLiteActivity.class);
                startActivity(intent);
            }
        });


    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_Name+" VARCHAR, "+SQLiteHelper.Table_Column_2_PhoneNumber+" VARCHAR);");

    }

    public void CheckEditTextStatus(){

        name = etName.getText().toString() ;
        phoneNumber = etCodes.getText().toString();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phoneNumber)){

            EditTextEmptyHold = false ;

        }
        else {

            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {

            query = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,phone_number) VALUES('"+name+"', '"+phoneNumber+"');";

            sqLiteDatabase.execSQL(query);

            Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    public void EditTextInsetClean(){

        etName.getText().clear();

        etCodes.getText().clear();

    }
}
