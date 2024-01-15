package com.sku.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.sku.myapplication.databinding.FragmentHomeBinding;
import com.sku.myapplication.ui.login.LoginActivity;

import android.util.Log;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static final String ARG_USER_ID = "userId";
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
            Log.d("HomeFragment", "Received userId: " + userId);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Update the text view with the userId
        final TextView welcomeTextView = binding.textHome;
        if (userId != null && !userId.isEmpty()) {
            welcomeTextView.setText("Welcome " + userId);
            Log.d("HomeFragment", "Setting welcome text for user: " + userId);
        } else {
            homeViewModel.getText().observe(getViewLifecycleOwner(), welcomeTextView::setText);
        }

        // Handle button click for login
        binding.buttonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
