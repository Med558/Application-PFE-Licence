package com.theangel.ericsson.ericsson.activities;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.CircularProgressButton;
import com.theangel.ericsson.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InscriptionActivity extends Activity {

    private static final String TAG = InscriptionActivity.class.getSimpleName();
    private static final String URL_REGISTER = "https://ericsson.000webhostapp.com/register.php";

    CircularProgressButton circularButton1;

    EditText et_nom, et_prenom, et_telephone, et_confirmer_telephone;
    TextInputEditText et_mot_de_passe, et_confirmer_mot_de_passe;
    Button btn_inscription;
    String nom, prenom, num_tel, mot_de_passe;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        et_nom = (EditText)findViewById(R.id.et_nom);
        et_prenom = (EditText)findViewById(R.id.et_prenom);
        et_telephone = (EditText)findViewById(R.id.et_telephone);
        et_confirmer_telephone = (EditText)findViewById(R.id.et_confirmer_telephone);
        et_mot_de_passe = (TextInputEditText) findViewById(R.id.et_mot_de_passe_inscription);
        et_confirmer_mot_de_passe = (TextInputEditText) findViewById(R.id.et_confirmer_mot_de_passe_inscription);

        //btn_inscription = (Button)findViewById(R.id.btn_inscription);

        circularButton1 = (CircularProgressButton)findViewById(R.id.circularButton1);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(getApplicationContext())) {
                    if(et_nom.getText().toString().equals("") || et_prenom.getText().toString().equals("") || et_telephone.getText().toString().equals("") ||
                            et_confirmer_telephone.getText().toString().equals("") || et_mot_de_passe.getText().toString().equals("") || et_confirmer_mot_de_passe.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                    }else {
                        if(!et_telephone.getText().toString().equals(et_confirmer_telephone.getText().toString())){
                            Toast.makeText(getApplicationContext(),"Veuillez vérifier votre numéro de téléphone.", Toast.LENGTH_LONG).show();
                        }else {
                            if(!et_mot_de_passe.getText().toString().equals(et_confirmer_mot_de_passe.getText().toString())){
                                Toast.makeText(getApplicationContext(),"Veuillez vérifier votre mot de passe.", Toast.LENGTH_LONG).show();
                            }else {
                                nom = et_nom.getText().toString();
                                prenom = et_prenom.getText().toString();
                                num_tel = et_telephone.getText().toString();
                                mot_de_passe = et_mot_de_passe.getText().toString();

                                registerUser(nom, prenom, num_tel, mot_de_passe);


                            }

                        }

                    }
                }else{
                    if (circularButton1.getProgress() == 0) {
                        simulateErrorProgress(circularButton1);
                    } else {
                        circularButton1.setProgress(0);
                    }
                }


            }
        });

//        btn_inscription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(et_nom.getText().toString().equals("") || et_prenom.getText().toString().equals("") || et_telephone.getText().toString().equals("") ||
//                        et_confirmer_telephone.getText().toString().equals("") || et_mot_de_passe.getText().toString().equals("") || et_confirmer_mot_de_passe.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
//                }else {
//                    if(!et_telephone.getText().toString().equals(et_confirmer_telephone.getText().toString())){
//                        Toast.makeText(getApplicationContext(),"Veuillez vérifier votre numéro de téléphone.", Toast.LENGTH_LONG).show();
//                    }else {
//                        if(!et_mot_de_passe.getText().toString().equals(et_confirmer_mot_de_passe.getText().toString())){
//                            Toast.makeText(getApplicationContext(),"Veuillez vérifier votre mot de passe.", Toast.LENGTH_LONG).show();
//                        }else {
//                            nom = et_nom.getText().toString();
//                            prenom = et_prenom.getText().toString();
//                            num_tel = et_telephone.getText().toString();
//                            mot_de_passe = et_mot_de_passe.getText().toString();
//
//                            registerUser(nom, prenom, num_tel, mot_de_passe);
//
//
//                        }
//
//                    }
//
//                }
//
//            }
//        });


    }




    private void registerUser(final String nom, final String prenom, final String num_tel, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                //hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        if (circularButton1.getProgress() == 0) {
                            simulateSuccessProgress(circularButton1);
                        } else {
                            circularButton1.setProgress(0);
                        }
                        Thread timerThread = new Thread(){
                            public void run(){
                                try{
                                    sleep(2000);
//                        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(circleProgress);
//                        animator.setDuration(3000);
//                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//                        animator.start();
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }finally{
                                    //Toast.makeText(getApplicationContext(),
                                      //      "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                                    // Launch login activity
                                    Intent intent = new Intent(InscriptionActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        };
                        timerThread.start();
                    } else {
                        // Error occurred in registration. Get the error
                        // message
                        if (circularButton1.getProgress() == 0) {
                            simulateErrorProgress(circularButton1);
                        } else {
                            circularButton1.setProgress(0);
                        }
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("num_tel", num_tel);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
