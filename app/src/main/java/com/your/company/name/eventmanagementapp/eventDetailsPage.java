package com.your.company.name.eventmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class eventDetailsPage extends AppCompatActivity {

    FirebaseStorage mStorageReference = FirebaseStorage.getInstance();
    StorageReference storageRef = mStorageReference.getReference("/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_page);

        //we collect data from previous screen

        //getIntent() gets the data from previous screen

        Intent eventDetailsIntent = getIntent();
        String eventName = eventDetailsIntent.getStringExtra("EventName");
        String registrationLink = eventDetailsIntent.getStringExtra("RegistrationLink");
        final String contactPhone = eventDetailsIntent.getStringExtra("ContactPhone");
        String description = eventDetailsIntent.getStringExtra("Description");

        //@To Do : collect other 2 fields as well

        TextView eventNAmeTV = findViewById(R.id.eventName);
        TextView registrationLinkTV = findViewById(R.id.registationLink);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        TextView contactPhoneTV = findViewById(R.id.contactPhone);

        eventNAmeTV.setText(eventName);
        registrationLinkTV.setText(registrationLink);
        descriptionTV.setText(description);
        contactPhoneTV.setText(contactPhone);


        contactPhoneTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData( Uri.parse("tel:"+contactPhone));
                startActivity(intent);
            }
        });

        final ImageView eventPosterIV = findViewById(R.id.eventPosterImage);



        storageRef.child(eventName+"/").child("eventPoster.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(eventDetailsPage.this).load(uri).into(eventPosterIV);
            }
        });

    }
}