package com.your.company.name.eventmanagementapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.your.company.name.eventmanagementapp.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    //Firebase
    private DatabaseReference mDatabaseReference;

    EditText testEdit;
    Button sendButton;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        testEdit = root.findViewById(R.id.sampleQuery);



        //Firebase data
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        sendButton = root.findViewById(R.id.sendData);

        //Alternative to regular
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String random = testEdit.getText().toString();
                mDatabaseReference.child("SampleTest").child("User ID").setValue(random);
            }
        });




        return root;
    }
}