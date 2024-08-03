package com.pi.newsdemoapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.pi.newsdemoapp.databinding.ActivityFragmentTutorialBinding;

public class FragmentTutorialActivity extends AppCompatActivity {
    ActivityFragmentTutorialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentTutorialBinding.inflate(getLayoutInflater());
        setContentView(binding.main);
        initListeners();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new ChatFragment())
                .commit();
    }

    private void initListeners() {
        binding.chatTab.setOnClickListener(view -> {
            showFragment(new ChatFragment());
        });
        binding.statusTab.setOnClickListener(view -> {
            showFragment(new StatusFragment());
        });
        binding.callsTab.setOnClickListener(view -> {
            showFragment(new CallsFragment());
        });
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}