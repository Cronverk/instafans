package software.appus.insta_fans.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import software.appus.insta_fans.R;
import software.appus.insta_fans.data.entity.MediaEntity;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.net.RESTClient;

public class MainActivity extends AppCompatActivity {

    public static final String TOKEN = "token";

    public static Intent getLAunchInstance(Context context, String token) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(TOKEN, token);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String token = "3208472180.1123882.bbbfa25c7021492199f8e171a3733db4";
        RESTClient.getInstance().getRestApi().getUserMedia(token, 0, 20)
                .enqueue(new Callback<ResponseEntity<List<MediaEntity>>>() {
                    @Override
                    public void onResponse(Call<ResponseEntity<List<MediaEntity>>> call, Response<ResponseEntity<List<MediaEntity>>> response) {
                        if (response.body() != null) {
                            List<MediaEntity> list = response.body().data;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity<List<MediaEntity>>> call, Throwable t) {

                    }
                });
    }
}
