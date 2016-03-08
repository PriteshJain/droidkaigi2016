package sh.pritesh.rubyconfindia.confsched.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import sh.pritesh.rubyconfindia.confsched.MainApplication;
import sh.pritesh.rubyconfindia.confsched.di.ActivityComponent;
import sh.pritesh.rubyconfindia.confsched.di.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @NonNull
    public ActivityComponent getComponent() {
        if (activityComponent == null) {
            MainApplication mainApplication = (MainApplication) getApplication();
            activityComponent = mainApplication.getComponent().plus(new ActivityModule(this));
        }
        return activityComponent;
    }
}
