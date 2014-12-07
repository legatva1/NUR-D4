package cz.cvut.nur.mojeid;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Václav Legát on 24.11.14.
 */
public class InformationTransferFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_information_transfer, container, false);

        List<Information> info=new ArrayList<Information>();


        info.add(new Information("Celé jméno",true));
        info.add(new Information("Ulice",true));
        info.add(new Information("Město",true));
        info.add(new Information("E-mail",false));
        info.add(new Information("Telefon",false));
        info.add(new Information("Přezdívka",false));
        info.add(new Information("Číslo popisné",false));

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);


        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new InformationAdapter(info,getActivity());
        mRecyclerView.setAdapter(mAdapter);


        Button transferButton=(Button)rootView.findViewById(R.id.button_transfer);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TransferDataTask(getActivity()).execute();
            }
        });



        return rootView;
    }




    private class TransferDataTask extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;

        private Activity mContext;


        private TransferDataTask(Activity context) {

            this.mContext=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Předávám údaje");
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

            Toast.makeText(mContext,"Registrace byla úspěšná.",Toast.LENGTH_SHORT).show();

            mContext.finish();



        }
    }
}
