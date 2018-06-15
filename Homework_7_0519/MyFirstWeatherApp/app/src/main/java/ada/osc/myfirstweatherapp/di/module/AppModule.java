package ada.osc.myfirstweatherapp.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Toni on 6/5/2018.
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext (){
        return context;
    }

}
