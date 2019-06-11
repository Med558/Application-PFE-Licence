package com.theangel.ericsson.ericsson.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.theangel.ericsson.R;

public class RechargerSoldeOoredooActivity extends AppCompatActivity {

    Button btn_effacer_num_recharger_solde_ooredoo, btn_valider_recharger_solde_ooredoo, btn_back_recharger_solde_ooredoo;
    EditText et_recharger_solde_ooredoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharger_solde_ooredoo);

        et_recharger_solde_ooredoo = (EditText)findViewById(R.id.et_recharger_solde_ooredoo);
        btn_effacer_num_recharger_solde_ooredoo =(Button)findViewById(R.id.btn_effacer_num_recharger_solde_ooredoo);
        btn_valider_recharger_solde_ooredoo =(Button)findViewById(R.id.btn_valider_recharger_solde_ooredoo);
        btn_back_recharger_solde_ooredoo = (Button)findViewById(R.id.btn_back_recharger_solde_ooredoo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#E40613"));
        }

        btn_back_recharger_solde_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_valider_recharger_solde_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_recharger_solde_ooredoo.getText().toString().equals("")||et_recharger_solde_ooredoo.getText().length() < 14){

                        Toast.makeText(getApplicationContext(),"Veuillez saisir les 14 chiffres de votre carte de recharge.",Toast.LENGTH_LONG).show();
                    }else {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_GRANTED ){
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: *101*" + et_recharger_solde_ooredoo.getText().toString() + "%23"));
                        startActivity(intent);
                    } else {
                        ActivityCompat.requestPermissions(RechargerSoldeOoredooActivity.this, new String[] {
                                        Manifest.permission.CALL_PHONE},
                                0);
                    }
                }
            }
        });

        btn_effacer_num_recharger_solde_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_recharger_solde_ooredoo.setText("");
            }
        });
    }
}
