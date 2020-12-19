package com.your.company.name.eventmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class eventDetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_page);

        //we collect data from previous screen

        //getIntent() gets the data from previous screen

        Intent eventDetailsIntent = getIntent();
        String eventName = eventDetailsIntent.getStringExtra("EventName");
        String registrationLink = eventDetailsIntent.getStringExtra("RegistrationLink");
        //@To Do : collect other 2 fields as well

        TextView eventNAmeTV = findViewById(R.id.EventName);
        TextView registrationLinkTV = findViewById(R.id.registationLink);

        eventNAmeTV.setText(eventName);
        registrationLinkTV.setText(registrationLink);


    }
}