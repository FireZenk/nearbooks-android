package com.nearsoft.nearbooks.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.nearsoft.nearbooks.BuildConfig;
import com.nearsoft.nearbooks.NearbooksApplication;
import com.nearsoft.nearbooks.R;
import com.nearsoft.nearbooks.config.Configuration;
import com.nearsoft.nearbooks.models.sqlite.User;
import com.nearsoft.nearbooks.view.helpers.ColorsWrapper;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.fabric.sdk.android.Fabric;

/**
 * Dagger 2 Nearbooks module.
 * Created by epool on 11/17/15.
 */
@Module
public class NearbooksApplicationModule {

    private final NearbooksApplication mNearbooksApplication;

    public NearbooksApplicationModule(NearbooksApplication nearbooksApplication) {
        mNearbooksApplication = nearbooksApplication;

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this.mNearbooksApplication.getApplicationContext());
        } else {
            Fabric.with(this.mNearbooksApplication, new Crashlytics());
        }

        FlowManager.init(this.mNearbooksApplication.getApplicationContext());
    }

    @Provides
    @Singleton
    public Configuration providesConfiguration() {
        return Configuration.getConfiguration(Configuration.DEVELOPMENT);
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mNearbooksApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    public SharedPreferences provideDefaultSharedPreferences() {
        return PreferenceManager
                .getDefaultSharedPreferences(mNearbooksApplication);
    }

    @Provides
    @Singleton
    public ColorsWrapper provideDefaultColorsWrapper() {
        int colorPrimaryDark = ContextCompat.getColor(mNearbooksApplication,
                R.color.colorPrimaryDark);
        int colorPrimary = ContextCompat.getColor(mNearbooksApplication,
                R.color.colorPrimary);
        int whiteColor = ContextCompat.getColor(mNearbooksApplication,
                R.color.white);
        return new ColorsWrapper(colorPrimaryDark, colorPrimary, whiteColor, whiteColor);
    }

    @Provides
    @Singleton
    public User provideUser() {
        return SQLite
                .select()
                .from(User.class)
                .querySingle();
    }

}
