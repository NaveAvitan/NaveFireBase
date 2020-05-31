package com.example.navefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuDelete): {
                Intent s = new Intent(this, menuDelete.class);
                startActivity(s);
                break;
            }
            case (R.id.menuSort): {
                Intent s = new Intent(this, menuSort.class);
                startActivity(s);
                break;
            }
            case (R.id.menuDataIn): {
                Intent s = new Intent(this, MainActivity.class);
                startActivity(s);
                break;
            }
            default:
                break;
        }
        return true;
    }
}

