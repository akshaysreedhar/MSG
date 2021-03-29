package com.msf.training.activities;

import android.os.Bundle;

import com.msf.training.R;
import com.msf.training.data.Demo;
import com.msf.training.databinding.ActivityDemoBinding;

public class DemoActivity extends BaseActivity {
    ActivityDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= (ActivityDemoBinding) getBinding(this, R.layout.activity_demo);
        String local_var = getString(R.string.local_hi);
        binding.setDemo(new Demo(local_var, new InstanceVarExample().instance_var));
    }

    private class InstanceVarExample {
        final String instance_var = getString(R.string.instance_hi);
    }
}