package com.gleamsoft.developer.appaudit.ui.importFile.report;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;

import java.util.ArrayList;
import java.util.List;

public class AuditorsActivity extends AppCompatActivity implements View.OnClickListener {
private ListView listViewAudit;
private ArrayList<String>inventoryItems;
private ArrayAdapter auditItemsAdapter;
Button btnFileImport;
FloatingActionButton floatingActionButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auditors);
    btnFileImport=(Button)findViewById(R.id.btnfiledata);
    listViewAudit = (ListView) findViewById(R.id.listViewAudit);
    floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
    inventoryItems = new ArrayList<>();
    showInventoryList();

    btnFileImport.setOnClickListener(this);
    floatingActionButton.setOnClickListener(this);

}

private List<Auditor> getAll() {
    return new Select()
                   .from(Auditor.class)
                   .orderBy("name ASC")
                   .execute();
}
private void showInventoryList() {
    List<Auditor> auditors = getAll();
    for (int i = 0; i < auditors.size(); i++) {
        Auditor auditor = auditors.get(i);
        inventoryItems.add(auditor.name+"-"+auditor.rut+"-"+auditor.name_qf+
                                   "-"+auditor.rut_qf+"-"+auditor.num_store+"-"+"-"+auditor.date_audit);
    }
    auditItemsAdapter = new ArrayAdapter<String>(this,
                                                            android.R.layout.simple_list_item_1, inventoryItems);


    listViewAudit.setAdapter(auditItemsAdapter);
    updateInventoryList();
}
private void updateInventoryList() {
    auditItemsAdapter.notifyDataSetChanged();
}

@Override
public void onClick(View view) {
    if(view==btnFileImport) {
        startActivity(new Intent(this, FileImportActivity.class));
    }
    if(view==floatingActionButton){
        new Delete().from(DatosArchivo.class).execute(); // all records
        new Delete().from(Auditor.class).execute(); // all records
        Toast.makeText(AuditorsActivity.this, "Registros eliminados", Toast.LENGTH_SHORT).show();
    }
}
}
