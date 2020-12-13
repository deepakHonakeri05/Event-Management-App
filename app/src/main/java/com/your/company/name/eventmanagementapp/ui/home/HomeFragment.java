package com.your.company.name.eventmanagementapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.your.company.name.eventmanagementapp.AddEventActivity;
import com.your.company.name.eventmanagementapp.R;
import com.your.company.name.eventmanagementapp.adapterLV;
import com.your.company.name.eventmanagementapp.eventDataType;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button addNewEventButton;

    //Firebase
    private DatabaseReference mDatabaseReference;

    ListView eventsListView; //from XML

    //This list stores firebase keys
    ArrayList<String> listKeys = new ArrayList<>();

    // We need this below link
    //@To do : add a custom datatype;
    public ArrayList<eventDataType> eventList;// contains all events


    //Create custom arrayAdapter
    public adapterLV adapterListView; //helps displaying event on screen



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        addNewEventButton = root.findViewById(R.id.addNewEvents);
        addNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent( getActivity(), AddEventActivity.class);
                startActivity(intent);
            }
        });

        //declaration
        eventsListView = root.findViewById(R.id.upcomingEvents);
        eventList = new ArrayList<>();
        adapterListView = new adapterLV(getActivity(),eventList);

        // Diplays the events;
        eventsListView.setAdapter(adapterListView);

        //Firebase data
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        addChildListener();

        return root;
    }


    // Listens/ sees new child elements that are added to Firebase database;

    public void addChildListener(){

        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    // we add new event to the list view : (eventslist)

                eventDataType newEvent = snapshot.getValue(eventDataType.class);
                /*
                    @To Do;
                    newEvent.setEventName( (String) snapshot.child("EventName").getValue());
                    newEvent.setDescription( (String) snapshot.child("Description").getValue());

                    registration     (same a s above)
                    contact
                 */

                listKeys.add(snapshot.getKey());
                //add new event on screen
                adapterListView.add(newEvent);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                String eventKey = snapshot.getKey();

                int position = listKeys.indexOf(eventKey);

                if(position >= 0){
                    eventList.remove(position);
                    listKeys.remove(position);
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDatabaseReference.addChildEventListener(childEventListener);
    }
}