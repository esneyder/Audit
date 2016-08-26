package com.gleamsoft.developer.appaudit.ui.importFile.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;



/**
 * Created by Developer on 1/08/2016.
 */
@Table(name="DatosArchivo",id = "_id")
public class DatosArchivo extends Model  {
  @Column(name = "id")
  private long id;
  @Column(name = "numeration")
  public String numeration;
  @Column(name = "sku")
  public String sku;
  @Column(name = "barcode")
  public String barcode;
  @Column(name = "description")
  public String description;
  @Column(name = "laboratory")
  public String laboratory;
  @Column(name = "clasification")
  public String clasification;
  @Column(name = "hourcapture")
  public  String hourcapture;
  @Column(name = "modificationtime")
  public  String modificationtime;
  @Column(name = "initialcount")
  public  String initialcount;
  @Column(name = "finalcountdown")
  public  String finalcountdown;
  @Column(name = "num_income")
  public  String num_income;
  @Column(name = "adjustment")
  public  String adjustment;
  @Column(name = "auditor",onDelete = Column.ForeignKeyAction.CASCADE)
  public Auditor auditor;


public DatosArchivo(){
    super();
  }

public DatosArchivo(long id, String numeration, String sku, String barcode, String description, String laboratory, String clasification, String hourcapture, String modificationtime, String initialcount, String finalcountdown, String num_income, String adjustment, Auditor auditor) {
  this.id = id;
  this.numeration = numeration;
  this.sku = sku;
  this.barcode = barcode;
  this.description = description;
  this.laboratory = laboratory;
  this.clasification = clasification;
  this.hourcapture = hourcapture;
  this.modificationtime = modificationtime;
  this.initialcount = initialcount;
  this.finalcountdown = finalcountdown;
  this.num_income = num_income;
  this.adjustment = adjustment;
  this.auditor = auditor;
}
}
