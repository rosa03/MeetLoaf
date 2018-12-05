package com.example.rosa.meetloaf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Meeting History");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        myMeetings = view.findViewById(R.id.pastMeetings);
        // Initialize meetings
        meetings = new ArrayList<>();
        FileManager fm = new FileManager(getContext());
        meetings.addAll(fm.readFile(getContext()));
        rvAdapter = new MeetingsAdapter(pastMeetings(), this.getContext());
        myMeetings.setAdapter(rvAdapter);
        myMeetings.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myMeetings.setItemAnimator(new DefaultItemAnimator());
        return view;    }

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
            //System.out.println(sDate1+"\t"+date1);
            if (currentDate.after(focus)) {
                m.add(meeting);
            }
        }
        return m;
    }

}
