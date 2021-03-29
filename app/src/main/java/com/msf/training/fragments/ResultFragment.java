package com.msf.training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.msf.training.R;
import com.msf.training.constants.Constants;
import com.msf.training.databinding.FragmentResultBinding;

import org.jetbrains.annotations.NotNull;


public class ResultFragment extends BaseFragment {
    FragmentResultBinding binding;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = (FragmentResultBinding) getBinding(inflater, R.layout.fragment_result, container);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = this.getArguments();
            binding.tvSum.setText(String.valueOf(bundle.getFloat(Constants.sum)));
            binding.tvSub.setText(String.valueOf(bundle.getFloat(Constants.sub)));
        }
    }
}