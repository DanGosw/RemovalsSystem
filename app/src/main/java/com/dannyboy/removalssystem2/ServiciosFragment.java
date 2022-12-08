package com.dannyboy.removalssystem2;

	import androidx.fragment.app.Fragment;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.Button;
	import android.widget.Toast;

public class ServiciosFragment extends Fragment {

	Button b;
	View vista;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	vista =  inflater.inflate(R.layout.activity_servicios_fragment, container, false);
	b = vista.findViewById(R.id.btnmessage);
	
	b.setOnClickListener(view -> Toast.makeText(getContext(),"No se", Toast.LENGTH_SHORT).show());
	return vista;
}
}