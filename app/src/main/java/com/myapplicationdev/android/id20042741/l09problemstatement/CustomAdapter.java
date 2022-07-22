package com.myapplicationdev.android.id20042741.l09problemstatement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id; // layout id for the row the id for the custom layout (android.R.layout.row)
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    // this getView method is used to render or to get the row // displaying the row
    // this method will be called depending on how many items are there in the data source (arraylist, array)
    // this method will also be called when items are being displayed or currently not visible
    // returns 1 row only for each value in the data source
    public View getView(int position, View convertView, ViewGroup parent){

        // Used to retrieve the LayoutInflater object so that we can inflate each row
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the view for each row
        // layout_id = the custom layout file id we had created
        // creates a view object based on the row.xml file
        // it gets the row.xml file by filling the first parameter with the layout id or android.R.layout.row
        // we use layout_id because we already know the id of the custom layout row.xml file that was parsed in from the main class
        View rowView = inflater.inflate(layout_id,parent, false);

        // Obtain the UI component and do the necessary binding
        // using the View rowView = .... , we essentially creates an object of the layout
        // the reason why we can use rowView.findViewByID here is because we created an object of the xml file
        // and it has the 2 controls inside.
        TextView songName = rowView.findViewById(R.id.clTVSongTitle);
        TextView songYear = rowView.findViewById(R.id.clTVSongYear);
        TextView songStar = rowView.findViewById(R.id.clTVSongStar);
        TextView songSinger = rowView.findViewById(R.id.clTVSongArtist);

        // Obtain the android version information based on the position
        // this arraylist is retrieved from the arraylist we had created inside this class and parsed from the main class
        // since this method will be called a # of times, the position of each Row will be different and starting from 0
        // it will be safe to assume that we can populate the data based on the new position of the listview
        Song currentVersion = songList.get(position);

        // Set values to the TextView to display the corresponding information
        songName.setText(currentVersion.getTitle());
        songYear.setText(String.valueOf(currentVersion.getYear()));
        songStar.setText(currentVersion.toString());
        songSinger.setText(currentVersion.getSinger());

        return rowView;
    }
}
