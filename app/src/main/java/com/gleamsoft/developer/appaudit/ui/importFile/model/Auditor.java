package com.gleamsoft.developer.appaudit.ui.importFile.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Developer on 1/08/2016.
 */
@Table(name="Auditor")
public class Auditor extends Model {

    @Column(name = "name")
    public String name;
    @Column(name = "rut")
    public String rut;
    @Column(name = "name_qf")
    public String name_qf;
    @Column(name = "rut_qf")
    public String rut_qf;
    @Column(name = "num_store")
    public int num_store;
    @Column(name = "date_audit")
    public String date_audit;
    @Column(name = "date")
    public String date;

    public Auditor(){
        super();
    }

    public List<Auditor> auditors() {
        return getMany(Auditor.class, "Auditor");
    }



}
