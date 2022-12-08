package com.dannyboy.removalssystem2;

	import androidx.fragment.app.Fragment;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;

public class VehiculosFragment extends Fragment {

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	return inflater.inflate(R.layout.activity_vehiculos_fragment, container, false);
	}
}