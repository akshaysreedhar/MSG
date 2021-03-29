package com.msf.training.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.msf.training.R;
import com.msf.training.constants.Constants;
import com.msf.training.singletons.SharedPreferenceManager;

import java.text.DecimalFormat;
import java.util.Calendar;


public class BaseFragment extends Fragment {
    protected BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
    }

    protected void showCalendar(TextView date) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> {
            String dateText = dayOfMonth + "/" + (month + 1) + "/" + year;
            date.setText(dateText);
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    protected ViewDataBinding getBinding(LayoutInflater inflater, Integer layout, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, layout, container, false);
    }

    protected void doCalculation(String number1, String number2, Integer operator) {
        if (TextUtils.isEmpty(number1) || TextUtils.isEmpty(number2)) {
            showToast(getActivity(), getString(R.string.valid_inputs));
        } else {
            Bundle bundle = new Bundle();
            if (operator == R.string.add) {
                float result = Float.parseFloat(number1) + Float.parseFloat(number2);
                bundle.putFloat(Constants.sum, result);
            } else if (operator == R.string.sub) {
                float result = Float.parseFloat(number1) - Float.parseFloat(number2);
                bundle.putFloat(Constants.sub, result);
            }
            ResultFragment resultFragment = new ResultFragment();
            resultFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.flItemContainer, resultFragment).commit();
        }
    }

    protected void addProduct(String productPriceText, String productName, String productDate) {
        SharedPreferenceManager.getInstance(getContext(), Constants.productDetails);
        if (!TextUtils.isEmpty(productPriceText) && !TextUtils.isEmpty(productName) && !TextUtils.isEmpty(productDate)) {
            float productPrice = Float.parseFloat(productPriceText);
            DecimalFormat twoForm = new DecimalFormat("#.##");
            productPrice = Float.parseFloat(twoForm.format(productPrice));
            SharedPreferenceManager.setStringValue(Constants.name, productName);
            SharedPreferenceManager.setFloatValue(Constants.price, productPrice);
            SharedPreferenceManager.setStringValue(Constants.date, productDate);
            showToast(getContext(), getString(R.string.data_saved));
        } else {
            showToast(getContext(), getString(R.string.valid_inputs));
        }
    }

    protected void alert(String title, String message, DialogInterface.OnClickListener actionPositive, DialogInterface.OnClickListener actionNegative) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, actionPositive);
        builder.setNegativeButton(R.string.no, actionNegative);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}