package com.example.ahar.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ahar.Model.DonateFoodItemList;
import com.example.ahar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DonateItemAdminpannelAdapter extends RecyclerView.Adapter<DonateItemAdminpannelAdapter.ViewHolder> {
    private static final String Tag = "RecyclerView";
    private Context context;
    private ArrayList<DonateFoodItemList> mdata;
    private DatabaseReference databaseReference;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String DonateID;

    public DonateItemAdminpannelAdapter(Context context, ArrayList<DonateFoodItemList> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.carddonateitem_admin,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemDes.setText(mdata.get(position).getFoodDes());
        holder.itemPrice.setText("Approximate price "+mdata.get(position).getApproxPrice()+" tk");
        holder.resaddress.setText(mdata.get(position).getRestaurantAddress());
        holder.resname.setText(mdata.get(position).getRestaurantName());
        holder.date.setText("Date: "+mdata.get(position).getDate());
        holder.stattime.setText("StartTime: "+mdata.get(position).getStartTime());
        holder.endtime.setText("EndTime: "+mdata.get(position).getEndTime());
        holder.itemconpeople.setText("Approximate "+mdata.get(position).getConsumePeople()+" people eat foods");

        Glide.with(context).load(mdata.get(position).getImage()).placeholder(R.drawable.dropdown).into(holder.itemImage);

        holder.confirmDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = mdata.get(position).getDonateid();
                String des = holder.itemDeliveryAddress.getText().toString().trim();
                DonateID = ss;
                String donateid = uid+"_"+DonateID;
                databaseReference = FirebaseDatabase.getInstance().getReference("confirmDonateFoodList");
                HashMap<String, Object> result = new HashMap<>();
                result.put("donateId", ss);
                result.put("deliveryAddress", des);
                databaseReference.child(donateid).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Query query = databaseReference.child("donateFoodList").orderByChild("donateId").equalTo(ss);
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                holder.itemView.setVisibility(View.GONE);
                                Toast.makeText(context, "Donate Successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemDes,itemPrice,resaddress,resname,date,stattime,endtime,itemconpeople;
        EditText itemDeliveryAddress;
        Button confirmDonate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.foodimg);
            itemDes = (TextView) itemView.findViewById(R.id.fooddescription);
            itemPrice = (TextView) itemView.findViewById(R.id.price);
            resaddress = (TextView) itemView.findViewById(R.id.resaddress);
            resname = (TextView) itemView.findViewById(R.id.restaurantname);
            date = (TextView) itemView.findViewById(R.id.date);
            stattime = (TextView) itemView.findViewById(R.id.starttime);
            endtime = (TextView) itemView.findViewById(R.id.endtime);
            itemconpeople = (TextView) itemView.findViewById(R.id.approxpeople);

            itemDeliveryAddress = (EditText) itemView.findViewById(R.id.deliveryaddress);
            confirmDonate = (Button) itemView.findViewById(R.id.confirmDonationButton);
        }
    }
}