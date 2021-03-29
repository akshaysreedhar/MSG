package com.msf.training.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.msf.training.R;
import com.msf.training.databinding.ActivityGenderBinding;

import org.json.JSONException;

public class GenderActivity extends BaseActivity {
    ActivityGenderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityGenderBinding) getBinding(this, R.layout.activity_gender);

        binding.btPredict.setOnClickListener(v -> {
            if (binding.etName.getText().toString().length() == 0) {
                showToast(this, "Enter a valid name");
            } else {
                RequestQueue queue = Volley.newRequestQueue(this);
                String url = "https://api.genderize.io/?name=" + binding.etName.getText().toString().trim();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                    try {
                        String result;
                        if (response.getString("gender").equals("null")) {
                            result = "No data available";
                        } else {
                            result = "The name " + response.getString("name") + " is common among " + response.getString("gender") + "\n with an accuracy of " + (Float.parseFloat(response.getString("probability")) * 100);
                        }
                        binding.tvPrediction.setText(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> showToast(GenderActivity.this, "Something went wrong"));
                queue.add(jsonObjectRequest);
            }
        });


    }
}
