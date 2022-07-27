package com.example.shollycomplainandsupport.looks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shollycomplainandsupport.helpClasses.Callbacks;
import com.example.shollycomplainandsupport.helpClasses.Clothes;
import com.example.shollycomplainandsupport.helpClasses.FirebaseModel;
import com.example.shollycomplainandsupport.helpClasses.NewsItem;
import com.example.shollycomplainandsupport.R;
import com.example.shollycomplainandsupport.RecentMethods;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewingLookFragment extends Fragment {

    FirebaseModel firebaseModel=new FirebaseModel();
    ImageView back,comment,send,schoolyCoin,options;
    TextView nickView,description,likesCount,lookPrice,lookPriceDollar,clothesCreator
            ,emptyList,comments,sendComment,noComment,delete,complain,complainOtherUserText;
    EditText editText;
    RecyclerView clothesCreatorsRecycler,complainRecycler;
    RecyclerView recyclerView;
    String likesCountString,lookPriceString,lookPriceDollarString
            ,editGetText,nick;
    ConstituentsAdapter.ItemClickListener itemClickListenerClothes;
    Fragment fragment;
    Bundle bundle;
    NewsItem newsItem;

    public ViewingLookFragment(Fragment fragment,NewsItem newsItem) {
        this.fragment = fragment;
        this.newsItem=newsItem;
    }

    public static ViewingLookFragment newInstance(Fragment fragment,NewsItem newsItem) {
        return new ViewingLookFragment(fragment,newsItem);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_viewinglook, container, false);
        BottomNavigationView bnv = getActivity().findViewById(R.id.bottomNavigationView);
        bnv.setVisibility(bnv.GONE);
        firebaseModel.initAll();
//        AppBarLayout abl = getActivity().findViewById(R.id.AppBarLayout);
//        abl.setVisibility(abl.GONE);
        return root;
    }

    @Override
    public void onViewCreated(@Nullable View view,@NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        back=view.findViewById(R.id.back);
        comment=view.findViewById(R.id.comment);
        description=view.findViewById(R.id.description);
        schoolyCoin=view.findViewById(R.id.schoolyCoin);
        clothesCreator=view.findViewById(R.id.clothesCreator);
        lookPrice=view.findViewById(R.id.lookPrice);
        options=view.findViewById(R.id.options);
        lookPriceDollar=view.findViewById(R.id.lookPriceDollar);
        nickView=view.findViewById(R.id.nick);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecentMethods.setCurrentFragment(fragment,getActivity());
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                RecentMethods.setCurrentFragment(fragment, getActivity());
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        nickView.setText(newsItem.getNick());
        nickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameToProfile=nickView.getText().toString();
            }
        });
        likesCountString=String.valueOf(newsItem.getLikes_count());
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogLookOptions(newsItem);
            }
        });
        clothesCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogClothesCreators(newsItem);
            }
        });
        description.setText(newsItem.getItem_description());
        if (newsItem.getLookPrice()==0) {
            lookPrice.setVisibility(View.GONE);
            lookPriceDollarString=String.valueOf(newsItem.getLookPriceDollar());
            if(newsItem.getLookPriceDollar()<1000){
                lookPriceDollar.setText(lookPriceDollarString+"$");
            }else if(newsItem.getLookPriceDollar()>1000 && newsItem.getLookPriceDollar()<10000){
                lookPriceDollar.setText(lookPriceDollarString.substring(0, 1)+"."+lookPriceDollarString.substring(1, 2)+"K"+"$");
            }
            else if(newsItem.getLookPriceDollar()>10000 && newsItem.getLookPriceDollar()<100000){
                lookPriceDollar.setText(lookPriceDollarString.substring(0, 2)+"."+lookPriceDollarString.substring(2,3)+"K"+"$");
            }
            else if(newsItem.getLookPriceDollar()>10000 && newsItem.getLookPriceDollar()<100000){
                lookPriceDollar.setText(lookPriceDollarString.substring(0, 2)+"."+lookPriceDollarString.substring(2,3)+"K"+"$");
            }else if(newsItem.getLookPriceDollar()>100000 && newsItem.getLookPriceDollar()<1000000){
                lookPriceDollar.setText(lookPriceDollarString.substring(0, 3)+"K"+"$");
            }
            else if(newsItem.getLookPriceDollar()>1000000 && newsItem.getLookPriceDollar()<10000000){
                lookPriceDollar.setText(lookPriceDollarString.substring(0, 1)+"KK"+"$");
            }
            else if(newsItem.getLookPriceDollar()>10000000 && newsItem.getLookPriceDollar()<100000000){
                lookPriceDollar.setText(lookPriceDollarString.substring(0, 2)+"KK"+"$");
            }
        }else{
            lookPriceDollarString=String.valueOf(newsItem.getLookPriceDollar());
            if(newsItem.getLookPriceDollar()<1000){
                lookPriceDollar.setText(" + "+lookPriceDollarString+"$");
            }else if(newsItem.getLookPriceDollar()>1000 && newsItem.getLookPriceDollar()<10000){
                lookPriceDollar.setText(" + "+lookPriceDollarString.substring(0, 1)+"."+lookPriceDollarString.substring(1, 2)+"K"+"$");
            }
            else if(newsItem.getLookPriceDollar()>10000 && newsItem.getLookPriceDollar()<100000){
                lookPriceDollar.setText(" + "+lookPriceDollarString.substring(0, 2)+"."+lookPriceDollarString.substring(2,3)+"K"+"$");
            }
            else if(newsItem.getLookPriceDollar()>10000 && newsItem.getLookPriceDollar()<100000){
                lookPriceDollar.setText(" + "+lookPriceDollarString.substring(0, 2)+"."+lookPriceDollarString.substring(2,3)+"K"+"$");
            }else if(newsItem.getLookPriceDollar()>100000 && newsItem.getLookPriceDollar()<1000000){
                lookPriceDollar.setText(" + "+lookPriceDollarString.substring(0, 3)+"K"+"$");
            }
            else if(newsItem.getLookPriceDollar()>1000000 && newsItem.getLookPriceDollar()<10000000){
                lookPriceDollar.setText(" + "+lookPriceDollarString.substring(0, 1)+"KK"+"$");
            }
            else if(newsItem.getLookPriceDollar()>10000000 && newsItem.getLookPriceDollar()<100000000){
                lookPriceDollar.setText(" + "+lookPriceDollarString.substring(0, 2)+"KK"+"$");
            }
        }
        if (newsItem.getLookPriceDollar()==0){
            lookPriceDollar.setVisibility(View.GONE);
        }
        if(newsItem.getLookPrice()==0){
            schoolyCoin.setVisibility(View.GONE);
            lookPrice.setVisibility(View.GONE);
        }else {
            lookPriceString=String.valueOf(newsItem.getLookPrice());
            if(newsItem.getLookPrice()<1000){
                lookPrice.setText(String.valueOf(lookPriceString));
            }else if(newsItem.getLookPrice()>1000 && newsItem.getLookPrice()<10000){
                lookPrice.setText(lookPriceString.substring(0, 1)+"."+lookPriceString.substring(1, 2)+"K");
            }
            else if(newsItem.getLookPrice()>10000 && newsItem.getLookPrice()<100000){
                lookPrice.setText(lookPriceString.substring(0, 2)+"."+lookPriceString.substring(2,3)+"K");
            }
            else if(newsItem.getLookPrice()>10000 && newsItem.getLookPrice()<100000){
                lookPrice.setText(lookPriceString.substring(0, 2)+"."+lookPriceString.substring(2,3)+"K");
            }else if(newsItem.getLookPrice()>100000 && newsItem.getLookPrice()<1000000){
                lookPrice.setText(lookPriceString.substring(0, 3)+"K");
            }
            else if(newsItem.getLookPrice()>1000000 && newsItem.getLookPrice()<10000000){
                lookPrice.setText(lookPriceString.substring(0, 1)+"KK");
            }
            else if(newsItem.getLookPrice()>10000000 && newsItem.getLookPrice()<100000000){
                lookPrice.setText(lookPriceString.substring(0, 2)+"KK");
            }
        }

    }

    private void showBottomSheetDialogClothesCreators(NewsItem newsItem) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_clothescreators);
        clothesCreatorsRecycler=bottomSheetDialog.findViewById(R.id.recyclerView);
        RecentMethods.getLookClothes(newsItem.getNick(), newsItem.getNewsId(), firebaseModel, new Callbacks.getLookClothes() {
            @Override
            public void getLookClothes(ArrayList<Clothes> clothesArrayList) {
                ConstituentsAdapter constituentsAdapter=new ConstituentsAdapter(clothesArrayList,itemClickListenerClothes);
                clothesCreatorsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                clothesCreatorsRecycler.setAdapter(constituentsAdapter);
            }
        });
        itemClickListenerClothes=new ConstituentsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Clothes clothes) {
                bottomSheetDialog.dismiss();
            }
        };

        bottomSheetDialog.show();
    }

    private void showBottomSheetDialogLookOptions(NewsItem newsItem) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_lookoptions);

        delete=bottomSheetDialog.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseModel.getUsersReference().child(newsItem.getNick()).child("looks")
                        .child(newsItem.getNewsId()).removeValue();
                Toast.makeText(getContext(), R.string.lookwasdeleted, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
