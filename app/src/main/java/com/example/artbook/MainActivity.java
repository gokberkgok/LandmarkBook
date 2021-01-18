package com.example.artbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> nameArray;
    ArrayList<Integer> idArray;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        nameArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nameArray);
        listView.setAdapter(arrayAdapter);
        getData();
    }


    public void getData(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM arts",null);
            int nameIndex = cursor.getColumnIndex("artname");
            int idIndex = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                nameArray.add(cursor.getString(nameIndex));
                idArray.add(cursor.getInt(idIndex));
            }

            arrayAdapter.notifyDataSetChanged();
            cursor.close();
        }catch (Exception e ){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater
        MenuInflater menuInflater = getMenuInflater();  //MainActivity de menu yü bağlama
        menuInflater.inflate(R.menu.add_art,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //İteme basılınca ne olacak bunu belirtiyoruz
        if (item.getItemId() == R.id.addArt){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

