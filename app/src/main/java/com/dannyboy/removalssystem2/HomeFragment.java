package com.dannyboy.removalssystem2;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dannyboy.removalssystem2.databinding.ActivityHomeFragmentBinding;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment {

private ActivityHomeFragmentBinding binding;

private NavigationView drawerLayout;


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	

	return inflater.inflate(R.layout.activity_home_fragment, container, false);

	}
}