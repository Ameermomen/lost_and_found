package com.ameermomen.lostandfind.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.ameermomen.lostandfind.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private AddPostFragment addPostFragment;
    private BottomNavigationView bottom_navigation;

    private FrameLayout homeFrame, profileFrame, addPostFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        homeFrame.setVisibility(View.VISIBLE);
                        addPostFrame.setVisibility(View.INVISIBLE);
                        profileFrame.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.menu_add_post:
                        homeFrame.setVisibility(View.INVISIBLE);
                        addPostFrame.setVisibility(View.VISIBLE);
                        profileFrame.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.menu_profile:
                        homeFrame.setVisibility(View.INVISIBLE);
                        addPostFrame.setVisibility(View.INVISIBLE);
                        profileFrame.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
    }

    private void initViews() {
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_home, homeFragment).commit();

        addPostFragment = new AddPostFragment();
        addPostFragment.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_add_post, addPostFragment).commit();

        profileFragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_profile, profileFragment).commit();

        profileFrame.setVisibility(View.INVISIBLE);
        addPostFrame.setVisibility(View.INVISIBLE);
    }


    public void findViews(){
        bottom_navigation = findViewById(R.id.bottom_navigation);
        homeFrame = findViewById(R.id.main_frame_home);
        addPostFrame = findViewById(R.id.main_frame_add_post);
        profileFrame = findViewById(R.id.main_frame_profile);

    }
}