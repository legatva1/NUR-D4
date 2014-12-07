package cz.cvut.nur.mojeid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Václav Legát on 24.11.14.
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InfoHolder> implements View.OnClickListener {


    private List<Information> contactList;
    private Context mContext;

    public InformationAdapter(List<Information> contactList,Context context) {
        this.contactList = contactList;
        this.mContext=context;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(InfoHolder contactViewHolder, int i) {
       Information ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.getName());
        if (ci.isRequired()) {
            contactViewHolder.required.setVisibility(View.VISIBLE);
        } else {
            contactViewHolder.required.setVisibility(View.INVISIBLE);
        }

        CardView cardView=(CardView)contactViewHolder.itemView;

        if (ci.isRequired()) {

            cardView.setCardBackgroundColor(Color.rgb(220,220,220));
            ci.setSelected(true);
            contactViewHolder.checkBox.setImageResource(R.drawable.ic_checked);


        }




    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        InfoHolder holder=new InfoHolder(itemView);

        itemView.setOnClickListener(this);

        itemView.setTag(holder);


        return holder;
    }

    @Override
    public void onClick(View v) {
        final InfoHolder holder = (InfoHolder) v.getTag();
        int position = holder.getPosition();

        final CardView cardView=(CardView)v;
        final Information i=contactList.get(position);



        if (i.isSelected()) {


            if (i.isRequired()) {


                final Context c = mContext;
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setCancelable(true);
                builder.setMessage("Předání povinného údaje je nutné k úspěšnému dokončení registrace.");
                builder.setTitle("Odebrání povinného údaje");
                builder.setPositiveButton("Odebrat", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        cardView.setCardBackgroundColor(Color.WHITE);
                        i.setSelected(false);
                        holder.checkBox.setImageResource(R.drawable.ic_unchecked);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Zrušit", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {

                        dialog.dismiss();

                    }
                });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener()
                {
                    public void onCancel(DialogInterface dialog) {
                        //  context.finish();
                        dialog.dismiss();

                    }
                });

                builder.show();
            }else {

                cardView.setCardBackgroundColor(Color.WHITE);
                i.setSelected(false);
                holder.checkBox.setImageResource(R.drawable.ic_unchecked);

            }





        } else {
            cardView.setCardBackgroundColor(Color.rgb(220,220,220));
            i.setSelected(true);
            holder.checkBox.setImageResource(R.drawable.ic_checked);

        }



    }


    public static class InfoHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView required;
        protected ImageView checkBox;



        public InfoHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
            required =  (TextView) v.findViewById(R.id.required);
            checkBox=(ImageView)v.findViewById(R.id.checkbox);

        }
    }

}
