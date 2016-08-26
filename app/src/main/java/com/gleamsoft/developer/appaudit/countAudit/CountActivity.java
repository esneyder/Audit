package com.gleamsoft.developer.appaudit.countAudit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Cache;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.countAudit.clases.Operaciones;
import com.gleamsoft.developer.appaudit.ui.importFile.RegisterActivity;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;

import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;
import com.gleamsoft.developer.appaudit.ui.importFile.report.AuditorsActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountActivity extends AppCompatActivity implements View.OnClickListener{
private Operaciones operaciones;
TextView txtId;
TextView txttiendaC;
TextView txtitemC;
TextView txtfechaC;
EditText txtskuC;
EditText txtdescripcionC;
EditText txtlaboratorioC;
EditText txtclasificacionC;
EditText txteanC;
EditText txtcodigoentradaC;
EditText txtcontenoC;
EditText txtnumingresosC;
Button btnExitC;
Button btnNextC;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_count);
    operaciones=new Operaciones();
    txttiendaC=(TextView) findViewById(R.id.txttiendaC);
    txtitemC=(TextView) findViewById(R.id.txtitemC);
    txteanC=(EditText) findViewById(R.id.txteanC);
    txtfechaC=(TextView) findViewById(R.id.txtfechaC);
    txtskuC=(EditText) findViewById(R.id.txtskuC);
    txtdescripcionC=(EditText) findViewById(R.id.txtdescripcionC);
    txtlaboratorioC=(EditText) findViewById(R.id.txtlaboratorioC);
    txtclasificacionC=(EditText) findViewById(R.id.txtclasificacionC);
    txtcodigoentradaC=(EditText) findViewById(R.id.txtcodigoentradaC);
    txtcontenoC=(EditText) findViewById(R.id.txtcontenoC);
    btnExitC=(Button) findViewById(R.id.btn_exit_c);
    txtnumingresosC=(EditText) findViewById(R.id.txtnumingresos);
    txtId=(TextView)findViewById(R.id.txtId);
    btnExitC.setOnClickListener(this);
    btnNextC=(Button) findViewById(R.id.btn_next_c);
    btnNextC.setOnClickListener(this);
    ValidateData("7804920015549");
}

public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    IntentResult scanningResult = IntentIntegrator.parseActivityResult(
            requestCode, resultCode, intent);
    if (scanningResult != null) {
        String scanContent = scanningResult.getContents();
        String scanFormat = scanningResult.getFormatName();
      ValidateData(scanContent);
    }
}
private void  ValidateData( String barcode){
    List<DatosArchivo> auditors = operaciones.get(barcode);
   if(auditors.size()==0)
   {
       mgs(R.string.txt_search_null);
   }else{
       for (int i = 0; i < auditors.size(); i++) {
       DatosArchivo auditor = auditors.get(i);
       txtId.setText(""+auditor.getId());
       txtitemC.setText(auditor.numeration + "");
       txtskuC.setText(auditor.sku + "");
       txtdescripcionC.setText(auditor.description + "");
       txtlaboratorioC.setText(auditor.laboratory + "");
       txtclasificacionC.setText(auditor.clasification + "");
       txteanC.setText(auditor.barcode);
       txtcontenoC.setText(auditor.initialcount);
       txtnumingresosC.setText(auditor.num_income+"");
       txttiendaC.setText(auditor.auditor.num_store + "");
       txtfechaC.setText(auditor.auditor.date + "");
   }
       disableEditText(txtcontenoC);
       disableEditText(txtnumingresosC);
       disableEditText(txtskuC);
       disableEditText(txtdescripcionC);
       disableEditText(txtlaboratorioC);
       disableEditText(txtclasificacionC);
       disableEditText(txteanC);
       disableButton(btnNextC);
   }

}


private void mgs(int s) {
    Toast.makeText(CountActivity.this, s, Toast.LENGTH_SHORT).show();
}

public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.bardcode, menu);
    return true;
}
private void setupCamera() {
    IntentIntegrator scanIntegrator = new IntentIntegrator(this);
    scanIntegrator.initiateScan();
}


public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.barcodeC:
            setupCamera();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}


@Override
public void onClick(View view) {
    if( view==btnExitC){
        startActivity(new Intent(this,AuditorsActivity.class));
    }
    if(view==btnNextC){
    operaciones.updateCount(txtcontenoC.getText().toString(),
            txtnumingresosC.getText().toString(),
            Long.parseLong(txtId.getText().toString()));
        mgs(R.string.mgs_count_update);
        inputClear();
    }

}

private void inputClear() {
    txtId.setText("");
    txtitemC.setText("");
    txtskuC.setText("");
    txtdescripcionC.setText("");
    txtlaboratorioC.setText("");
    txtclasificacionC.setText("");
    txteanC.setText("");
    txtcontenoC.setText("");
    txtnumingresosC.setText("");
    txttiendaC.setText("");
    txtfechaC.setText("");
}
private void disableEditText(EditText editText) {
    editText.setFocusable(false);
    editText.setEnabled(false);
    editText.setCursorVisible(false);
    editText.setKeyListener(null);
    editText.setBackgroundColor(Color.TRANSPARENT);
}
private void disableButton(Button button) {
    button.setFocusable(false);
    button.setEnabled(false);
    button.setCursorVisible(false);
    button.setKeyListener(null);
    button.setBackgroundColor(Color.TRANSPARENT);
}
}
