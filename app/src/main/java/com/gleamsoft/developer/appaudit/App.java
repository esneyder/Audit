package com.gleamsoft.developer.appaudit;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.app.Application;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;

/**
 * Created by Developer on 1/08/2016.
 */
public class App extends Application {
@Override
public void onCreate() {
    super.onCreate();
    Configuration.Builder config = new Configuration.Builder(this);
    config.addModelClasses(Auditor.class, DatosArchivo.class);
    ActiveAndroid.initialize(config.create());
}

}
