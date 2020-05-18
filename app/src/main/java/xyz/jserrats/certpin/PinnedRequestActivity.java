package xyz.jserrats.certpin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PinnedRequestActivity extends AppCompatActivity {

    TextView txtString;
    public String hostname = "swapi.dev";
    public String url = "https://swapi.dev/api/people/3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain_request);
        txtString = (TextView) findViewById(R.id.txtString);

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {

        //OkHttpClient client = new OkHttpClient();

        CertificatePinner certpin = new CertificatePinner.Builder()
                .add(hostname, "sha256/eSiyNwaNIbIkI94wfLFmhq8/ATxm30i973pMZ669tZo=")
                .build();

        OkHttpClient client = new OkHttpClient.Builder().certificatePinner(certpin)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("DEBUG", "Request Failed");
                call.cancel();
                PinnedRequestActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtString.setText("Request failed :(");
                    }
                });
                Log.e("ERROR", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Log.d("DEBUG", myResponse);
                PinnedRequestActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtString.setText(myResponse);
                    }
                });

            }
        });
    }
}
