package com.example.ridesharecanada.model;

import android.content.Context;
import android.content.SharedPreferences;
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
    public void saveSearchQuery(String from, String to){
        if (SharedPrefEditor != null) {
            SharedPrefEditor.putString("From", from);
            SharedPrefEditor.putString("To", to);
            SharedPrefEditor.apply(); // Use apply() instead of commit()
        }
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
    public String getSearchFrom() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString("From", null);
        }
        return null;
    }
    public String getSearchTo() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString("To", null);
        }
        return null;
    }
}
