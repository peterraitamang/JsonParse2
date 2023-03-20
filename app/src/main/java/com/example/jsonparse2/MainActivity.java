package com.example.jsonparse2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.jsonparse2.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<String>  profileImage = new ArrayList<>();
    ArrayList<String> staffNames = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();
    ArrayList<String> contactNo = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView = binding.recycler1;

        try {
            JSONObject object = new JSONObject(loadJSONfromAsset());
            JSONArray userArray = object.getJSONArray("Staff Members");
            for (int i = 0; i< userArray.length();i++){
                JSONObject userDetail = userArray.getJSONObject(i);

                profileImage.add(userDetail.getString("image"));
                staffNames.add(userDetail.getString("name"));
                email.add(userDetail.getString("email"));
                contactNo.add(userDetail.getString("contact no"));
                address.add(userDetail.getString("address"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomAdatper customAdatper = new CustomAdatper(MainActivity.this,profileImage,
                staffNames,email,contactNo,address);
        recyclerView.setAdapter(customAdatper);


    }
    public String loadJSONfromAsset(){
        String json = null;
        try{
            InputStream inputStream = getAssets().open("list_item.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}