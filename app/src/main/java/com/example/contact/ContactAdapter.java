package com.example.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context context;
    ArrayList<contact> arrcontact;
    contact_onclick onclick;

    ContactAdapter(Context context, ArrayList<contact> arrcontact, contact_onclick onclick) {
        this.context = context;
        this.arrcontact = arrcontact;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        holder.imgcontact.setImageResource(arrcontact.get(position).img);
        holder.txtname.setText(arrcontact.get(position).name);
        holder.txtnumber.setText(arrcontact.get(position).number);
        holder.imgdelete.setImageResource(R.drawable.ic_baseline_delete_24);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(arrcontact.get(holder.getAdapterPosition()),true);
            }
        });

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(arrcontact.get(holder.getAdapterPosition()),false);
            }
        });

        holder.imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = arrcontact.get(holder.getAdapterPosition()).number;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +91" + num));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrcontact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgcontact, imgdelete, imgcall;
        TextView txtname, txtnumber;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgcontact = itemView.findViewById(R.id.imgcntxt);
            txtname = itemView.findViewById(R.id.txtname);
            txtnumber = itemView.findViewById(R.id.txtnumber);
            imgdelete = itemView.findViewById(R.id.imgdelete);
            imgcall = itemView.findViewById(R.id.imgcall);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
