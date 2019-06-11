package com.theangel.ericsson.ericsson.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.fragments.AccueilFragment;
import com.theangel.ericsson.ericsson.fragments.ChaineYoutubeEricssonFragment;
import com.theangel.ericsson.ericsson.fragments.EbayFragment;
import com.theangel.ericsson.ericsson.fragments.HorlogeAtomiqueFragment;
import com.theangel.ericsson.ericsson.fragments.InfosDeviceFragment;
import com.theangel.ericsson.ericsson.fragments.MapFragment;
import com.theangel.ericsson.ericsson.fragments.MeteoFragment;
import com.theangel.ericsson.ericsson.fragments.ParametresFragment;
import com.theangel.ericsson.ericsson.fragments.RadiosTunisiennesFragment;
import com.theangel.ericsson.ericsson.fragments.ServicesOperateurOoredooFragment;
import com.theangel.ericsson.ericsson.fragments.ServicesOperateurOrangeFragment;
import com.theangel.ericsson.ericsson.fragments.ServicesOperateurTunTelFragment;
import com.theangel.ericsson.ericsson.fragments.TvShowFragment;

public class MenuActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    Context context;
    Fragment fragment;
    String nom, prenom, non_connecte, numtel;
    TextView tv_nom_user;
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    // lets change the variable names first.
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //provenance = getIntent().getStringExtra("from");
        //nom = getIntent().getStringExtra("nom");
        //prenom = getIntent().getStringExtra("prenom");
        non_connecte = getIntent().getStringExtra("non_connecte");
        numtel = getIntent().getStringExtra("numtel");
        nom = getIntent().getStringExtra("nom");
        prenom = getIntent().getStringExtra("prenom");
        mNavigationView = (NavigationView) findViewById(R.id.nvView);
        tv_nom_user = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.tv_nom_user);
        Menu nv = mNavigationView.getMenu();
        MenuItem item = nv.findItem(R.id.nav_deconnexion_fragment);


        if(non_connecte.equals("non_connecte")){
            item.setTitle("S'authentifier");
            tv_nom_user.setText("Invité");

        }else {
            tv_nom_user.setText(prenom + " " + nom);
            //item.setVisible(false);
        }

        // Set a Toolbar to replace the ActionBar.
                toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        // ...From section above...
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#660099"));
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, AccueilFragment.newInstance(context)).commit();
        getSupportActionBar().setTitle("Accueil");
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_accueil_fragment:
                fragmentClass = AccueilFragment.class;
                getSupportActionBar().setTitle("Accueil");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_services_operateur_fragment:
                fragmentClass = AccueilFragment.class;
                if(non_connecte.equals("non_connecte")){
                    Toast.makeText(getApplicationContext(),"Veuillez vous authentifier.",Toast.LENGTH_LONG).show();
                    Menu nv = mNavigationView.getMenu();
                    MenuItem item1 = nv.findItem(R.id.nav_accueil_fragment);
                    item1.setChecked(true);
                }else{
                    String firstLetter = String.valueOf(numtel.charAt(0));
                    if(firstLetter.equals("2")){
                        fragmentClass = ServicesOperateurOoredooFragment.class;
                        getSupportActionBar().setTitle("Ooredoo");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E40613")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#E40613"));
                        }
                    } else if(firstLetter.equals("5")){
                        fragmentClass = ServicesOperateurOrangeFragment.class;
                        getSupportActionBar().setTitle("Orange");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6600")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#FF6600"));
                        }
                    } else if(firstLetter.equals("9")){
                        fragmentClass = ServicesOperateurTunTelFragment.class;
                        getSupportActionBar().setTitle("Tunisie Télécom");
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#14376B")));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#14376B"));
                        }
                    }

                }
                break;
            case R.id.nav_map_fragment:
                fragmentClass = AccueilFragment.class;
                if (isNetworkAvailable(getApplicationContext())) {
                    fragmentClass = MapFragment.class;
                    getSupportActionBar().setTitle("Map");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.parseColor("#660099"));
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                    Menu nv = mNavigationView.getMenu();
                    MenuItem item1 = nv.findItem(R.id.nav_accueil_fragment);
                    item1.setChecked(true);
                }

                break;
            case R.id.nav_chaine_youtube_fragment:
                fragmentClass = AccueilFragment.class;
                if (isNetworkAvailable(getApplicationContext())) {
                    fragmentClass = ChaineYoutubeEricssonFragment.class;
                    getSupportActionBar().setTitle("Chaîne Youtube Ericsson");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.parseColor("#660099"));
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                    Menu nv = mNavigationView.getMenu();
                    MenuItem item1 = nv.findItem(R.id.nav_accueil_fragment);
                    item1.setChecked(true);
                }


                break;
            case R.id.nav_radios_tunisiennes_fragment:
                fragmentClass = AccueilFragment.class;
                if (isNetworkAvailable(getApplicationContext())) {
                    fragmentClass = RadiosTunisiennesFragment.class;
                    getSupportActionBar().setTitle("Radios tunisiennes");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6666CC")));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.parseColor("#6666CC"));
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Aucune connexion Internet",Toast.LENGTH_LONG).show();
                    Menu nv = mNavigationView.getMenu();
                    MenuItem item1 = nv.findItem(R.id.nav_accueil_fragment);
                    item1.setChecked(true);
                }

                break;
            case R.id.nav_horloge_atomique_fragment:
                fragmentClass = HorlogeAtomiqueFragment.class;
                getSupportActionBar().setTitle("Horloge Atomique");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_tv_show_fragment:
                fragmentClass = TvShowFragment.class;
                getSupportActionBar().setTitle("TV Show");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_meteo_fragment:
                fragmentClass = MeteoFragment.class;
                getSupportActionBar().setTitle("Météo");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_infos_device_fragment:
                fragmentClass = InfosDeviceFragment.class;
                getSupportActionBar().setTitle("Infos Device");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_ebay_fragment:
                fragmentClass = EbayFragment.class;
                getSupportActionBar().setTitle("ebay");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_parametres_du_compte_fragment:
                fragmentClass = ParametresFragment.class;
                getSupportActionBar().setTitle("Paramètres du compte");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#660099")));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.parseColor("#660099"));
                }
                break;
            case R.id.nav_contacter_admin_fragment:

                Intent intentmail = new Intent(android.content.Intent.ACTION_SEND);
                intentmail.setType("text/plain");
                intentmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"benhniamed9@gmail.com"});
                intentmail.putExtra(android.content.Intent.EXTRA_SUBJECT, "Réclamation ODP");
                try {
                    startActivity(Intent.createChooser(intentmail, "Sending mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MenuActivity.this, "Aucun compte mail n'est installé sur ce smartphone.", Toast.LENGTH_LONG).show();
                }
                fragmentClass = AccueilFragment.class;
                break;
            case R.id.nav_deconnexion_fragment:


                if(non_connecte.equals("non_connecte")) {

                    Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    sp = getSharedPreferences("sp1", MODE_PRIVATE);
                    spe = sp.edit();
                    spe.putString("mail", null);
                    spe.putString("pwd", null);
                    spe.commit();
                    Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }




                fragmentClass = AccueilFragment.class;


                break;
            default:
                fragmentClass = AccueilFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
