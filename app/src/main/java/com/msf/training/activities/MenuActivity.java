package com.msf.training.activities;

import android.content.Intent;
import android.os.Bundle;

import com.msf.training.R;
import com.msf.training.data.Menu;
import com.msf.training.databinding.ActivityMenuBinding;

public class MenuActivity extends BaseActivity {
    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityMenuBinding) getBinding(this, R.layout.activity_menu);
        if (getIntent() != null) {
            if (getIntent().getStringExtra(Intent.EXTRA_TEXT) != null) {
                binding.setName(new Menu(getString(R.string.hello) + " " + getIntent().getStringExtra(Intent.EXTRA_TEXT)));
                binding.setActivity(this);
            }
        }
    }

    public void demoIntent() {
        intentScreen(this, DemoActivity.class);
    }

    public void navDrawerIntent() {
        intentScreen(this, NavigationDrawerActivity.class);
    }

    public void recyclerIntent() {
        intentScreen(this, RecyclerActivity.class);
    }

    public void logoutIntent() {
        intentWithClearStack(this);
    }

    public void genderIntent() {
        intentScreen(this, GenderActivity.class);
    }

    public void userIntent() {
        intentScreen(this, UserActivity.class);
    }
}