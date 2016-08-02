package com.gleamsoft.developer.appaudit;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Developer on 1/08/2016.
 */
public class App extends Application {
@Override
public void onCreate() {
    super.onCreate();
    //Initializing Active Android
    ActiveAndroid.initialize(this);
}

}
