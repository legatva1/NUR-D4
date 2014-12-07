package cz.cvut.nur.mojeid;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ForgottenPasswordActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgotten_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_forgotten_password, container, false);


            Button confirm=(Button)rootView.findViewById(R.id.confirm);

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),"Instrukce byly odeslány na Váš e-mail.",Toast.LENGTH_LONG).show();
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
            });

            final ImageView code=(ImageView)rootView.findViewById(R.id.codeImage);


            Button newCode=(Button)rootView.findViewById(R.id.new_code);

            newCode.setOnClickListener(new View.OnClickListener() {

                int codePos;
                @Override
                public void onClick(View v) {

                    codePos++;

                    if (codePos == 3) {
                        codePos=0;
                    }

                    switch (codePos) {
                        case 0:
                            code.setImageResource(R.drawable.ic_code1);
                            break;
                        case 1:
                            code.setImageResource(R.drawable.ic_code2);
                            break;
                        case 2:
                            code.setImageResource(R.drawable.ic_code3);
                            break;

                    }




                }
            });


            return rootView;
        }
    }
}
