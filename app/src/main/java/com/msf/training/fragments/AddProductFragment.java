package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.msf.training.R;
import com.msf.training.databinding.FragmentAddProductBinding;

import org.jetbrains.annotations.NotNull;

public class AddProductFragment extends BaseFragment {
    FragmentAddProductBinding binding;

    public AddProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = (FragmentAddProductBinding) getBinding(inflater, R.layout.fragment_add_product, container);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.etDate.setOnClickListener(v -> showCalendar(binding.etDate));
        binding.btAddProduct.setOnClickListener(v -> addProduct(binding.etPrice.getText().toString().trim(), binding.etName.getText().toString().trim(), binding.etDate.getText().toString().trim()));
    }
}