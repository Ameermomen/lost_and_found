package com.ameermomen.lostandfind.interfaces;

import com.ameermomen.lostandfind.Utils.LostItemPost;
import com.ameermomen.lostandfind.Utils.Post;

import java.util.ArrayList;

public interface PostCallBack {
    void uploadPostComplete(boolean status, String msg);
    void fetchLostItems(ArrayList<LostItemPost> lostItemPosts);

}
