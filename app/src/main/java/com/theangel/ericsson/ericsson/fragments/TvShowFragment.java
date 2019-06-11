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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.activities.BuilderManager;
import com.theangel.ericsson.ericsson.activities.DetailsActivity;
import com.theangel.ericsson.ericsson.activities.YoutubeVideo1Activity;
import com.theangel.ericsson.ericsson.async.ImagesFromUrlAT;
import com.theangel.ericsson.ericsson.async.SearchAT;
import com.theangel.ericsson.ericsson.callbacks.OnImagesDownloaded;
import com.theangel.ericsson.ericsson.callbacks.OnSearchCompleted;
import com.theangel.ericsson.ericsson.model.GridItem;
import com.theangel.ericsson.ericsson.model.Show;
import com.theangel.ericsson.ericsson.ui.ImagesUIAdapter;

import java.util.List;


public class TvShowFragment extends Fragment implements OnSearchCompleted, OnImagesDownloaded<Show> {

    private final String TAG = "c3trace";

    private EditText etKeyword;
    private GridView gvShows;
    private ProgressBar pbLoading;
    private Button btnsearch, watania1, watania2, elhiwarettounsi, beinsportnews, france24, euronews, alkaassonline, france_info;
    private BoomMenuButton bmb;
    Fragment fragment;
    Class fragmentClass;
    String non_connecte, numtel, firstLetter;
    private NavigationView mNavigationView;

    public static Fragment newInstance(Context context) {
        TvShowFragment f = new TvShowFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tv_show, null);

        bmb = (BoomMenuButton)root.findViewById(R.id.bmb_tv_show);
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

        pbLoading = (ProgressBar) root.findViewById(R.id.pbLoading);
        etKeyword = (EditText) root.findViewById(R.id.etKeyword);
        gvShows = (GridView) root.findViewById(R.id.gvShows);
        btnsearch = (Button)root.findViewById(R.id.btnSearch);

        watania1 = (Button)root.findViewById(R.id.watania1);
        watania2 = (Button)root.findViewById(R.id.watania2);
        elhiwarettounsi = (Button)root.findViewById(R.id.elhiwarettounsi);
        beinsportnews = (Button)root.findViewById(R.id.beinsportnews);
        france24 = (Button)root.findViewById(R.id.france24);
        euronews = (Button)root.findViewById(R.id.euronews);
        alkaassonline = (Button)root.findViewById(R.id.alkaassonline);
        france_info = (Button)root.findViewById(R.id.france_info);

        watania1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent1.putExtra("lien_video","7IymnBrCwng");
                startActivity(intent1);
            }
        });

        watania2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent2.putExtra("lien_video","AiNrsP0-LXU");
                startActivity(intent2);
            }
        });

        elhiwarettounsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent3.putExtra("lien_video","rDF-slYIkuw");
                startActivity(intent3);
            }
        });

        beinsportnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent4.putExtra("lien_video","wjZsWw4x3iU");
                startActivity(intent4);
            }
        });

        france24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent5.putExtra("lien_video","hL0sEdVJs3U");
                startActivity(intent5);
            }
        });

        euronews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent6.putExtra("lien_video","q5H4bPSXthM");
                startActivity(intent6);
            }
        });

        alkaassonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent7.putExtra("lien_video","k2ZXeeehZPw");
                startActivity(intent7);
            }
        });

        france_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(getActivity().getApplicationContext(), YoutubeVideo1Activity.class);
                intent8.putExtra("lien_video","SN7sncKzRq4");
                startActivity(intent8);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch_onClick(v);
            }
        });

        return root;
    }

    public void btnSearch_onClick(View source) {
        pbLoading.setVisibility(View.VISIBLE);
        gvShows.setVisibility(View.GONE);

        String showName = etKeyword.getText().toString();

        SearchAT async = new SearchAT(this);
        async.execute(showName);
    }

    @Override
    public void onSearchCompleted(List<Show> shows) {
        for (Show s : shows) {
            Log.i(TAG, s.toString());
        }

        ImagesFromUrlAT imagesAT = new ImagesFromUrlAT(this);
        imagesAT.execute(shows);
    }

    @Override
    public void onImagesDownloaded(List<GridItem<Show>> bitmaps) {
        gvShows.setAdapter(new ImagesUIAdapter(getActivity().getApplicationContext(), bitmaps));

        gvShows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " + id);
                Intent intent = new Intent(getActivity().getApplicationContext(), DetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        pbLoading.setVisibility(View.GONE);
        gvShows.setVisibility(View.VISIBLE);
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
