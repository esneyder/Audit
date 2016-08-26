package com.gleamsoft.developer.appaudit.countAudit.model;

/**
 * Created by Developer on 26/08/2016.
 */
public class CountData {
private Long Id;
private String Barcode;
private String count;

public CountData(Long id, String barcode, String count) {
    Id = id;
    Barcode = barcode;
    this.count = count;
}

public Long getId() {
    return Id;
}

public void setId(Long id) {
    Id = id;
}

public String getBarcode() {
    return Barcode;
}

public void setBarcode(String barcode) {
    Barcode = barcode;
}

public String getCount() {
    return count;
}

public void setCount(String count) {
    this.count = count;
}
}
