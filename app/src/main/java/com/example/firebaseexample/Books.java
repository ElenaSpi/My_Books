package com.example.firebaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseexample.data.util.Constants;
import com.google.firebase.auth.FirebaseAuth;

public class Books extends AppCompatActivity {
    //Button game;
    Button rubaiyat, gatsby;
    TextView description, description2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        //game = (Button) findViewById(R.id.btnGame);
        rubaiyat = (Button) findViewById(R.id.btnRub);
        gatsby = (Button) findViewById(R.id.btnGatsby);
        description = (TextView) findViewById(R.id.description);
        description.setText(Constants.gatsby);
        description2 = (TextView) findViewById(R.id.description2);
        description2.setText(Constants.rub);

        gatsby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, TheGreatGatsbyActivity.class);
                startActivity(intent);
            }
        });

        rubaiyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, TheRubaiyatActivity.class);
                startActivity(intent);
            }
        });

        /*
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseBooks.this, GameOfThronesActivity.class);
                startActivity(intent);
            }
        });
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            navigateUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateUser() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}