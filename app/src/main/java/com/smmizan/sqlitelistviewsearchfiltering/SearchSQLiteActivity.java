package com.smmizan.sqlitelistviewsearchfiltering;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchSQLiteActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<MyModel> StudentList = new ArrayList<MyModel>();
    MyAdapter listAdapter;
    SQLiteHelper sqLiteHelper;
    EditText editText;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sqlite);


        listView = (ListView) findViewById(R.id.listView1);

        editText = (EditText) findViewById(R.id.edittext1);

        listView.setTextFilterEnabled(true);

        sqLiteHelper = new SQLiteHelper(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Getting Search ListView clicked item.
                MyModel ListViewClickData = (MyModel) parent.getItemAtPosition(position);

                // printing clicked item on screen using Toast message.
                Toast.makeText(SearchSQLiteActivity.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(SearchSQLiteActivity.this, DetailsActivity.class);
                // Pass all data rank
                intent.putExtra("name",ListViewClickData.getName());
                // Pass all data country
                intent.putExtra("code",ListViewClickData.getCode());
                startActivity(intent);


            }
        });


        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {

                listAdapter.getFilter().filter(stringVar.toString());
            }
        });

    }

    public void DisplayDataInToListView() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        MyModel student;
        StudentList = new ArrayList<MyModel>();

        if (cursor.moveToFirst()) {
            do {

                String tempName = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));

                String tempNumber= cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber));

                student = new MyModel(tempName, tempNumber);

                StudentList.add(student);

            } while (cursor.moveToNext());
        }

        listAdapter = new MyAdapter(SearchSQLiteActivity.this, R.layout.custom_layout, StudentList);

        listView.setAdapter(listAdapter);

        cursor.close();
    }

    @Override
    protected void onResume() {

        DisplayDataInToListView() ;

        super.onResume();
    }
}
