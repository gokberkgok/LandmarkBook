package com.example.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity {
    //static Bitmap selectedImage;        //heryerden ulaşılabilir -- static
    //Singleton kullanacağız

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        //Data

        final ArrayList<String> landmarkNames = new ArrayList<>();
        landmarkNames.add("New York");
        landmarkNames.add("Eyfel Kulesi");
        landmarkNames.add("Pisa");
        landmarkNames.add("London Bridge");

        final ArrayList<String> countryNames = new ArrayList<>();
        countryNames.add("America");
        countryNames.add("France");
        countryNames.add("Italy");
        countryNames.add("United Kingdom");

        Bitmap newyork = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.newyork);
        Bitmap eyfel = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eyfel);
        Bitmap pisa = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisa);
        Bitmap londonbridge = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.lb);

        final ArrayList<Bitmap> landmarkImages = new ArrayList<>();

        landmarkImages.add(newyork);
        landmarkImages.add(eyfel);
        landmarkImages.add(pisa);
        landmarkImages.add(londonbridge);


        //ListView
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,landmarkNames);

        listView.setAdapter(arrayAdapter);  // bağlama

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  // position index i belirtiyor
                Intent intent = new Intent(MainActivity.this,DetayActivity.class);
                intent.putExtra("name",landmarkNames.get(position));
                intent.putExtra("country",countryNames.get(position));
                Singleton singleton = Singleton.getInstance();
                singleton.setChosenImage(landmarkImages.get(position));
                //selectedImage = landmarkImages.get(position);


                startActivity(intent);

            }
        });
    }
}