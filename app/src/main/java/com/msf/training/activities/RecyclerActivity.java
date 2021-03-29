package com.msf.training.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.msf.training.R;
import com.msf.training.constants.Constants;
import com.msf.training.data.Recycler;
import com.msf.training.databinding.ActivityRecyclerBinding;
import com.msf.training.fragments.AddFragment;
import com.msf.training.fragments.BrandNameFragment;
import com.msf.training.fragments.FlowerNameFragment;
import com.msf.training.fragments.FoodNameFragment;
import com.msf.training.fragments.ResultFragment;
import com.msf.training.fragments.SubtractFragment;
import com.msf.training.singletons.StateManager;

public class RecyclerActivity extends BaseActivity {
    final StateManager stateManager = StateManager.getInstance();
    int i = 0;
    ActivityRecyclerBinding binding;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityRecyclerBinding) getBinding(this, R.layout.activity_recycler);
        Toolbar toolbar = binding.tbItemToolbar;
        BottomNavigationView bottomNavigationView = binding.bnItemNavigation;
        switchCompat = binding.swToggle;
        ImageButton imageButton = binding.ibBack;

        imageButton.setOnClickListener(v -> finish());
        setSupportActionBar(toolbar);
        binding.setRecycler(new Recycler(getString(R.string.food)));
        getFragment(R.id.flItemContainer, new FoodNameFragment());
        MenuItem item1 = bottomNavigationView.getMenu().findItem(R.id.food);

        if (i == 0) {
            setFragment(new FoodNameFragment(), getString(R.string.food), R.drawable.ic_food, new AddFragment(), getString(R.string.addition), R.drawable.ic_add, item1);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            i = 1;
            if (item.getItemId() == R.id.food) {
                setFragment(new FoodNameFragment(), getString(R.string.food), R.drawable.ic_food, new AddFragment(), getString(R.string.addition), R.drawable.ic_add, item);
            } else if (item.getItemId() == R.id.brand) {
                setFragment(new BrandNameFragment(), getString(R.string.brands), R.drawable.ic_brand, new SubtractFragment(), getString(R.string.subtraction), R.drawable.ic_minus, item);
            } else if (item.getItemId() == R.id.flower) {
                setFragment(new FlowerNameFragment(), getString(R.string.flowers), R.drawable.ic_flower, new ResultFragment(), getString(R.string.result), R.drawable.ic_done, item);
            }
            return false;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stateManager.setSwitchState1(false);
        stateManager.setSwitchState2(false);
        stateManager.setSwitchState3(false);
    }

    protected void setRecycler(Fragment fragment, String name, Integer icon, MenuItem item) {
        getFragment(R.id.flItemContainer, fragment);
        binding.setRecycler(new Recycler(name));
        item.setIcon(icon);
        item.setTitle(name);
    }

    protected void setOnTab(Fragment fragment, String name, Integer icon, MenuItem item, Boolean bool) {
        setRecycler(fragment, name, icon, item);
        switchCompat.setOnCheckedChangeListener(null);
        switchCompat.setChecked(bool);
    }

    private void setFragment(Fragment fragment1, String name1, Integer icon1, Fragment fragment2, String name2, Integer icon2, MenuItem item) {
        if (item.getItemId() == R.id.food) {
            if (!stateManager.getSwitchState1()) {
                setOnTab(fragment1, name1, icon1, item, false);
            } else {
                setOnTab(fragment2, name2, icon2, item, true);
            }
        }
        if (item.getItemId() == R.id.brand) {
            if (!stateManager.getSwitchState2()) {
                setOnTab(fragment1, name1, icon1, item, false);
            } else {
                setOnTab(fragment2, name2, icon2, item, true);
            }
        }
        if (item.getItemId() == R.id.flower) {
            if (!stateManager.getSwitchState3()) {
                setOnTab(fragment1, name1, icon1, item, false);
            } else {
                setOnTab(fragment2, name2, icon2, item, true);
            }
        }

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (item.getItemId() == R.id.food) {
                if (!stateManager.getSwitchState1()) {
                    setOnSwitch(Constants.foodSwitch, true, fragment2, name2, icon2, item);
                } else {
                    setOnSwitch(Constants.foodSwitch, false, fragment1, name1, icon1, item);
                }
            } else if (item.getItemId() == R.id.brand) {
                if (!stateManager.getSwitchState2()) {
                    setOnSwitch(Constants.brandSwitch, true, fragment2, name2, icon2, item);
                } else {
                    setOnSwitch(Constants.brandSwitch, false, fragment1, name1, icon1, item);
                }
            } else if (item.getItemId() == R.id.flower) {
                if (!stateManager.getSwitchState3()) {
                    setOnSwitch(Constants.flowerSwitch, true, fragment2, name2, icon2, item);
                } else {
                    setOnSwitch(Constants.flowerSwitch, false, fragment1, name1, icon1, item);
                }
            }
        });
    }

    private void setOnSwitch(String switchName, Boolean bool, Fragment fragment, String name, Integer icon, MenuItem item) {
        switch (switchName) {
            case Constants.foodSwitch:
                stateManager.setSwitchState1(bool);
                break;
            case Constants.brandSwitch:
                stateManager.setSwitchState2(bool);
                break;
            case Constants.flowerSwitch:
                stateManager.setSwitchState3(bool);
                break;
        }
        setRecycler(fragment, name, icon, item);
    }
}