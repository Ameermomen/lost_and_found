package com.ameermomen.lostandfind.interfaces;

public interface AuthCallBack {
    void onCreateAccountComplete(boolean status, String msg);
    void updateUserInfoComplete(boolean status, String msg);
    void onLoginComplete(boolean status, String msg);
}
