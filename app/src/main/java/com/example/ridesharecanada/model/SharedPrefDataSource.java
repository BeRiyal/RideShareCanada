package com.example.ridesharecanada.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
public class SharedPrefDataSource {
    static SharedPrefDataSource instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor SharedPrefEditor;
    public static SharedPrefDataSource getInstance(){
        if (instance == null)
            instance = new SharedPrefDataSource();
        return instance;
    }
    public void init(Context context){
        sharedPreferences = context.getSharedPreferences("Login",Context.MODE_PRIVATE);
        SharedPrefEditor = sharedPreferences.edit();
    }
    public void setLoginId(String id){
        if (SharedPrefEditor != null) {
            SharedPrefEditor.putString("Id", id);
            SharedPrefEditor.apply(); // Use apply() instead of commit()
        }
    }
    public String getLoginId() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString("Id", null);
        }
        return null;
    }
}
