package cz.cvut.nur.mojeid;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Václav Legát on 23.11.14.
 */
public class PasswordFragment extends Fragment{

    static TextView usernameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_password, container, false);

        usernameTextView=(TextView)rootView.findViewById(R.id.username);

        return rootView;
    }


    public static String getUsername() {

        if (usernameTextView != null) {
            return usernameTextView.getText().toString();
        }

        return "";
    }
}
