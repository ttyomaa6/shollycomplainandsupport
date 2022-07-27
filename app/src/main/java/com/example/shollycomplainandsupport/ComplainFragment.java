package com.example.shollycomplainandsupport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shollycomplainandsupport.helpClasses.Complain;
import com.example.shollycomplainandsupport.helpClasses.FirebaseModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class ComplainFragment extends Fragment {

    public static ComplainFragment newInstance( ) {
        return new ComplainFragment();
    }

    FirebaseModel firebaseModel=new FirebaseModel();
    RecyclerView recyclerView;
    RecyclerViewAdapter.ItemClickListener itemClickListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_complain, container, false);
        BottomNavigationView bnv = getActivity().findViewById(R.id.bottomNavigationView);
        bnv.setVisibility(View.VISIBLE);
        firebaseModel.initAll();
        return root;
    }

    @Override
    public void onViewCreated(@Nullable View view,@NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        itemClickListener=new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Complain complain, String type) {
                RecentMethods.setCurrentFragment(ViewingFragment.newInstance(ComplainFragment.newInstance()),getActivity());
            }
        };
        recyclerView=view.findViewById(R.id.recyclerView);
        firebaseModel.getReference().child("AppData").child("complains")
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot=task.getResult();
                    ArrayList<Complain> complainArrayList=new ArrayList<>();
                    for (DataSnapshot snap:snapshot.getChildren()){
                        complainArrayList.add(snap.getValue(Complain.class));
                    }
                    RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(complainArrayList,itemClickListener,"complain");
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }
        });
    }
}
