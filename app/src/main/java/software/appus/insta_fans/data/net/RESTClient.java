package software.appus.insta_fans.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {

    private final Retrofit retrofit;
    private final OkHttpClient client;
    private static RESTClient INSTANCE;

    private final static String BASE_URL = "https://api.instagram.com/";

    public static RESTClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RESTClient();
        }
        ;
        return INSTANCE;
    }

    private RESTClient() {
        Gson gson = new GsonBuilder()
                .create();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                })
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    public RestApi getRestApi() {
        return retrofit.create(RestApi.class);
    }
}