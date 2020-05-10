package xyz.jserrats.certpin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the Send button
     */
    public void makePlainRequest(View view) {
        Log.d("MainActivity", "Sending intent to PlainRequestActivity");
        Intent intent = new Intent(this, PlainRequestActivity.class);
        startActivity(intent);
    }

    public void makePinnedRequest(View view) {
        // Do something in response to button
    }
}
