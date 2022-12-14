package com.dannyboy.removalssystem2;

	import androidx.fragment.app.Fragment;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.ArrayAdapter;
	import android.widget.Button;
	import android.widget.Spinner;
	import android.widget.Toast;
	
	import java.util.ArrayList;
	import java.util.Arrays;


public class ServiciosFragment extends Fragment {

	Button b;
	View vista;
	Spinner spi;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	vista =  inflater.inflate(R.layout.activity_servicios_fragment, container, false);
	b = vista.findViewById(R.id.btnlog);
	spi= vista.findViewById(R.id.spin);
	
	String[] item = {"1", "2", "3", "4","5"};
	ArrayList<String>arrayList = new ArrayList<>(Arrays.asList(item));
	
	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, arrayList);
	spi.setAdapter(arrayAdapter);
	
	b.setOnClickListener(view -> Toast.makeText(getContext(),"No se", Toast.LENGTH_SHORT).show());
	return vista;
}
}