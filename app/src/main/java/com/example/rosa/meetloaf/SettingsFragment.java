package com.example.rosa.meetloaf;


import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

public class SettingsFragment extends Fragment {
    private String colourArray[] = {"#000000", "#34000d", "#800020", "#4B0082", "#293ED2", "#0B7465"};
    private Button changeColour;
    private CreateFragment createFragment;

    /**
     * Constructs the fragment.
     */
    public SettingsFragment() {
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
        ((MainActivity) getActivity()).setActionBarTitle("Settings");
        // Inflate the layout for this fragment
        //addPreferencesFromResource(R.xml.app_preferences);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        changeColour = view.findViewById(R.id.changeColour);
        changeColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random i = new Random();
                int c = i.nextInt(6 - 1) + 1;
                changeColour.setTextColor(Color.parseColor(colourArray[c]));

            }
        });
        return view;
    }


}
