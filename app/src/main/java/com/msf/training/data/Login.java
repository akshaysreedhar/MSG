package com.msf.training.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.msf.training.BR;

public class Login extends BaseObservable {
    private String username;
    private String password;

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
