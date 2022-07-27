package com.example.shollycomplainandsupport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shollycomplainandsupport.helpClasses.Complain;
import com.example.shollycomplainandsupport.helpClasses.FirebaseModel;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    ArrayList<Complain> listAdapter;
    private RecyclerViewAdapter.ItemClickListener clickListener;
    String nick;
    private FirebaseModel firebaseModel = new FirebaseModel();
    static String type;
    static Complain complain1;
    ItemClickListener itemClickListener;

    public  RecyclerViewAdapter(ArrayList<Complain> listAdapter,ItemClickListener itemClickListener,String type) {
        this.listAdapter = listAdapter;
        this.itemClickListener=itemClickListener;
        this.type=type;
    }


    @NotNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.rvitem_complains, viewGroup, false);
        ViewHolder viewHolder=new RecyclerViewAdapter.ViewHolder(v);
        firebaseModel.initAll();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Complain complain = listAdapter.get(position);
        holder.appealedPerson.setText(complain.getAppealedPerson());
        holder.complaintPerson.setText(complain.getSneakPerson());
        if(type.equals("support")){
            holder.whomTo.setVisibility(View.GONE);
            holder.complaintPerson.setVisibility(View.GONE);
            holder.descriptionText.setVisibility(View.GONE);
            holder.description.setVisibility(View.GONE);
        }
        holder.reason.setText(complain.getReason());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complain1 =listAdapter.get(holder.getAdapterPosition());
                itemClickListener.onItemClick(listAdapter.get(holder.getAdapterPosition()),type);
            }
        });
        holder.description.setText(complain.getDescription());
    }



    @Override
    public int getItemCount() {
        return listAdapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView appealedPerson,complaintPerson,reason,whomTo,description,descriptionText;
        ViewHolder(View itemView) {
            super(itemView);
            appealedPerson=itemView.findViewById(R.id.appealedPerson);
            complaintPerson=itemView.findViewById(R.id.complaintPerson);
            reason=itemView.findViewById(R.id.reason);
            whomTo=itemView.findViewById(R.id.toWhom);
            description=itemView.findViewById(R.id.description);
            descriptionText=itemView.findViewById(R.id.descriptionText);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(new Complain(),"");
        }
    }

    Complain getItem(int id) {
        return listAdapter.get(id);
    }


    public interface ItemClickListener {
        void onItemClick(Complain complain,String type);
    }

    public static void complainInfo(ItemClickListener itemClickListener){
        itemClickListener.onItemClick(complain1,type);
    }


}
