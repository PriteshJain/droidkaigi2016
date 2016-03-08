package sh.pritesh.rubyconfindia.confsched;

import android.app.Application;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;
import com.jakewharton.threetenabp.AndroidThreeTen;

import io.fabric.sdk.android.Fabric;
import sh.pritesh.rubyconfindia.StethoWrapper;
import sh.pritesh.rubyconfindia.confsched.di.AppComponent;
import sh.pritesh.rubyconfindia.confsched.di.AppModule;
import sh.pritesh.rubyconfindia.confsched.di.DaggerAppComponent;
import sh.pritesh.rubyconfindia.confsched.util.LocaleUtil;

public class MainApplication extends Application {

    AppComponent appComponent;

    @NonNull
    public AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        Fabric.with(this, new Crashlytics());

        new StethoWrapper(this).setup();

        AndroidThreeTen.init(this);

        LocaleUtil.initLocale(this);
    }

}
