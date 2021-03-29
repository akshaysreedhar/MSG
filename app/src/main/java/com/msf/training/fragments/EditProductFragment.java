package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.msf.training.R;
import com.msf.training.constants.Constants;
import com.msf.training.databinding.FragmentEditProductBinding;
import com.msf.training.singletons.SharedPreferenceManager;

import org.jetbrains.annotations.NotNull;

public class EditProductFragment extends BaseFragment {
    FragmentEditProductBinding binding;

    public EditProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = (FragmentEditProductBinding) getBinding(inflater, R.layout.fragment_edit_product, container);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        EditText name = binding.etName;
        EditText price = binding.etPrice;
        TextView date = binding.etDate;

        SharedPreferenceManager.getInstance(getContext(), Constants.productDetails);

        String productName = SharedPreferenceManager.getStringValue(Constants.name);
        float productPrice = SharedPreferenceManager.getFloatValue(Constants.price);
        String productDate = SharedPreferenceManager.getStringValue(Constants.date);

        if (productName != null && productPrice != 0 && productDate != null) {
            name.setText(productName);
            price.setText(String.valueOf(productPrice));
            date.setText(productDate);
        }
        date.setOnClickListener(v -> showCalendar(date));
        binding.btEdit.setOnClickListener(v -> addProduct(price.getText().toString().trim(), name.getText().toString().trim(), date.getText().toString().trim()));
    }
}