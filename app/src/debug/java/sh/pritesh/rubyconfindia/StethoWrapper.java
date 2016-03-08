package sh.pritesh.rubyconfindia;

import android.content.Context;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import sh.pritesh.rubyconfindia.confsched.MainApplication;

public class StethoWrapper {

    @Inject
    Context context;

    public StethoWrapper(MainApplication app) {
        app.getComponent().inject(this);
    }

    public void setup() {
        Stetho.initializeWithDefaults(context);
    }

}

