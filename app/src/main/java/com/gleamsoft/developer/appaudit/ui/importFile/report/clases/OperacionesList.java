package com.gleamsoft.developer.appaudit.ui.importFile.report.clases;

import com.activeandroid.query.Select;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;

import java.util.List;

/**
 * Created by Developer on 25/08/2016.
 */
public class OperacionesList {
public List<DatosArchivo> getAll() {
    return new Select()
                   .from(DatosArchivo.class)
                   //.orderBy("name ASC")
                   .execute();
}
public List<Auditor> getAudit() {
    return new Select()
                   .from(Auditor.class)
                   //.orderBy("name ASC")
                   .execute();
}
}
