package com.theangel.ericsson.ericsson.fragments;

import android.Manifest;
import android.content.Context;
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
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.activities.BuilderManager;


public class MapFragment extends Fragment {

    MapView mapView;
    GoogleMap map;
    private BoomMenuButton bmb;
    Fragment fragment;
    Class fragmentClass;
    String non_connecte, numtel, firstLetter;
    private NavigationView mNavigationView;

    public static Fragment newInstance(Context context) {
        MapFragment f = new MapFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_map, null);

        bmb = (BoomMenuButton)root.findViewById(R.id.bmb_map);
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
                        if(non_connecte.equals("non_connecte")){
                            Toast.makeText(getActivity().getApplicationContext(),"Veuillez vous authentifier.",Toast.LENGTH_LONG).show();
                        }else {
                            fragment = null;
                            firstLetter = String.valueOf(numtel.charAt(0));
                            if(firstLetter.equals("2")){
                                fragmentClass = ServicesOperateurOoredooFragment.class;
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Ooredoo");
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E40613")));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    Window window = getActivity().getWindow();
                                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                    window.setStatusBarColor(Color.parseColor("#E40613"));
                                }
                            } else if(firstLetter.equals("5")){
                                fragmentClass = ServicesOperateurOrangeFragment.class;
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Orange");
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6600")));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    Window window = getActivity().getWindow();
                                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                    window.setStatusBarColor(Color.parseColor("#FF6600"));
                                }
                            } else if(firstLetter.equals("9")){
                                fragmentClass = ServicesOperateurTunTelFragment.class;
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tunisie Télécom");
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#14376B")));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    Window window = getActivity().getWindow();
                                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                    window.setStatusBarColor(Color.parseColor("#14376B"));
                                }
                            }
                            mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
                            Menu nv = mNavigationView.getMenu();
                            MenuItem item = nv.findItem(R.id.nav_services_operateur_fragment);
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
                    }
                }));
            }

            if (i == 2) {
                bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder2().listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Clicked " + index, Toast.LENGTH_SHORT).show();
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

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) root.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            // Gets to GoogleMap from the MapView and does initialization stuff
            map = mapView.getMap();
            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.setMyLocationEnabled(true);

            // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
            MapsInitializer.initialize(this.getActivity());

            // Updates the location and zoom of the MapView
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(36.8189700, 10.1657900), 10);
            map.animateCamera(cameraUpdate);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    0);        }






        return root;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
