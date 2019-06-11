package com.theangel.ericsson.ericsson.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;
import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.activities.BuilderManager;
import com.theangel.ericsson.ericsson.activities.YoutubeVideo1Activity;
import com.theangel.ericsson.ericsson.adapters.ViewPagerAdapter;
import com.theangel.ericsson.ericsson.callbacks.OnImagesDownloaded;
import com.theangel.ericsson.ericsson.callbacks.OnSearchCompleted;
import com.theangel.ericsson.ericsson.model.GridItem;
import com.theangel.ericsson.ericsson.model.Show;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AccueilFragment extends Fragment {

    Button btn_services_operateur, btn_youtube, btn_map, btn_radios, btn_horloge;
    String nom, prenom, id_user, non_connecte, numtel, firstLetter;
    TextView tv_operateur_hors_ligne;
    Fragment fragment;
    Class fragmentClass;
    ImageView img1, img2, img3;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    // lets change the variable names first.
    private NavigationView mNavigationView;
    Menu nv;
    MenuItem item_services_operateur, item_map, item_youtube, item_horloge, item_radios;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    ViewPager viewPager;

    private BoomMenuButton bmb;
    private ArrayList<Pair> piecesAndButtons = new ArrayList<>();

    public static Fragment newInstance(Context context) {
        AccueilFragment f = new AccueilFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_accueil, null);

        bmb = (BoomMenuButton)root.findViewById(R.id.bmb_accueil);
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
        }



        //bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());
//        nom = getActivity().getIntent().getStringExtra("nom");
//        prenom = getActivity().getIntent().getStringExtra("prenom");
        non_connecte = getActivity().getIntent().getStringExtra("non_connecte");
//        id_user = getActivity().getIntent().getStringExtra("jibli_iduser");
        numtel = getActivity().getIntent().getStringExtra("numtel");


        //btn_horloge = (Button)root.findViewById(R.id.btn_horloge);
        //btn_map = (Button)root.findViewById(R.id.btn_map);
        //btn_radios = (Button)root.findViewById(R.id.btn_radios);
        //btn_youtube = (Button)root.findViewById(R.id.btn_youtube);
        //btn_services_operateur = (Button)root.findViewById(R.id.btn_services_operateur);

        //img_logo_operateur = (ImageView)root.findViewById(R.id.img_logo_operateur);
        //img_accueil = (ImageView)root.findViewById(R.id.img_accueil);
        viewPager = (ViewPager)root.findViewById(R.id.viewPager);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);

        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.mauve), 4000);
        animation.addFrame(getResources().getDrawable(R.drawable.ericsson_logo), 4000);
        animation.addFrame(getResources().getDrawable(R.drawable.mauvee), 4000);
        animation.setOneShot(false);

        //img_accueil.setBackgroundDrawable(animation);

        // start the animation!
        animation.start();

//                    Picasso.with(getActivity().getApplicationContext())
//                    .load("https://ericsson.000webhostapp.com/images/image_accueil.png")
//                    .into(img1);

//        URL url = null;
//        try {
//            url = new URL("http://192.168.0.101/android_login/images/image1.png");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = null;
//        try {
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        img_accueil.setImageBitmap(bmp);

        //new ImageDownloader(img_accueil).execute("http://192.168.0.101/android_login/images/image1.png");

//        if (isNetworkAvailable(getActivity().getApplicationContext())) {
//            Picasso.with(getActivity().getApplicationContext())
//                    .load("https://ericsson.000webhostapp.com/images/image_accueil.png")
//                    .into(img_accueil);
//        }else {
//            img_accueil.setBackgroundResource(R.drawable.ericsson);
//        }

        //tv_operateur_hors_ligne = (TextView)root.findViewById(R.id.tv_operateur_hors_ligne);

        //nvDrawer = (NavigationView)root.findViewById(R.id.nvView);

//        nv = mNavigationView.getMenu();
//        item_services_operateur = nv.findItem(R.id.nav_services_operateur_fragment);
//        item_map = nv.findItem(R.id.nav_map_fragment);
//        item_youtube = nv.findItem(R.id.nav_chaine_youtube_fragment);
//        item_horloge = nv.findItem(R.id.nav_horloge_atomique_fragment);
//        item_radios = nv.findItem(R.id.nav_radios_tunisiennes_fragment);

        if(non_connecte.equals("non_connecte")){
            //img_logo_operateur.setVisibility(View.GONE);

        }else {

        }

        if(numtel.equals("")){

        }else {
            firstLetter = String.valueOf(numtel.charAt(0));
            if(firstLetter.equals("2")){
//                img_logo_operateur.setBackgroundResource(R.drawable.logo_ooredoo);
            } else if(firstLetter.equals("5")){
//                img_logo_operateur.setBackgroundResource(R.drawable.logo_orange);
            } else if(firstLetter.equals("9")){
//                img_logo_operateur.setBackgroundResource(R.drawable.logo_tuntel);
            }
        }


