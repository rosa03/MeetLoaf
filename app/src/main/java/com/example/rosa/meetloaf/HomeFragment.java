package com.example.rosa.meetloaf;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * This class creates a fragment that displays current meetings.
 *
 * @author Rosa Salih
 * @version 1.0
 * @since 20-11-2018
 */
public class HomeFragment extends Fragment {

    private RecyclerView myMeetings;
    private RecyclerView.Adapter rvAdapter;
    private List<Meeting> meetings;
    private TextView textView;
    static int textColour = Color.BLACK;
    static int textSize = 30;

    /**
     * Constructs the fragment.
     */
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Constructs a view every time fragment is selected.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Home");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myMeetings = view.findViewById(R.id.myMeetings);
        textView = view.findViewById(R.id.textView);
        textView.setTextColor(textColour);
        textView.setTextSize(textSize);
        // Initialize meetings
        FileManager fm = new FileManager(getContext());
        meetings = new ArrayList<>();
        meetings.addAll(fm.readFile(getContext()));
        rvAdapter = new MeetingsAdapter(currentMeetings(), this.getContext());
        myMeetings.setAdapter(rvAdapter);
        myMeetings.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myMeetings.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    /**
     * This method checks if the date input is after the current date and adds it to an array list
     * if it is.
     *
     * @return m
     */
    public List<Meeting> currentMeetings() {
        List<Meeting> m = new ArrayList<>();
        Date currentDate = Calendar.getInstance().getTime();
        Date focus = null;
        for (Meeting meeting : meetings) {
            SimpleDateFormat date1 = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
            try {
                focus = date1.parse(meeting.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (currentDate.before(focus)) {
                m.add(meeting);
            }
        }

        return m;
    }


}