package com.your.company.name.eventmanagementapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.your.company.name.eventmanagementapp.eventDetailsPage;

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
    public List<eventDataType> eventList;// contains all events


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

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Event");

        //declaration
        eventsListView = root.findViewById(R.id.upcomingEvents);
        eventList = new ArrayList<>();
        adapterListView = new adapterLV(getActivity(),eventList);

        // Diplays the events;
        eventsListView.setAdapter(adapterListView);


        ///

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                                                                            // i - position, l - id
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent eventsPageIntent = new Intent(getActivity(), eventDetailsPage.class);

                //we send the data
                eventsPageIntent.putExtra("EventName",eventList.get(position).getEventName());
                eventsPageIntent.putExtra("RegistrationLink",eventList.get(position).getRegistrationlink());
                eventsPageIntent.putExtra("Description",eventList.get(position).getDescription());
                eventsPageIntent.putExtra("ContactPhone",eventList.get(position).getPhone());
                // add the other fields as well
                // phone, description
                //same format

                startActivity(eventsPageIntent);

                //
            }
        });


        ///

        addChildListener();

        return root;
    }


    // Listens/ sees new child elements that are added to Firebase database;

    public void addChildListener(){

        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                    // we add new event to the list view : (eventslist)

                eventDataType newEvent = snapshot.getValue(eventDataType.class);
                newEvent.setEventName( (String) snapshot.child("EventName").getValue());
                newEvent.setDescription( (String) snapshot.child("Description").getValue());
                newEvent.setRegistrationlink((String) snapshot.child("RegistrationLink").getValue());
                newEvent.setPhone((String) snapshot.child("ContactPhone").getValue());

                Log.e("Firebase","Data recieved!");

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