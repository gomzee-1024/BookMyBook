package com.bmb.gk.bookmybook.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gautam on 08/04/17.
 */
@Module
public class UserModule {

    @Provides
    @Singleton
    User provideUser(){
        return new User();
    }
}
