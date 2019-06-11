package com.theangel.ericsson.ericsson.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.activities.Apercu;
import com.theangel.ericsson.ericsson.activities.BuilderManager;

public class RadiosTunisiennesFragment extends Fragment {

    private BoomMenuButton bmb;
    Fragment fragment;
    Class fragmentClass;
    String non_connecte, numtel, firstLetter;
    private NavigationView mNavigationView;
    Button mosaique_fm, ifm, shems_fm, express_fm, oxygene_fm, cap_fm, jawhara_fm, oasis_fm, knooz_fm, sabra_fm, mfm_tn, radio6, saraha_fm, radio_med;

    public static Fragment newInstance(Context context) {
        RadiosTunisiennesFragment f = new RadiosTunisiennesFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_radios_tunisiennes, null);

        bmb = (BoomMenuButton)root.findViewById(R.id.bmb_radio);
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

        mosaique_fm = (Button)root.findViewById(R.id.mosaique_fm);
        ifm = (Button)root.findViewById(R.id.ifm);
        shems_fm = (Button)root.findViewById(R.id.shems_fm);
        express_fm = (Button)root.findViewById(R.id.express_fm);
        oxygene_fm = (Button)root.findViewById(R.id.oxygene_fm);
        cap_fm = (Button)root.findViewById(R.id.cap_fm);
        jawhara_fm = (Button)root.findViewById(R.id.jawhara_fm);
        oasis_fm = (Button)root.findViewById(R.id.oasis_fm);
        knooz_fm = (Button)root.findViewById(R.id.knooz_fm);
        sabra_fm = (Button)root.findViewById(R.id.sabra_fm);
        mfm_tn = (Button)root.findViewById(R.id.mfm_tn);
        radio6 = (Button)root.findViewById(R.id.radio6);
        saraha_fm = (Button)root.findViewById(R.id.saraha_fm);
        radio_med = (Button)root.findViewById(R.id.radio_med);

        mosaique_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://radio.mosaiquefm.net:8000/mosalive");
                intent.putExtra("cle_nom", "MOSAIQUE FM 94.9");
                startActivity(intent);
            }
        });

        ifm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://5.135.138.182:8000/direct");
                intent.putExtra("cle_nom", "Radio IFM 100.6");
                startActivity(intent);
            }
        });

        shems_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://stream6.tanitweb.com/shems");
                intent.putExtra("cle_nom", "SHEMS FM 101.7");
                startActivity(intent);
            }
        });

        express_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://stream6.tanitweb.com/expressfm");
                intent.putExtra("cle_nom", "EXPRESS FM 103.6");
                startActivity(intent);
            }
        });

        oxygene_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://radiooxygenefm.ice.infomaniak.ch/radiooxygenefm-64.mp3");
                intent.putExtra("cle_nom", "Oxygene FM 90.0");
                startActivity(intent);
            }
        });

        cap_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://stream8.tanitweb.com/capfm");
                intent.putExtra("cle_nom", "CAP FM 105.6");
                startActivity(intent);
            }
        });

        jawhara_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://streaming2.toutech.net:8000/jawharafm");
                intent.putExtra("cle_nom", "JAWHARA FM 102.5");
                startActivity(intent);
            }
        });

        oasis_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://stream6.tanitweb.com/oasis");
                intent.putExtra("cle_nom", "Oasis FM 96.5");
                startActivity(intent);
            }
        });

        knooz_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://streaming.knoozfm.net:8000/knoozfm");
                intent.putExtra("cle_nom", "KnOOz FM 105.1");
                startActivity(intent);
            }
        });

        sabra_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://stream6.tanitweb.com/sabrafm");
                intent.putExtra("cle_nom", "Sabra FM 98.8");
                startActivity(intent);
            }
        });

        mfm_tn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://radiomfmtunisie.net:8000/live");
                intent.putExtra("cle_nom", "MFM 94.6");
                startActivity(intent);
            }
        });

        radio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://streaming.radio6tunis.net:8000/radio6tunis");
                intent.putExtra("cle_nom", "Radio 6 97.2");
                startActivity(intent);
            }
        });

        saraha_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://ns326208.ip-37-59-9.eu:8000/sarahafm");
                intent.putExtra("cle_nom", "Saraha FM 107.3");
                startActivity(intent);
            }
        });

        radio_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Apercu.class);
                intent.putExtra("cle_lien","http://stream6.tanitweb.com/radiomed");
                intent.putExtra("cle_nom", "Radio Med 100.0");
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
