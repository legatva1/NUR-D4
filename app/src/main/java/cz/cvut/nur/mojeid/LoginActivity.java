package cz.cvut.nur.mojeid;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class LoginActivity extends Activity{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        PagerTabStrip strip = (PagerTabStrip) findViewById(R.id.strip);
        strip.setTabIndicatorColor(Color.GRAY);
        strip.setDrawFullUnderline(false);

        mViewPager.setCurrentItem(0);

        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.hide();
        }

        final Context c=this;

        Button b = (Button) findViewById(R.id.login);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos=mViewPager.getCurrentItem();

                String username;


                if (pos==0) {
                    username=PasswordFragment.getUsername();

                }else if (pos==1) {
                    username = CertificateFragment.getUsername();

                } else {
                    username = PasswordOTPFragment.getUsername();

                }




                if (username.equals("")) {
                    Toast.makeText(c, "Zadejte uživatelské jméno.", Toast.LENGTH_SHORT).show();
                } else {

                    new LoginTask(username,c).execute();

                }



            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_forgotten_password) {
            Intent intent=new Intent(this,ForgottenPasswordActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new PasswordFragment();
                    break;
                case 1:
                    fragment = new CertificateFragment();
                    break;
                case 2:
                    fragment = new PasswordOTPFragment();
                    break;
                default:
                    fragment = new PasswordFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_password);
                case 1:
                    return getString(R.string.title_certificate);
                case 2:
                    return getString(R.string.title_passwordotp);
            }
            return null;
        }
    }


    private class LoginTask extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;
        private String username;
        private Context mContext;


        private LoginTask(String username,Context context) {
            this.username = username;
            this.mContext=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Přihlašuji uživatele "+username);
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            Intent i = new Intent(LoginActivity.this, InformationTransferActivity.class);
            startActivity(i);
            finish();
        }
    }


}

