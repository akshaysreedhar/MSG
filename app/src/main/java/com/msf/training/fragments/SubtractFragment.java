package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.msf.training.R;
import com.msf.training.databinding.FragmentSubtractBinding;

import org.jetbrains.annotations.NotNull;

public class SubtractFragment extends BaseFragment {
    FragmentSubtractBinding binding;

    public SubtractFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = (FragmentSubtractBinding) getBinding(inflater, R.layout.fragment_subtract, container);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.btSubtract.setOnClickListener(view1 -> doCalculation(binding.etValueOne.getText().toString().trim(), binding.etValueTwo.getText().toString().trim(), R.string.sub));
    }
}