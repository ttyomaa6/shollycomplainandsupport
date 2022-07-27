package com.example.shollycomplainandsupport;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shollycomplainandsupport.helpClasses.Complain;
import com.example.shollycomplainandsupport.helpClasses.ComplainResponse;
import com.example.shollycomplainandsupport.helpClasses.FirebaseModel;
import com.example.shollycomplainandsupport.looks.ViewingLookFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ViewingFragment extends Fragment {

    Fragment fragment;
    EditText editText;
    RelativeLayout confirmRelative;
    FirebaseModel firebaseModel=new FirebaseModel();
    TextView appealedPerson, complaintPerson, reason, whomTo, description, descriptionText, goToLook, deleteAccount;

    public ViewingFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public static ViewingFragment newInstance(Fragment fragment) {
        return new ViewingFragment(fragment);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_viewing, container, false);
        BottomNavigationView bnv = getActivity().findViewById(R.id.bottomNavigationView);
        bnv.setVisibility(bnv.GONE);
        firebaseModel.initAll();
        return root;
    }

    @Override
    public void onViewCreated(@Nullable View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView leftarrow = view.findViewById(R.id.back_tomain);
        leftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecentMethods.setCurrentFragment(fragment, getActivity());
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                RecentMethods.setCurrentFragment(fragment, getActivity());
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        appealedPerson = view.findViewById(R.id.appealedPerson);
        complaintPerson = view.findViewById(R.id.complaintPerson);
        reason = view.findViewById(R.id.reason);
        whomTo = view.findViewById(R.id.toWhom);
        description = view.findViewById(R.id.description);
        descriptionText = view.findViewById(R.id.descriptionText);
        editText = view.findViewById(R.id.edittext);
        confirmRelative = view.findViewById(R.id.confirm);
        goToLook = view.findViewById(R.id.gotolook);
        deleteAccount = view.findViewById(R.id.deleteAccount);

        RecyclerViewAdapter.complainInfo(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Complain complain, String type) {
                if (type.equals("support")) {
                    whomTo.setVisibility(View.GONE);
                    complaintPerson.setVisibility(View.GONE);
                    descriptionText.setVisibility(View.GONE);
                    description.setVisibility(View.GONE);
                    goToLook.setVisibility(View.GONE);
                    deleteAccount.setVisibility(View.GONE);
                }
                appealedPerson.setText(complain.getAppealedPerson());
                complaintPerson.setText(complain.getSneakPerson());
                reason.setText(complain.getReason());
                description.setText(complain.getDescription());
                if (complain.getNewsItem() == null) {
                    goToLook.setVisibility(View.GONE);
                }
                goToLook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecentMethods.setCurrentFragment(ViewingLookFragment.newInstance(ViewingFragment.newInstance(fragment), complain.getNewsItem()), getActivity());
                    }
                });
                deleteAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(complain.getSneakPerson());
                    }
                });
                confirmRelative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().length()==0){
                            Toast.makeText(getContext(),R.string.enterReply, Toast.LENGTH_SHORT).show();
                        }else{
                            if(type.equals("support")){
                                String uid=firebaseModel.getReference().child("AppData").child("supportResponse").push().getKey();
                                firebaseModel.getReference().child("AppData").child("supportResponse").child(uid).setValue(new ComplainResponse(editText.getText().toString(), complain, uid));
                                Toast.makeText(getContext(), R.string.replysend, Toast.LENGTH_SHORT).show();
                            }else {
                                String uid=firebaseModel.getReference().child("AppData").child("complainResponse").push().getKey();
                                firebaseModel.getReference().child("AppData").child("complainResponse").child(uid).setValue(new ComplainResponse(editText.getText().toString(), complain, uid));
                                Toast.makeText(getContext(), R.string.replysend, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });


    }
    public void showDialog(String complaintPerson){

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_delete);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView text=dialog.findViewById(R.id.acceptText);
        text.setText("Удалить аккаунт"+" "+complaintPerson+"?");

        RelativeLayout yes=dialog.findViewById(R.id.yes);
        RelativeLayout no=dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseModel.getUsersReference().child(complaintPerson).removeValue();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
