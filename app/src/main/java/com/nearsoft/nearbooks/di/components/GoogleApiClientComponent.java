package com.nearsoft.nearbooks.di.components;

import com.nearsoft.nearbooks.di.modules.BaseActivityModule;
import com.nearsoft.nearbooks.di.modules.GoogleApiClientModule;
import com.nearsoft.nearbooks.di.scopes.PerActivity;
import com.nearsoft.nearbooks.sync.auth.AuthenticatorActivity;
import com.nearsoft.nearbooks.view.activities.GoogleApiClientBaseActivity;
import com.nearsoft.nearbooks.view.activities.HomeActivity;
import com.nearsoft.nearbooks.view.activities.MainActivity;

import dagger.Component;

/**
 * Dagger 2 Google api client component.
 * Created by epool on 11/17/15.
 */
@PerActivity
@Component(
        dependencies = {NearbooksApplicationComponent.class},
        modules = {BaseActivityModule.class, GoogleApiClientModule.class}
)
public interface GoogleApiClientComponent extends BaseActivityComponent {

    void inject(GoogleApiClientBaseActivity googleApiClientBaseActivity);

    void inject(MainActivity mainActivity);

    void inject(HomeActivity homeActivity);

    void inject(AuthenticatorActivity authenticatorActivity);

}