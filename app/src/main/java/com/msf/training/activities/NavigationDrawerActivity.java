package com.msf.training.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.msf.training.R;
import com.msf.training.databinding.ActivityNavigaionDrawerBinding;
import com.msf.training.fragments.AddProductFragment;
import com.msf.training.fragments.EditProductFragment;

import java.util.Objects;

public class NavigationDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavigaionDrawerBinding binding = (ActivityNavigaionDrawerBinding) getBinding(this, R.layout.activity_navigaion_drawer);

        Toolbar toolbar = binding.tbBottomTab;
        setSupportActionBar(toolbar);
        drawerLayout = binding.drawer;
        NavigationView navigationView = binding.nvDrawer;
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        setFragment(new AddProductFragment(), R.string.add_product);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.add_product) {
            setFragment(new AddProductFragment(), R.string.add_product);
        }
        if (item.getItemId() == R.id.edit_product) {
            setFragment(new EditProductFragment(), R.string.edit_product);
        }
        if (item.getItemId() == R.id.logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirm);
            builder.setMessage(R.string.logout_confirm);
            builder.setPositiveButton(R.string.yes, (dialog, which) -> intentWithClearStack(this));
            builder.setNegativeButton(R.string.no, null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return false;
    }

    private void setFragment(Fragment fragment, Integer title) {
        getFragment(R.id.flFragmentContainer, fragment);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }
}