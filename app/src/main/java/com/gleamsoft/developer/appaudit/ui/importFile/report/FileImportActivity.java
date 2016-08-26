package com.gleamsoft.developer.appaudit.ui.importFile.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;
import com.gleamsoft.developer.appaudit.ui.importFile.report.clases.OperacionesList;

import java.util.ArrayList;
import java.util.List;

public class FileImportActivity extends AppCompatActivity {
private ListView listViewAudit;
private ArrayList<String> inventoryItems;
private ArrayAdapter auditItemsAdapter;
 private OperacionesList operacionesList;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_file_import);
    listViewAudit = (ListView) findViewById(R.id.listViewimport);
    inventoryItems = new ArrayList<>();
    operacionesList =new OperacionesList();
    showInventoryList();
}


private void showInventoryList() {
    List<DatosArchivo> auditors = operacionesList.getAll();
    for (int i = 0; i < auditors.size(); i++) {
        DatosArchivo auditor = auditors.get(i);
        inventoryItems.add(auditor.numeration
                                   +"|"+auditor.sku+"|"+auditor.barcode
                                   +"|"+auditor.description+
                                   "|"+auditor.laboratory+
                                   "|"+auditor.clasification+
                                   "|"+auditor.hourcapture);

  Log.d("Hora captura",auditor.hourcapture+"");
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

