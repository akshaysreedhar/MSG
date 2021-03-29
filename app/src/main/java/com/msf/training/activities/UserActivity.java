package com.msf.training.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msf.training.R;
import com.msf.training.adapters.UserAdapter;
import com.msf.training.data.User;
import com.msf.training.databinding.ActivityUserBinding;

import java.util.Arrays;
import java.util.List;

public class UserActivity extends BaseActivity {
    private static final String url = "https://api.github.com/users";
    ActivityUserBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityUserBinding) getBinding(this, R.layout.activity_user);
        RecyclerView userList = binding.userList;
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            List<User> users = Arrays.asList(gson.fromJson(response.toString(), User[].class));
            userList.setAdapter(new UserAdapter(users));
        }, error -> {

        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
