package com.gleamsoft.developer.appaudit.pruebas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gleamsoft.developer.appaudit.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.OnClick;

public class BarcodeActivity extends AppCompatActivity implements View.OnClickListener {
private Button scanBtn;
private TextView formatTxt, contentTxt;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_barcode);

    scanBtn = (Button) findViewById(R.id.scan_button);
    // Se Instancia el Campo de Texto para el nombre del formato de c—digo
    // de barra
    formatTxt = (TextView) findViewById(R.id.scan_format);
    // Se Instancia el Campo de Texto para el contenido del c—digo de barra
    contentTxt = (TextView) findViewById(R.id.scan_content);
    // Se agrega la clase MainActivity.java como Listener del evento click
    // del bot—n de Scan
    scanBtn.setOnClickListener(this);
}

@Override
public void onClick(View view) {
// Se responde al evento click
    if (view.getId() == R.id.scan_button) {
        // Se instancia un objeto de la clase IntentIntegrator
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        // Se procede con el proceso de scaneo
        scanIntegrator.initiateScan();
    }
}
public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    // Se obtiene el resultado del proceso de scaneo y se parsea
    IntentResult scanningResult = IntentIntegrator.parseActivityResult(
            requestCode, resultCode, intent);
    if (scanningResult != null) {
        // Quiere decir que se obtuvo resultado pro lo tanto:
        // Desplegamos en pantalla el contenido del c—digo de barra scaneado
        String scanContent = scanningResult.getContents();
        contentTxt.setText("Contenido: " + scanContent);
        // Desplegamos en pantalla el nombre del formato del c—digo de barra
        // scaneado
        String scanFormat = scanningResult.getFormatName();
        formatTxt.setText("Formato: " + scanFormat);
    } else {
        // Quiere decir que NO se obtuvo resultado
        Toast toast = Toast.makeText(getApplicationContext(),
                "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
}
