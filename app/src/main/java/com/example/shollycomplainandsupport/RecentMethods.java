package com.example.shollycomplainandsupport;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.shollycomplainandsupport.helpClasses.Callbacks;
import com.example.shollycomplainandsupport.helpClasses.Clothes;
import com.example.shollycomplainandsupport.helpClasses.FirebaseModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecentMethods {

    public static void setCurrentFragment(Fragment fragment, Activity activity) {
        FragmentTransaction ft = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }

    public static void getLookClothes(String nick, String uid, FirebaseModel firebaseModel, Callbacks.getLookClothes callback){
        firebaseModel.initAll();
        Query query=firebaseModel.getUsersReference().child(nick).child("looks").child(uid)
                .child("clothesCreators");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Clothes> clothesArrayList=new ArrayList<>();
                for(DataSnapshot snap:snapshot.getChildren()){
                    Clothes clothes=new Clothes();
                    clothes=snap.getValue(Clothes.class);
                    clothesArrayList.add(clothes);
                }
                callback.getLookClothes(clothesArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
