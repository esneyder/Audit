package com.gleamsoft.developer.appaudit.ui.importFile.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;

import java.util.ArrayList;
import java.util.List;

public class FileImportActivity extends AppCompatActivity {
private ListView listViewAudit;
private ArrayList<String> inventoryItems;
private ArrayAdapter auditItemsAdapter;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_file_import);
    listViewAudit = (ListView) findViewById(R.id.listViewimport);
    inventoryItems = new ArrayList<>();
    showInventoryList();
}

private List<DatosArchivo> getAll() {
    return new Select()
                   .from(DatosArchivo.class)
                   //.orderBy("name ASC")
                   .execute();
}
private void showInventoryList() {
    List<DatosArchivo> auditors = getAll();
    for (int i = 0; i < auditors.size(); i++) {
        DatosArchivo auditor = auditors.get(i);
        inventoryItems.add(auditor.numeration+"|"+auditor.barcode
                                   +"|"+auditor.description+
                                   "|"+auditor.laboratory+
                                   "|"+auditor.clasification);
    }
    auditItemsAdapter = new ArrayAdapter<String>(this,
                                                        android.R.layout.simple_list_item_1, inventoryItems);


    listViewAudit.setAdapter(auditItemsAdapter);
    updateInventoryList();
}
private void updateInventoryList() {
    auditItemsAdapter.notifyDataSetChanged();
}

}

