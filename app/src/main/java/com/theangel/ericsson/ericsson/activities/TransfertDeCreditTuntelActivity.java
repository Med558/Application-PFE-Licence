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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.theangel.ericsson.R;

public class TransfertDeCreditTuntelActivity extends AppCompatActivity {

    Button btn_effacer_numero_transfert_de_credit_tuntel, btn_effacer_montant_transfert_de_credit_tuntel, btn_valider_transfert_de_credit_tuntel, btn_back_transfert_de_credit_tuntel;
    EditText et_numero_transfert_de_credit_tuntel, et_montant_transfert_de_credit_tuntel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfert_de_credit_tuntel);

        btn_effacer_numero_transfert_de_credit_tuntel = (Button)findViewById(R.id.btn_effacer_numero_transfert_de_credit_tuntel);
        btn_effacer_montant_transfert_de_credit_tuntel = (Button)findViewById(R.id.btn_effacer_montant_transfert_de_credit_tuntel);
        btn_valider_transfert_de_credit_tuntel = (Button)findViewById(R.id.btn_valider_transfert_de_credit_tuntel);
        btn_back_transfert_de_credit_tuntel = (Button)findViewById(R.id.btn_back_transfert_de_credit_tuntel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#14376B"));
        }

        et_numero_transfert_de_credit_tuntel = (EditText) findViewById(R.id.et_numero_transfert_de_credit_tuntel);
        et_montant_transfert_de_credit_tuntel = (EditText)findViewById(R.id.et_montant_transfert_de_credit_tuntel);

        btn_effacer_numero_transfert_de_credit_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_numero_transfert_de_credit_tuntel.setText("");
            }
        });

        btn_effacer_montant_transfert_de_credit_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_montant_transfert_de_credit_tuntel.setText("");
            }
        });

        btn_valider_transfert_de_credit_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_numero_transfert_de_credit_tuntel.getText().toString().equals("")||et_numero_transfert_de_credit_tuntel.getText().length() < 8){

                    Toast.makeText(getApplicationContext(),"Veuillez saisir un numéro de téléphone Tunisie Télécom valide.",Toast.LENGTH_LONG).show();
                }else if(et_montant_transfert_de_credit_tuntel.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir un montant.",Toast.LENGTH_LONG).show();
                }else {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_GRANTED ){
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: *133*" + et_montant_transfert_de_credit_tuntel.getText().toString() + "*" + et_numero_transfert_de_credit_tuntel.getText().toString() + "%23"));
                        startActivity(intent);
                    } else {
                        ActivityCompat.requestPermissions(TransfertDeCreditTuntelActivity.this, new String[] {
                                        Manifest.permission.CALL_PHONE},
                                0);
                    }
                }
            }
        });

        btn_back_transfert_de_credit_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
