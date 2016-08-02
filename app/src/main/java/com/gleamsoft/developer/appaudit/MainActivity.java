package com.gleamsoft.developer.appaudit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gleamsoft.developer.appaudit.ui.importFile.RegisterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
CheckBox ckAuditIrd;
CheckBox ckAuditVenc;
Button btnExit;
Button btnMainSetup;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ckAuditIrd=(CheckBox) findViewById(R.id.ck_audit_ird);
    ckAuditVenc=(CheckBox) findViewById(R.id.ck_audit_venc);
    btnExit=(Button) findViewById(R.id.btn_exit);
    btnMainSetup=(Button) findViewById(R.id.btn_main_setup);
    btnExit.setOnClickListener(this);
    btnMainSetup.setOnClickListener(this);
    ckAuditIrd.setOnClickListener(this);
    ckAuditVenc.setOnClickListener(this);

}


@Override
public void onClick(View view) {

    if(view==ckAuditVenc){
        DisplayToast("Estamos trabajando en este proceso!");
    }
    if(view==btnExit){
    finish();
    }
    if(view==btnMainSetup){
        if(ckAuditIrd.isChecked())
            startActivity(new Intent(this, RegisterActivity.class));
        else
            DisplayToast("No has seleccionado nnguna opción");

    }
}
private void DisplayToast(String msg) {
    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
}

}
