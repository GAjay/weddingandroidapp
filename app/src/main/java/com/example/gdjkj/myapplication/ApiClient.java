package com.example.gdjkj.myapplication;

import com.example.gdjkj.myapplication.model.Response;
import com.example.gdjkj.myapplication.model.request.SendMessageRequest;
import com.example.gdjkj.myapplication.model.request.SendReplyMessage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gdjkj on 11/20/16.
 */

public class ApiClient {

    public static final String BASE_URL = "http://gauravkhatri.com/app/service/";
    public static String errorURL = "http://ranosys.net/client/starrez/";
    private static ApiInterface gitApiInterface;


    public static ApiInterface getApiCall() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(150, TimeUnit.SECONDS);
        okClient.readTimeout(150, TimeUnit.SECONDS);
        okClient.addInterceptor(logging);
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitApiInterface = client.create(ApiInterface.class);
        return gitApiInterface;
    }

    /**
     * Interface hold various method for web service calling.
     */
    public interface ApiInterface {
        /**
         * Method to send the error logs via web service on server.
         *
         * @param sendMessageRequest : message object.
         * @return : resposne body return raw response from server.
         */
        @POST("wish.php")
        Call<ResponseBody> sendMessage(@Body SendMessageRequest sendMessageRequest);

        @GET("allwish.php")
        Call<Response> callback();

        @POST("replywish.php")
        Call<ResponseBody> sendReply(@Body SendReplyMessage sendReplyMessage);


    }
}

