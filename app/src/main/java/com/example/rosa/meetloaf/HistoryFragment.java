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
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView myMeetings;
    private RecyclerView.Adapter rvAdapter;
    private List<Meeting> meetings;
    private TextView tv;
    static int textColour = Color.BLACK;
    static int textSize = 30;

    /**
     * Constructs the fragment.
     */
    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Meeting History");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        myMeetings = view.findViewById(R.id.pastMeetings);
        tv = view.findViewById(R.id.tv);
        tv.setTextSize(textSize);
        tv.setTextColor(textColour);
        // Initialize meetings
        meetings = new ArrayList<>();
        FileManager fm = new FileManager(getContext());
        meetings.addAll(fm.readFile(getContext()));
        rvAdapter = new MeetingsAdapter(pastMeetings(), this.getContext());
        myMeetings.setAdapter(rvAdapter);
        myMeetings.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myMeetings.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    /**
     * This method checks if the date input is before the current date and adds it to an array list
     * of meeting objects if it is.
     *
     * @return m
     */
    public List<Meeting> pastMeetings() {
        List<Meeting> m = new ArrayList<>();
        Date currentDate = Calendar.getInstance().getTime();
        Date focus = null;
        for (Meeting meeting : meetings) {
            SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            try {
                focus = date1.parse(meeting.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (currentDate.after(focus)) {
                m.add(meeting);
            }
        }
        return m;
    }

}
