package com.your.company.name.eventmanagementapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.your.company.name.eventmanagementapp.R;
import com.your.company.name.eventmanagementapp.calender;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        CardView calenderCV = root.findViewById(R.id.calenderCardView);

        calenderCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calenderIntent = new Intent(getActivity(), calender.class);
                startActivity(calenderIntent);
            }
        });

        return root;
    }

    //Works only for button
//    public void function(View view){
//
//        Intent calenderIntent = new Intent(getActivity(), calender.class);
//        startActivity(calenderIntent);
//
//    }
}