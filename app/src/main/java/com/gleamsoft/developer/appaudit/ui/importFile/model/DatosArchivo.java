package com.gleamsoft.developer.appaudit.ui.importFile.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;



/**
 * Created by Developer on 1/08/2016.
 */
@Table(name="DatosArchivo")
public class DatosArchivo extends Model  {
  @Column(name = "numeration")
  public String numeration;
  @Column(name = "barcode")
  public String barcode;
  @Column(name = "description")
  public String description;
  @Column(name = "laboratory")
  public String laboratory;
  @Column(name = "clasification")
  public String clasification;
  @Column(name = "auditor",onDelete = Column.ForeignKeyAction.CASCADE)
  public Auditor auditor;


public DatosArchivo(){
    super();
  }

public DatosArchivo(String numeration, String barcode, String description,
                    String laboratory, String clasification, Auditor auditor) {
  this.numeration = numeration;
  this.barcode = barcode;
  this.description = description;
  this.laboratory = laboratory;
  this.clasification = clasification;
  this.auditor = auditor;
}
}
