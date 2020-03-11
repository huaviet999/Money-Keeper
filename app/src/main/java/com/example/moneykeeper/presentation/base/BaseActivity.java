package com.example.moneykeeper.presentation.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import utils.AndroidDialogUtils;

/**
 * Created by Viet Hua on 3/11/2020
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayoutId());
    }

    protected abstract int getResLayoutId();

    //Show Toast Message
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Add - Replace - Hide Fragment
     */
    protected FragmentManager fragmentManager = null;

    protected void generateFragmentManager() {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
    }

    public void addFragment(Fragment fragment, String tag, int containerId) {
        generateFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag)
                .addToBackStack(tag).commit();
    }

    public void replaceFragment(Fragment fragment, String tag, int containerId) {
        generateFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag)
                .addToBackStack(tag).commit();
    }

    public void removeFragment(int resId) {
        generateFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(resId)).commit();
    }
    /**
     * Show / hide Progress Dialog
     */
    public void showProgressDialog(){
        AndroidDialogUtils.getInstance().showProgressDialog(this,"");
    }
    public void hideProgressDialog(){
        AndroidDialogUtils.getInstance().hideProgressDilog();
    }
}