//        btn_horloge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = null;
//                fragmentClass = HorlogeAtomiqueFragment.class;
//                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Horloge Atomique");
//                ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    Window window = getActivity().getWindow();
//                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                    window.setStatusBarColor(Color.parseColor("#660099"));
//                }
//                mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
//                Menu nv = mNavigationView.getMenu();
//                MenuItem item = nv.findItem(R.id.nav_horloge_atomique_fragment);
//                item.setChecked(true);
//                try {
//                    fragment = (Fragment) fragmentClass.newInstance();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                // Insert the fragment by replacing any existing fragment
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//            }
//        });
//
//        btn_map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isNetworkAvailable(getActivity().getApplicationContext())) {
//                    fragment = null;
//                    fragmentClass = MapFragment.class;
//                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Map");
//                    ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Window window = getActivity().getWindow();
//                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                        window.setStatusBarColor(Color.parseColor("#660099"));
//                    }
//                    mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
//                    Menu nv = mNavigationView.getMenu();
//                    MenuItem item = nv.findItem(R.id.nav_map_fragment);
//                    item.setChecked(true);
//                    try {
//                        fragment = (Fragment) fragmentClass.newInstance();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    // Insert the fragment by replacing any existing fragment
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//                }else {
//                    Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//
//        btn_radios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isNetworkAvailable(getActivity().getApplicationContext())) {
//                    fragment = null;
//                    fragmentClass = RadiosTunisiennesFragment.class;
//                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Radios tunisiennes");
//                    ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Window window = getActivity().getWindow();
//                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                        window.setStatusBarColor(Color.parseColor("#660099"));
//                    }
//                    mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
//                    Menu nv = mNavigationView.getMenu();
//                    MenuItem item = nv.findItem(R.id.nav_radios_tunisiennes_fragment);
//                    item.setChecked(true);
//                    try {
//                        fragment = (Fragment) fragmentClass.newInstance();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    // Insert the fragment by replacing any existing fragment
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//                }else {
//                    Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//
//        btn_youtube.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isNetworkAvailable(getActivity().getApplicationContext())) {
//                    fragment = null;
//                    fragmentClass = ChaineYoutubeEricssonFragment.class;
//                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Chaîne Youtube Ericsson");
//                    ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Window window = getActivity().getWindow();
//                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                        window.setStatusBarColor(Color.parseColor("#660099"));
//                    }
//                    mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
//                    Menu nv = mNavigationView.getMenu();
//                    MenuItem item = nv.findItem(R.id.nav_chaine_youtube_fragment);
//                    item.setChecked(true);
//                    try {
//                        fragment = (Fragment) fragmentClass.newInstance();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    // Insert the fragment by replacing any existing fragment
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//                }else {
//                    Toast.makeText(getActivity().getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//
//        btn_services_operateur.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(non_connecte.equals("non_connecte")){
//                   Toast.makeText(getActivity().getApplicationContext(),"Veuillez vous authentifier.",Toast.LENGTH_LONG).show();
//                }else {
//                    fragment = null;
//                    firstLetter = String.valueOf(numtel.charAt(0));
//                    if(firstLetter.equals("2")){
//                        fragmentClass = ServicesOperateurOoredooFragment.class;
//                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Ooredoo");
//                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E40613")));
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            Window window = getActivity().getWindow();
//                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                            window.setStatusBarColor(Color.parseColor("#E40613"));
//                        }
//                    } else if(firstLetter.equals("5")){
//                        fragmentClass = ServicesOperateurOrangeFragment.class;
//                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Orange");
//                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6600")));
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            Window window = getActivity().getWindow();
//                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                            window.setStatusBarColor(Color.parseColor("#FF6600"));
//                        }
//                    } else if(firstLetter.equals("9")){
//                        fragmentClass = ServicesOperateurTunTelFragment.class;
//                        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tunisie Télécom");
//                        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#14376B")));
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            Window window = getActivity().getWindow();
//                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                            window.setStatusBarColor(Color.parseColor("#14376B"));
//                        }
//                    }
//                    mNavigationView = (NavigationView)getActivity().findViewById(R.id.nvView);
//                    Menu nv = mNavigationView.getMenu();
//                    MenuItem item = nv.findItem(R.id.nav_services_operateur_fragment);
//                    item.setChecked(true);
//                    try {
//                        fragment = (Fragment) fragmentClass.newInstance();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    // Insert the fragment by replacing any existing fragment
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//
//
//
//                }
//
//            }
//        });

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_PHONE_STATE) ==
                PackageManager.PERMISSION_GRANTED ){

        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                            Manifest.permission.READ_PHONE_STATE},
                    0);
        }

        return root;
    }


    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            if (getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(viewPager.getCurrentItem()==0){
                            viewPager.setCurrentItem(1);
                        }else if(viewPager.getCurrentItem()==1){
                            viewPager.setCurrentItem(2);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }

        }
    }

}
