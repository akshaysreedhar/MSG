package com.msf.training.activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.msf.training.R;
import com.msf.training.data.Login;
import com.msf.training.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityLoginBinding) getBinding(this, R.layout.activity_login);
        binding.setActivity(this);
        binding.setLogin(new Login());
    }

    public void userLogin() {
        if (binding.getLogin().getUsername() != null && binding.getLogin().getPassword() != null) {
            String uName = binding.getLogin().getUsername().trim();
            String pass = binding.getLogin().getPassword().trim();
            if (uName.length() < 6 || uName.length() > 30 || !uName.equals(getString(R.string.akshay)) || TextUtils.isEmpty(uName)) {
                showToast(this, getString(R.string.check_name));
            } else if (pass.length() < 6 || !pass.equals(getString(R.string.new_pass)) || TextUtils.isEmpty(pass)) {
                showToast(this, getString(R.string.check_pass));
            } else {
                startActivity(new Intent(this, MenuActivity.class).putExtra(Intent.EXTRA_TEXT, uName));
                finish();
            }
        } else {
            showToast(this, getString(R.string.valid_inputs));
        }
    }
}