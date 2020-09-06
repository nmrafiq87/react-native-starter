package com.reactnativestarter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class PreferenceModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;
    public static final String COLOUR_PREFERENCE = "COLOR_PREFERENCE";
    private SharedPreferences sharedPref;

    PreferenceModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
        sharedPref = getReactApplicationContext().getSharedPreferences(COLOUR_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public String getName() {
        return "PreferenceModule";
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }


    @ReactMethod
    public void getContact(String key, Callback successCallback) {
        WritableMap map = new WritableNativeMap();
        String json = sharedPref.getString("MyObject", "");
        Gson gson = new Gson();
        Contact contact = gson.fromJson(sharedPref.getString(key, ""), Contact.class);
        map.putString("id", contact.id);
        map.putString("name", contact.name);
        map.putInt("age", contact.age);
        successCallback.invoke(map);
    }

    @ReactMethod
    public void setContact(ReadableMap readableMap) {
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        Contact contact = new Contact(readableMap.getString("id"), readableMap.getString("name"), readableMap.getInt("age"));
        String json = gson.toJson(contact);
        editor.putString(readableMap.getString("id"), json);
        editor.commit();
     }

}
