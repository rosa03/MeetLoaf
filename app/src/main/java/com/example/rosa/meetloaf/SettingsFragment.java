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
    private int sizeArray[] = {10, 15, 20, 30};
    private Button changeColour;
    private Button changeSize;
    int i = 0;
    int j = 0;

    static int textColourS = Color.BLACK;
    static int textSizeS = 15;

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
        changeSize = view.findViewById(R.id.changeSize);

        changeColour.setTextColor(SettingsFragment.textColourS);
        changeColour.setTextSize(SettingsFragment.textSizeS);

        changeSize.setTextColor(SettingsFragment.textColourS);
        changeSize.setTextSize(SettingsFragment.textSizeS);

        changeColour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (i == colourArray.length - 1) {
                    i = 0;
                } else {
                    i++;
                }
                changeColour.setTextColor(Color.parseColor(colourArray[i]));
                changeSize.setTextColor(Color.parseColor(colourArray[i]));
                MeetingsAdapter.textColour = Color.parseColor(colourArray[i]);
                CreateFragment.textColour = Color.parseColor(colourArray[i]);
                SettingsFragment.textColourS = Color.parseColor(colourArray[i]);

            }
        });
        changeSize.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (j == sizeArray.length - 1) {
                    j = 0;
                } else {
                    j++;
                }
                changeSize.setTextSize(sizeArray[j]);
                changeColour.setTextSize(sizeArray[j]);
                MeetingsAdapter.textSize = sizeArray[j];
                CreateFragment.textSize = sizeArray[j];
                SettingsFragment.textSizeS = sizeArray[j];
            }
        });
        return view;
    }


}
