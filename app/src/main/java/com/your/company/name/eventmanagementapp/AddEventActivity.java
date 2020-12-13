package com.your.company.name.eventmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    EditText eventName, eventDescription, eventLink, contactPhone;
    // poster image give some time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Event/");

        eventName = findViewById(R.id.eventName);
        eventDescription = findViewById(R.id.eventDescription);
        eventLink = findViewById(R.id.eventLink);
        contactPhone = findViewById(R.id.eventContact);

    }

    public void addData(View v){

        String name, description, link, contact;

        name = eventName.getText().toString();
        description = eventDescription.getText().toString();
        link = eventLink.getText().toString();
        contact = contactPhone.getText().toString();

        DatabaseReference element = mDatabaseReference.push();

        //              Key -->     Value       (Key-value pairs)
        element.child("EventName").setValue(name);
        element.child("Description").setValue(description);
        element.child("RegistrationLink").setValue(link);
        element.child("ContactPhone").setValue(contact);

        //finish function will close the activity on it's own;
        finish();
    }
}