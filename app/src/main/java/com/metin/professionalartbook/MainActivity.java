package com.metin.professionalartbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_art, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_art){
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ArrayList<String> artNameList = new ArrayList<>();
        ArrayList<Bitmap> artImageList = new ArrayList<>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, artNameList);
        listView.setAdapter(arrayAdapter);

        String Url = "content://com.metin.professionalartbook.ArtContentProvider";
        Uri artUri = Uri.parse(Url);

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(artUri, null, null, null, "name");

        if (cursor != null){

            while (cursor.moveToNext()){

                artNameList.add(cursor.getString(cursor.getColumnIndex(ArtContentProvider.NAME)));
                byte[] bytes = cursor.getBlob(cursor.getColumnIndex(ArtContentProvider.IMAGE));
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                artImageList.add(bitmap);

                arrayAdapter.notifyDataSetChanged();

            }
        }

    }
}










