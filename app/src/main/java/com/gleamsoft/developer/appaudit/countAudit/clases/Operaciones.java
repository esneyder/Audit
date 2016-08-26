package com.gleamsoft.developer.appaudit.countAudit.clases;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;
import com.gleamsoft.developer.appaudit.util.Config;

import java.util.List;

/**
 * Created by Developer on 25/08/2016.
 */
public class Operaciones {

public List<DatosArchivo> getAll(String item) {
    return new Select()
                   .from(DatosArchivo.class)
                   .orderBy("hourcapture ASC")
                   .where("numeration = ?" ,item)
                   .execute();
}
public List<DatosArchivo> get(String barcode) {
    return new Select()
                   .from(DatosArchivo.class)
                   .where("barcode = ?" ,barcode)
           .execute();
}

public static boolean isExists(String id) {
    DatosArchivo item = new Select().from(DatosArchivo.class).where("barcode = ?", "7804920015549").executeSingle();
    if (item == null) {
        return false;
    } else {
        return true;
    }
}
public void updateCount(String inicount,String numincome,long IdUpdate){
    String time=Config.getDate()+":"+Config.getMinute();
    String updateSet = " modificationtime = ? ," +
                               " initialcount = ? ,"+
                               " num_income = ? ";
    new Update(DatosArchivo.class)
            .set(updateSet, time, inicount,numincome)
            .where(" _id = ? ", IdUpdate)
            .execute();

}

public List<DatosArchivo> getId(long id) {
    return new Select()
                   .from(DatosArchivo.class)
                   .where(" _id = ?" ,id)
                   .execute();
}

public void finalCountdown(String id, String count,String initialcount) {
    String time=Config.getDate()+":"+Config.getMinute();
    int ajust=Integer.parseInt(count) - Integer.parseInt(initialcount);
    String updateSet =
            " finalcountdown = ? ," +
            " modificationtime = ? ,"+
            " adjustment = ? ";
    new Update(DatosArchivo.class)
            .set(updateSet, count, time,ajust)
            .where(" _id = ? ", id)
            .execute();
}
}
