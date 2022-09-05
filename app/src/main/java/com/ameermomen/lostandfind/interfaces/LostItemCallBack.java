package com.ameermomen.lostandfind.interfaces;

import com.ameermomen.lostandfind.Utils.LostItemPost;

public interface LostItemCallBack {

    void onCallButtonPress(LostItemPost post);
    void onMapButtonPress(LostItemPost post);

}
