package com.gleamsoft.developer.appaudit.countAudit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.countAudit.adapter.CountAdapter;
import com.gleamsoft.developer.appaudit.countAudit.clases.Operaciones;
import com.gleamsoft.developer.appaudit.countAudit.model.CountData;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UpdateCountActivity extends AppCompatActivity {
private List<CountData> countList = new ArrayList<>();
private RecyclerView recyclerView;
private CountAdapter mAdapter;
private TextView tienda;
private EditText item;
private TextView sku;
private TextView fecha;
private TextView descriptor;
private TextView laboratorio;
Operaciones operacions;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_count);
    tienda=(TextView)  findViewById(R.id.txttiendaEdit);
    item=(EditText)  findViewById(R.id.tdtitemEdit);
    fecha=(TextView)  findViewById(R.id.txtfechaEdit);
    sku=(TextView)  findViewById(R.id.txtstkEdit);
    descriptor=(TextView)  findViewById(R.id.txtdescripcionEdit);
    laboratorio=(TextView)  findViewById(R.id.txtlaboratorioEdit);
    operacions=new Operaciones();
    Bundle extras = getIntent().getExtras();
    String item = null;

    if (extras != null) {
        item = extras.getString("item");
        // and get whatever type
        Toast.makeText(UpdateCountActivity.this, "Item " + item, Toast.LENGTH_SHORT).show();
        prepareCountData(item);

    }
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    
    mAdapter = new CountAdapter(countList,this);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    recyclerView.setAdapter(mAdapter);

    
}

private void prepareCountData(String item) {
    DatosArchivo a=new DatosArchivo();
    List<DatosArchivo> file = operacions.getAll(item);
    for (int i = 0; i < file.size(); i++) {
        DatosArchivo ar = file.get(i);
        a=ar;
        CountData cd=new CountData(ar.getId(),ar.barcode,ar.initialcount);
        countList.add(cd);
        Log.d("Proceso",ar.initialcount +"-"+ ar.finalcountdown +"-"+ar.adjustment);
    }

//    mAdapter.notifyDataSetChanged();
    prepareDataInput(a);
}

private void prepareDataInput(DatosArchivo ar) {
    tienda.setText(ar.auditor.num_store+"");
    item.setText(ar.numeration+"");
    fecha.setText(ar.auditor.date_audit+"");
    sku.setText(ar.sku+"");
    descriptor.setText(ar.description+"");
    laboratorio.setText(ar.laboratory+"");
}
}
