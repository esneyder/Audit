package com.gleamsoft.developer.appaudit.ui.importFile.report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.countAudit.UpdateCountActivity;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;
import com.gleamsoft.developer.appaudit.ui.importFile.report.clases.OperacionesList;

import java.util.ArrayList;
import java.util.List;

public class AuditReportAuditActivity extends AppCompatActivity {
private ListView listViewAudit;
private ArrayList<String> inventoryItems;
private ArrayAdapter auditItemsAdapter;
private OperacionesList operaciones;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audit_report_audit);
    operaciones=new OperacionesList();
    listViewAudit = (ListView) findViewById(R.id.listViewAudit);
    inventoryItems = new ArrayList<>();

    listViewAudit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            List<DatosArchivo> file = operaciones.getAll();
            DatosArchivo ar = file.get(i);
            Intent intent=new Intent(getApplicationContext(), UpdateCountActivity.class);
            intent.putExtra("Id",ar.getId());
            intent.putExtra("item",ar.numeration);
            startActivity(intent);
        }
    });
    showInventoryList();
}
private void showInventoryList() {
    List<DatosArchivo> file = operaciones.getAll();
    for (int i = 0; i < file.size(); i++) {
        DatosArchivo ar = file.get(i);
        inventoryItems.add(
                                   ar.numeration+"-"+
                                   ar.sku+"-"+
                                   ar.barcode +"-"+
                                   ar.description+"-"+
                                   ar.laboratory+"-"+
                                   ar.clasification+"-"+
                                   ar.hourcapture+"-"+
                                   ar.initialcount);
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
