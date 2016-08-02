package com.gleamsoft.developer.appaudit.ui.importFile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.gleamsoft.developer.appaudit.R;
import com.gleamsoft.developer.appaudit.ui.importFile.model.Auditor;
import com.gleamsoft.developer.appaudit.ui.importFile.model.DatosArchivo;
import com.gleamsoft.developer.appaudit.ui.importFile.report.AuditorsActivity;
import com.gleamsoft.developer.appaudit.util.Config;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
private static final int ABRIRFICHERO_RESULT_CODE = 1;
static EditText txtDate;
static EditText txtname;
static EditText txtrut;
static EditText txtnameqf;
static EditText txtrutqf;
static EditText txtnumstore;
Button btnImport;
Button btnSave;
Button list;
Button btnclean;
TextView txtRuteFile;
Toolbar toolbar;
TextInputLayout inputLayoutName;
TextInputLayout inputLayoutRut;
TextInputLayout inputLayoutNameQf;
TextInputLayout inputLayoutRutQf;
TextInputLayout inputLayoutNumStore;
TextInputLayout inputLayoutDate;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);

    toolbar=(Toolbar) findViewById(R.id.toolbar);
    inputLayoutName=(TextInputLayout) findViewById(R.id.input_layout_name);
    inputLayoutRut=(TextInputLayout) findViewById(R.id.input_layout_rut);
    inputLayoutNameQf=(TextInputLayout) findViewById(R.id.input_layout_name_qf);
    inputLayoutRutQf=(TextInputLayout) findViewById(R.id.input_layout_rut_qf);
    inputLayoutNumStore=(TextInputLayout) findViewById(R.id.input_layout_name);
    inputLayoutDate=(TextInputLayout) findViewById(R.id.input_layout_date);

    txtRuteFile = (TextView) findViewById(R.id.txt_rute_file);
    txtDate = (EditText) findViewById(R.id.input_date);
    txtname = (EditText) findViewById(R.id.input_name);
    txtrut = (EditText) findViewById(R.id.input_rut);
    txtnameqf = (EditText) findViewById(R.id.input_name_qf);
    txtrutqf = (EditText) findViewById(R.id.input_rut_qf);
    txtnumstore = (EditText) findViewById(R.id.input_num_store);
    btnImport = (Button) findViewById(R.id.btnImport);
    btnSave = (Button) findViewById(R.id.btn_save);
    list = (Button) findViewById(R.id.btnlist);
    btnclean=(Button)findViewById(R.id.btn_clean);
    setSupportActionBar(toolbar);

    btnImport.setOnClickListener(this);
    txtDate.setOnClickListener(this);
    btnSave.setOnClickListener(this);
    list.setOnClickListener(this);


}

@Override
public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
    txtDate.setText(date);
}

@Override
public void onClick(View id) {
    if (id == txtDate) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                RegisterActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
    if (id == btnImport) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, ABRIRFICHERO_RESULT_CODE);

    }
    if (id == btnSave) {
        submitForm();
    }
    if (id == list) {
        startActivity(new Intent(this, AuditorsActivity.class));
    }
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
        case ABRIRFICHERO_RESULT_CODE:
            if (resultCode == RESULT_OK) {
                String ruta = data.getData().getPath();
                txtRuteFile.setText(ruta);
            }
    }
}

public static void readFromFile(Context contect, String nameFile) {
    String line = "";
    BufferedReader myReader;
    try {
        File myFile = new File(nameFile);
        FileInputStream fIn;
        fIn = new FileInputStream(myFile);
        myReader = new BufferedReader(new InputStreamReader(
                                                                   fIn));
        line = myReader.readLine();
        Auditor auditor = new Auditor();
        auditor.name = txtname.getText().toString();
        auditor.rut = txtrut.getText().toString();
        auditor.name_qf = txtnameqf.getText().toString();
        auditor.rut_qf = txtrutqf.getText().toString();
        auditor.num_store = Integer.parseInt(txtnumstore.getText().toString());
        auditor.date_audit = txtDate.getText().toString();
        auditor.date = Config.getDate();
        auditor.save();
        while (line != null) {
            save(line, auditor);
            line = myReader.readLine();
        }
        myReader.close();
    } catch (FileNotFoundException e) {

        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}

private static void save(String datafle, Auditor auditor) {
    String d = datafle.replace("|", ",");
    String[] arrayData = d.split(",");
    DatosArchivo datosArchivo = new DatosArchivo();
    for (int i = 0; i < arrayData.length; i++) {
        //System.out.println(arrayData[0]);
        datosArchivo.numeration = arrayData[0];
        datosArchivo.barcode = arrayData[1];
        datosArchivo.description = arrayData[2];
        datosArchivo.laboratory = arrayData[3];
        datosArchivo.clasification = arrayData[4];
        datosArchivo.auditor = auditor;
        datosArchivo.save();
    }


}
private void submitForm() {
    if (!validateName()) {
        return;
    }
    if (!validateRut()) {
        return;
    }
    if (!validateNameQF()) {
        return;
    }
    if (!validateRutQF()) {
        return;
    }
    if (!validateNumStore()) {
        return;
    }
    if (!validateDateAudit()) {
        return;
    }
    if(txtRuteFile.getText().toString().equals("")){
        DisplayToast("Debe cargar el archivo con los datos");
    }else {
        readFromFile(this, txtRuteFile.getText().toString());
    DisplayToast("Registros guardados correctamente!.");
    }
}
private boolean validateName() {
    if (txtname.getText().toString().trim().isEmpty()) {
        inputLayoutName.setError(getString(R.string.err_msg_name));
        requestFocus(txtname);
        return false;
    } else {
        inputLayoutName.setErrorEnabled(false);
    }

    return true;
}
private boolean validateRut() {
    if (txtrut.getText().toString().trim().isEmpty()) {
        inputLayoutRut.setError(getString(R.string.err_msg_rut));
        requestFocus(txtrut);
        return false;
    } else {
        inputLayoutRut.setErrorEnabled(false);
    }

    return true;
}

private boolean validateNameQF() {
    if (txtnameqf.getText().toString().trim().isEmpty()) {
        inputLayoutNameQf.setError(getString(R.string.err_msg_name_qf));
        requestFocus(txtnameqf);
        return false;
    } else {
        inputLayoutNameQf.setErrorEnabled(false);
    }

    return true;
}
private boolean validateRutQF() {
    if (txtrutqf.getText().toString().trim().isEmpty()) {
        inputLayoutRutQf.setError(getString(R.string.err_msg_rut_qf));
        requestFocus(txtrutqf);
        return false;
    } else {
        inputLayoutRutQf.setErrorEnabled(false);
    }

    return true;
}
private boolean validateNumStore() {
    if (txtnumstore.getText().toString().trim().isEmpty()) {
        inputLayoutNumStore.setError(getString(R.string.err_msg_num_store));
        requestFocus(txtnumstore);
        return false;
    } else {
        inputLayoutNumStore.setErrorEnabled(false);
    }

    return true;
}
private boolean validateDateAudit() {
    if (txtDate.getText().toString().trim().isEmpty()) {
        inputLayoutNumStore.setError(getString(R.string.err_msg_date_audit));
        requestFocus(txtDate);
        return false;
    } else {
        inputLayoutNumStore.setErrorEnabled(false);
    }

    return true;
}

private void requestFocus(View view) {
    if (view.requestFocus()) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}

private void DisplayToast(String msg) {
    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
}

}
