package com.ameermomen.lostandfind.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameermomen.lostandfind.R;
import com.ameermomen.lostandfind.Utils.Database;
import com.ameermomen.lostandfind.Utils.LostItemPost;
import com.ameermomen.lostandfind.interfaces.LostItemCallBack;
import com.ameermomen.lostandfind.interfaces.PostCallBack;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private Activity activity;
    private Database db;
    private ArrayList<LostItemPost> posts;
    private RecyclerView frag_home_RV_lostItems;
    public HomeFragment() {
       db = new Database();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(root);
        initVars();
        return root;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    private void findViews(View root) {
        frag_home_RV_lostItems = root.findViewById(R.id.frag_home_RV_lostItems);
    }

    private void initVars() {
        db.setPostCallBack(new PostCallBack() {
            @Override
            public void uploadPostComplete(boolean status, String msg) {

            }

            @Override
            public void fetchLostItems(ArrayList<LostItemPost> lostItemPosts) {
                posts = lostItemPosts;
                setLostItems();
            }
        });

        db.fetchLostItems();
    }

    public void setLostItems(){
        LostItemAdapter lostItemAdapter = new LostItemAdapter(activity, posts);
        lostItemAdapter.setLostItemCallBack(new LostItemCallBack() {
            @Override
            public void onCallButtonPress(LostItemPost post) {
                String phone = post.getUser().getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }

            @Override
            public void onMapButtonPress(LostItemPost post) {
                double lat = post.getPost().getLocation().getLatitude();
                double lang = post.getPost().getLocation().getLongitude();
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + lat + "," + lang);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        frag_home_RV_lostItems.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        frag_home_RV_lostItems.setHasFixedSize(true);
        frag_home_RV_lostItems.setItemAnimator(new DefaultItemAnimator());
        frag_home_RV_lostItems.setAdapter(lostItemAdapter);


    }
}