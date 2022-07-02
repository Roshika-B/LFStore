package com.roshh.lfstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Intent i1 =new Intent(getApplicationContext(), Database.class);
                startActivity(i1);
                return true;
            case R.id.item2:
                Intent i2 = new Intent(getApplicationContext(), Sqlite.class);
                startActivity(i2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

//            case R.id.item2:
//                Intent i3 = new Intent(getApplicationContext(), Sqlite.class);
//                startActivity(i3);
//                return true;
        }
    }
}