package com.theangel.ericsson.ericsson.activities;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.CircularProgressButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.utils.ProgressGenerator;

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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class LoginActivity extends Activity {

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    Button btn_connexion, btn_sinscrire;
    TextView tv_ignorer_etape;
    EditText et_login;
    TextInputEditText et_mot_de_passe;
    String nom, prenom, numtel, password000, value_login, value_mot_de_passe;
    final Context context = this;
    private ProgressDialog pDialog;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String URL_LOGIN = "https://ericsson.000webhostapp.com/login.php";

    SharedPreferences sp1;
    SharedPreferences.Editor spe;

    CheckBox checkbox_login;

    CircularProgressButton circularButton1_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //btn_connexion = (Button)findViewById(R.id.btn_connexion);
        btn_sinscrire = (Button)findViewById(R.id.btn_sinscrire);

        tv_ignorer_etape = (TextView) findViewById(R.id.tv_ignorer_etape);

        et_login = (EditText)findViewById(R.id.et_login);
        et_mot_de_passe = (TextInputEditText) findViewById(R.id.et_mot_de_passe_login);

        checkbox_login = (CheckBox) findViewById(R.id.checkbox_login);
        checkbox_login.setChecked(false);
        sp1 = getSharedPreferences("SP1",MODE_PRIVATE);
        value_login = sp1.getString("login","");
        value_mot_de_passe = sp1.getString("mot_de_passe","");
        if (value_login.equals("") || value_mot_de_passe.equals("")) {
            // the key does not exist
        } else {
            //try {
            // handle the value
            //relativeLayout = (RelativeLayout)findViewById(R.id.activity_sign_in);
            //relativeLayout.setVisibility(View.GONE);
            et_login.setText(sp1.getString("login",""));
            et_mot_de_passe.setText(sp1.getString("mot_de_passe",""));
            checkbox_login.setChecked(true);
            //checkBox.setChecked(true);
            //certif();
//            } catch (CertificateException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (KeyStoreException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (KeyManagementException e) {
//                e.printStackTrace();
//            }


        }


        circularButton1_login = (CircularProgressButton)findViewById(R.id.circularButton1_login);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        circularButton1_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(context)) {
                if(et_login.getText().toString().equals("") || et_mot_de_passe.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                }else {
                    String firstLetter = String.valueOf(et_login.getText().toString().charAt(0));
                    if(et_login.getText().length() < 8 ){
                        Toast.makeText(getApplicationContext(),"Veuillez saisir 8 chiffres.",Toast.LENGTH_LONG).show();
                    }else {

                            String num_tel = et_login.getText().toString();
                            String password = et_mot_de_passe.getText().toString();
                            circularButton1_login.setEnabled(false);
                            et_login.setEnabled(false);
                            et_mot_de_passe.setEnabled(false);

                        if(checkbox_login.isChecked()){
                            sp1 = getSharedPreferences("SP1",MODE_PRIVATE);
                            spe = sp1.edit();
                            spe.putString("login",et_login.getText().toString());
                            spe.putString("mot_de_passe",et_mot_de_passe.getText().toString());
                            spe.commit();
                        }else{
                            sp1 = getSharedPreferences("SP1",MODE_PRIVATE);
                            spe = sp1.edit();
                            spe.putString("login","");
                            spe.putString("mot_de_passe","");
                            spe.commit();
                        }


                            loginProcess(num_tel, password);

                    }

                }
                }else {
                    if (circularButton1_login.getProgress() == 0) {
                        simulateErrorProgress(circularButton1_login);
                    } else {
                        circularButton1_login.setProgress(0);
                    }
                    Toast.makeText(getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                }

            }
        });


        btn_sinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable(context)) {
                    Intent intent = new Intent(LoginActivity.this, InscriptionActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                }

            }
        });

//        btn_connexion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(et_login.getText().toString().equals("") || et_mot_de_passe.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
//                }else {
//                    String firstLetter = String.valueOf(et_login.getText().toString().charAt(0));
//                    if(et_login.getText().length() < 8 ){
//                        Toast.makeText(getApplicationContext(),"Veuillez saisir 8 chiffres.",Toast.LENGTH_LONG).show();
//                    }else {
//                        if (isNetworkAvailable(context)) {
//                            String num_tel = et_login.getText().toString();
//                            String password = et_mot_de_passe.getText().toString();
//
//                            loginProcess(num_tel, password);
//                        }else {
//                            Toast.makeText(getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
//                        }
//
////                        if (firstLetter.equals("2") || firstLetter.equals("5") || firstLetter.equals("9")) {
////                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
////                            //intent.putExtra("nom", nom);
////                            //intent.putExtra("prenom", prenom);
////                            intent.putExtra("numtel", et_login.getText().toString());
////                            intent.putExtra("non_connecte", "");
////                            startActivity(intent);
////                            LoginActivity.this.finish();
////                        } else {
////                            Toast.makeText(getApplicationContext(), "Veuillez saisir un numéro valide.", Toast.LENGTH_LONG).show();
////                        }
//
//
//                    }
//
//                }
//            }
//        });

        tv_ignorer_etape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                //intent.putExtra("nom", nom);
                //intent.putExtra("prenom", prenom);
                intent.putExtra("numtel", "");
                intent.putExtra("non_connecte", "non_connecte");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // your code.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(R.string.quitter);

        // set dialog message
        alertDialogBuilder

                .setCancelable(true)
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    private void loginProcess(final String num_tel, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Authentification...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                //hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        JSONObject user = jObj.getJSONObject("user");
                        nom = user.getString("nom");
                        prenom = user.getString("prenom");
                        numtel = user.getString("num_tel");
                        password000 = user.getString("password");

                        if (circularButton1_login.getProgress() == 0) {
                            simulateSuccessProgress(circularButton1_login);
                        } else {
                            circularButton1_login.setProgress(0);
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
                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                    intent.putExtra("nom", nom);
                                    intent.putExtra("prenom", prenom);
                                    intent.putExtra("numtel", numtel);
                                    intent.putExtra("non_connecte", "");
                                    //Toast.makeText(getApplicationContext(),password000,Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        };
                        timerThread.start();




                    } else {
                        // Error in login. Get the error message
                        if (circularButton1_login.getProgress() == 0) {
                            simulateErrorProgress(circularButton1_login);
                        } else {
                            circularButton1_login.setProgress(0);
                        }
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                        circularButton1_login.setEnabled(true);
                        et_login.setEnabled(true);
                        et_mot_de_passe.setEnabled(true);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
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
