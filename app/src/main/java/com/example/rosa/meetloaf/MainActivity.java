package com.example.rosa.meetloaf;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private CreateFragment createFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = findViewById(R.id.main_frame);
        mainNav = findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        createFragment = new CreateFragment();
        historyFragment = new HistoryFragment();
        settingsFragment = new SettingsFragment();

        setFragment(homeFragment);

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(homeFragment);
                        return true;

                    case R.id.create:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(createFragment);
                        return true;

                    case R.id.history:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(historyFragment);
                        return true;

                    case R.id.settings:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(settingsFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

}

    private void setFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();

    }

}
