package rs.tfzr.mtipstudenti;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MTIPStudentiMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PretragaFragment.OnFragmentInteractionListener,
        StudentiFragment.OnFragmentInteractionListener,
        StudentiDodavanjeFragment.OnFragmentInteractionListener,
        StudentiIzmenaFragment.OnFragmentInteractionListener
{


    public static Activity _activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mtipstudenti_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        _activity = this;
        displayView(new PretragaFragment(),_activity);

    }

    @Override
    public void onBackPressed() {

        try
        {
            StudentiDodavanjeFragment myFragment = (StudentiDodavanjeFragment)getFragmentManager().findFragmentByTag("StudentiDodavanje");
            if (myFragment != null && myFragment.isVisible()) {
                // add your code here
                displayView(new StudentiFragment(),_activity);
                return;
            }
        }
        catch (Exception ex)
        {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }




    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.navBar_Kalkulator: {
                Intent intentKalkulator = new Intent(this, MTIPKalkulator.class);
                startActivity(intentKalkulator);
                break;
            }

            case R.id.navBar_Pretraga:
            {
                displayView(new PretragaFragment(),this);
                break;
            }
            case R.id.navBar_Studenti:
            {
                displayView(new StudentiFragment(),this);
                break;
            }
            case R.id.navBar_Studijski_Programi:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
                builder.setMessage("Treba kreirati stranicu (Fragmente) za upravljanje Studijskim programima (Smerovima) kao ЕЎto je uraД‘eno za Studente!").setTitle("MTIP Studenti");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}});
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            }
            case R.id.navBar_O_Aplikaciji:
            {
                DijalogOAplikaciji();
                break;
            }

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void DijalogOAplikaciji() {
        final Dialog dialog = new Dialog(this, R.style.AboutDialog);

        dialog.setContentView(R.layout.mtip_about_dialog);
        dialog.setTitle("O Aplikaciji");

        TextView txtEmail = dialog.findViewById(R.id.textviewAboutDialogEmal);

        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mtip.studenti@tfzr.uns.ac.rs"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "sugestija / pitanje / zamerka ");
                    email.putExtra(Intent.EXTRA_TEXT, "");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Odaberite Vaš e - mail klijent :"));

                } catch (Exception ex) {

                }
            }
        });

        Button dialogButton = dialog.findViewById(R.id.dialogButtonOKClose);
        dialogButton.setOnClickListener(new
                                                View.OnClickListener() {
                                                    @Override
                                                    public void
                                                    onClick(View v) {
                                                        dialog.dismiss();
                                                    }
                                                })
        ;
        dialog.show();
    }

    public static void displayView(Fragment fragment, Activity activity) {
// update the main content by replacing fragments
        if (fragment != null) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment,"StudentiDodavanje").commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
