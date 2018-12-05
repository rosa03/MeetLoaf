package com.example.rosa.meetloaf;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //private FileManager fm = new FileManager(getActivity());
    private RecyclerView myMeetings;
    private RecyclerView.Adapter rvAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Home");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myMeetings = view.findViewById(R.id.myMeetings);
        // Initialize meetings
        List<Meeting> meetings = new ArrayList<>();
        for (Meeting m : meetings) {
            //FileManager.saveMeetingToFile(m, getContext());
        }
        //try {
        meetings = FileManager.readFile(getContext());
        //} catch (IOException e) {
        //   e.printStackTrace();
        //}
        FileManager fm = new FileManager(getContext());
        meetings.add(new Meeting("name", "house"));
        meetings.add(new Meeting("beep", "house"));

//        meetings.addAll(fm.readFile(getContext()));

        rvAdapter = new MeetingsAdapter(meetings, this.getContext());
        myMeetings.setAdapter(rvAdapter);
        myMeetings.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myMeetings.setItemAnimator(new DefaultItemAnimator());

        // Set layout manager to position the items
//        myMeetings.setLayoutManager(new LinearLayoutManager(getContext()));
//        try {
//            fm.readFile(this.getContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return view;
    }

}
