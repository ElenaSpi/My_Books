package com.example.firebaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;

public class TheGreatGatsbyActivity extends AppCompatActivity {
    PDFView The_Great_Gatsby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_great_gatsby);

        The_Great_Gatsby = (PDFView) findViewById(R.id.gatsbyView);
        The_Great_Gatsby.fromAsset("The Great Gatsby.pdf").load();
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