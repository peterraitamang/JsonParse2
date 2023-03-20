package com.example.jsonparse2;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonparse2.databinding.ItemRowBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdatper extends RecyclerView.Adapter<CustomAdatper.Viewholder> {
    ArrayList<String> profileImage;
    ArrayList<String> staffName;
    ArrayList<String> emails;
    ArrayList<String> contactNo;
    ArrayList<String> address;
    Context context;

    public CustomAdatper(Context context,
                         ArrayList<String> profileImage,
                         ArrayList<String> staffName,
                         ArrayList<String> emails,
                         ArrayList<String> contactNo,
                         ArrayList<String> address
                         ) {
        this.context = context;
        this.profileImage = profileImage;
        this.staffName = staffName;
        this.emails = emails;
        this.contactNo = contactNo;
        this.address = address;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        Viewholder viewholder = new Viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Resources resources = context.getResources();
        int resourceID = resources.getIdentifier(profileImage.get(position),
                "drawable",context.getPackageName());

        holder.profileImg.setImageResource(resourceID);
        holder.name.setText(staffName.get(position));
        holder.email.setText(emails.get(position));
        holder.contactNo.setText(contactNo.get(position));
        holder.address.setText(address.get(position));

        final int pos = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, staffName.get(pos), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return staffName.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name;
        TextView email;
        TextView address;
        TextView contactNo;
        ImageView profileImg;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileImg = (ImageView) itemView.findViewById(R.id.profile_image);

            name = (TextView) itemView.findViewById(R.id.txt_name);
            email = (TextView) itemView.findViewById(R.id.txt_email);
            address = (TextView) itemView.findViewById(R.id.txt_address);
            contactNo = (TextView) itemView.findViewById(R.id.txt_contactNo);


        }
    }
}
