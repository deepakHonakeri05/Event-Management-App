package com.your.company.name.eventmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class adapterLV extends ArrayAdapter<eventDataType> {

    public adapterLV(Context context, List<eventDataType> events)
    {
        super(context,0,events);

    }

    @Override
    public View getView(int position, View scrapView, ViewGroup parent)
    {
        View listItemView = scrapView;
        //populate new views on screen
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.eventlistitem , parent, false);
            //just to inflate the layout with view
        }


        //To Do : reference 2 textviews; Refer language/Miwok app
        //TextView eventText = listItemView.findViewById(R.id.event_name);

        eventDataType currenteventDataType = getItem(position);

        TextView eventText = listItemView.findViewById(R.id.event_name);
        TextView descriptionText = listItemView.findViewById(R.id.event_description);

        eventText.setText(currenteventDataType.getEventName());
        descriptionText.setText(currenteventDataType.getDescription());

        return listItemView;
    }

}
