package com.theangel.ericsson.ericsson.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.activities.BuilderManager;
import com.theangel.ericsson.ericsson.activities.RechargerSoldeOoredooActivity;
import com.theangel.ericsson.ericsson.activities.TransfertDeCreditOoredooActivity;


public class ServicesOperateurOoredooFragment extends Fragment {

    private BoomMenuButton bmb;
    Fragment fragment;
    Class fragmentClass;
    String non_connecte, numtel, firstLetter;
    private NavigationView mNavigationView;
    Button btn_consulter_solde_ooredoo, btn_forfait_internet_ooredoo, btn_recharger_solde_ooredoo, btn_contacter_service_client_ooredoo, btn_transfert_de_credit_ooredoo;

    public static Fragment newInstance(Context context) {
        ServicesOperateurOoredooFragment f = new ServicesOperateurOoredooFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_services_operateur_ooredoo, null);

        bmb = (BoomMenuButton)root.findViewById(R.id.bmb_services_operateur_ooredoo);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_2);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < 9; i++){
            if (i == 0) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder0().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        if (isNetworkAvailable(getActivity().getApplicationContext())) {
                            fragment = null;
                            fragmentClass = EbayFragment.class;
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("ebay");
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getActivity().getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(Color.parseColor("#660099"));
                            }
                            mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                            Menu nv = mNavigationView.getMenu();
                            MenuItem item = nv.findItem(R.id.nav_ebay_fragment);
                            item.setChecked(true);
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Insert the fragment by replacing any existing fragment
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                        }
                    }
                }));
            }

            if (i == 1) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder1().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                    }
                }));
            }

            if (i == 2) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder2().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        if (isNetworkAvailable(getActivity().getApplicationContext())) {
                            fragment = null;
                            fragmentClass = MapFragment.class;
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Map");
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getActivity().getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(Color.parseColor("#660099"));
                            }
                            mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                            Menu nv = mNavigationView.getMenu();
                            MenuItem item = nv.findItem(R.id.nav_map_fragment);
                            item.setChecked(true);
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Insert the fragment by replacing any existing fragment
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                        }
                    }
                }));
            }

            if (i == 3) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder3().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        if (isNetworkAvailable(getActivity().getApplicationContext())) {
                            fragment = null;
                            fragmentClass = ChaineYoutubeEricssonFragment.class;
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Chaîne Youtube Ericsson");
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getActivity().getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(Color.parseColor("#660099"));
                            }
                            mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                            Menu nv = mNavigationView.getMenu();
                            MenuItem item = nv.findItem(R.id.nav_chaine_youtube_fragment);
                            item.setChecked(true);
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Insert the fragment by replacing any existing fragment
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                        }
                    }
                }));
            }

            if (i == 4) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder4().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        if (isNetworkAvailable(getActivity().getApplicationContext())) {
                            fragment = null;
                            fragmentClass = RadiosTunisiennesFragment.class;
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Radios tunisiennes");
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getActivity().getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(Color.parseColor("#660099"));
                            }
                            mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                            Menu nv = mNavigationView.getMenu();
                            MenuItem item = nv.findItem(R.id.nav_radios_tunisiennes_fragment);
                            item.setChecked(true);
                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Insert the fragment by replacing any existing fragment
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                        }
                    }
                }));
            }

            if (i == 5) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder5().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        fragment = null;
                        fragmentClass = HorlogeAtomiqueFragment.class;
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Horloge");
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getActivity().getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#660099"));
                        }
                        mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                        Menu nv = mNavigationView.getMenu();
                        MenuItem item = nv.findItem(R.id.nav_horloge_atomique_fragment);
                        item.setChecked(true);
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                    }
                }));
            }

            if (i == 6) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder6().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        fragment = null;
                        fragmentClass = TvShowFragment.class;
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TV Show");
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getActivity().getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#660099"));
                        }
                        mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                        Menu nv = mNavigationView.getMenu();
                        MenuItem item = nv.findItem(R.id.nav_horloge_atomique_fragment);
                        item.setChecked(true);
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                    }
                }));
            }

            if (i == 7) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder7().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        fragment = null;
                        fragmentClass = MeteoFragment.class;
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Météo");
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getActivity().getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#660099"));
                        }
                        mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                        Menu nv = mNavigationView.getMenu();
                        MenuItem item = nv.findItem(R.id.nav_meteo_fragment);
                        item.setChecked(true);
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                    }
                }));
            }

            if (i == 8) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder8().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        fragment = null;
                        fragmentClass = InfosDeviceFragment.class;
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Infos Device");
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getActivity().getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#660099"));
                        }
                        mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                        Menu nv = mNavigationView.getMenu();
                        MenuItem item = nv.findItem(R.id.nav_infos_device_fragment);
                        item.setChecked(true);
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                    }
                }));
            }
        }

        non_connecte = getActivity().getIntent().getStringExtra("non_connecte");
        numtel = getActivity().getIntent().getStringExtra("numtel");

        btn_consulter_solde_ooredoo = (Button)root.findViewById(R.id.btn_consulter_solde_ooredoo);
        btn_forfait_internet_ooredoo = (Button)root.findViewById(R.id.btn_forfait_internet_ooredoo);
        btn_recharger_solde_ooredoo = (Button)root.findViewById(R.id.btn_recharger_solde_ooredoo);
        btn_contacter_service_client_ooredoo = (Button)root.findViewById(R.id.btn_contacter_service_client_ooredoo);
        btn_transfert_de_credit_ooredoo = (Button)root.findViewById(R.id.btn_transfert_de_credit_ooredoo);

        btn_consulter_solde_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED ){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:*100%23"));
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {
                                    Manifest.permission.CALL_PHONE},
                            0);
                }

            }
        });

        btn_forfait_internet_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED ){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:*124%23"));
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {
                                    Manifest.permission.CALL_PHONE},
                            0);
                }

            }
        });

        btn_contacter_service_client_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED ){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1255"));
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {
                                    Manifest.permission.CALL_PHONE},
                            0);
                }

            }
        });

        btn_recharger_solde_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RechargerSoldeOoredooActivity.class);
                startActivity(intent);
            }
        });

        btn_transfert_de_credit_ooredoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TransfertDeCreditOoredooActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
