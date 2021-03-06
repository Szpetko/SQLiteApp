package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //references to buttons
    Button btn_Add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_active;
    ListView lv_userList;

    ArrayAdapter userArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Add = findViewById(R.id.btn_Add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        sw_active = findViewById(R.id.sw_active);
        lv_userList = findViewById(R.id.lv_userList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        UpdateListView(dataBaseHelper);

        //button listeners
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel;

                try {
                    userModel = new UserModel(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_active.isChecked());
                    //Toast.makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "error", 0,false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(userModel);
                Toast.makeText(MainActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                UpdateListView(dataBaseHelper);

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                //List<UserModel> everyone = dataBaseHelper.getEveryUser();

                UpdateListView(dataBaseHelper);

                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        lv_userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel clickedUser = (UserModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedUser);
                UpdateListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted: " + clickedUser, Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void UpdateListView(DataBaseHelper dataBaseHelper2) {
        userArrayAdapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryUser());
        lv_userList.setAdapter(userArrayAdapter);
    }


}