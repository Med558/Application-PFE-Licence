package com.theangel.ericsson.ericsson.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.theangel.ericsson.R;

public class DetailsMeteoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meteo);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        float temperature = prefs.getFloat("temperature", -1);

        TextView tvTemperature = (TextView) findViewById(R.id.tvTemperature);
        tvTemperature.setText(temperature + "°");

        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 15);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View template = inflater.inflate(R.layout.toast_layout, null, false);

        TextView tvText = (TextView) template.findViewById(R.id.textMeteo);
        tvText.setText("Il fait " + temperature + "°");

        toast.setView(template);
        toast.show();
    }
}
