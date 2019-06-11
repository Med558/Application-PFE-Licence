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

public class RechargerSoldeTuntelActivity extends AppCompatActivity {

    Button btn_effacer_num_recharger_solde_tuntel, btn_valider_recharger_solde_tuntel, btn_back_recharger_solde_tuntel;
    EditText et_recharger_solde_tuntel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharger_solde_tuntel);

        et_recharger_solde_tuntel = (EditText)findViewById(R.id.et_recharger_solde_tuntel);
        btn_effacer_num_recharger_solde_tuntel =(Button)findViewById(R.id.btn_effacer_num_recharger_solde_tuntel);
        btn_valider_recharger_solde_tuntel =(Button)findViewById(R.id.btn_valider_recharger_solde_tuntel);
        btn_back_recharger_solde_tuntel = (Button)findViewById(R.id.btn_back_recharger_solde_tuntel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#14376B"));
        }

        btn_back_recharger_solde_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_valider_recharger_solde_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_recharger_solde_tuntel.getText().toString().equals("")||et_recharger_solde_tuntel.getText().length() < 14){

                    Toast.makeText(getApplicationContext(),"Veuillez saisir les 14 chiffres de votre carte de recharge.",Toast.LENGTH_LONG).show();
                }else {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_GRANTED ){
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: *123*" + et_recharger_solde_tuntel.getText().toString() + "%23"));
                        startActivity(intent);
                    } else {
                        ActivityCompat.requestPermissions(RechargerSoldeTuntelActivity.this, new String[] {
                                        Manifest.permission.CALL_PHONE},
                                0);
                    }
                }
            }
        });

        btn_effacer_num_recharger_solde_tuntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_recharger_solde_tuntel.setText("");
            }
        });
    }
}
