package ada.osc.myfirstweatherapp.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.network.ApiService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Toni on 6/15/2018.
 */

@Module
public class NetworkModule {

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public GsonBuilder provideGsonBuilder(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    }

    @Provides
    public Gson provideGson(GsonBuilder gsonBuilder){
        return gsonBuilder.create();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }


    @Provides
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
