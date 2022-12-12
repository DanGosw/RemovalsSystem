package com.dannyboy.removalssystem2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener{

MapView mMapView;
GoogleMap mMap;
View vista;
TextView lat, lon;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	vista = inflater.inflate(R.layout.activity_home_fragment, container, false);
	
	return vista;
}
@Override
public void onCreate(Bundle saveInstance){super.onCreate(saveInstance);}
@Override
public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
	super.onViewCreated (view, savedInstanceState);
	mMapView = vista.findViewById(R.id.map);
	lat = vista.findViewById(R.id.latit);
	lon = vista.findViewById(R.id.longi);
	if (mMapView != null) {
		mMapView.onCreate(null);
		mMapView.onResume();
		mMapView.getMapAsync(this);
	}
	}
@Override
public void onMapReady(@NonNull GoogleMap googleMap) {
	mMap = googleMap;
	this.mMap.setOnMapClickListener(this);
	this.mMap.setOnMapLongClickListener(this);
	
	LatLng pe = new LatLng(-9.125574839157506, -78.51786661473645);
	mMap.addMarker(new MarkerOptions().position(pe).title("Mi ubicacion"));
	mMap.moveCamera(CameraUpdateFactory.newLatLng(pe));
}

@Override
public void onMapClick(@NonNull LatLng latLng) {
	
	lat.setText(String.valueOf(latLng.latitude));
	lon.setText(String.valueOf(latLng.longitude));
	mMap.clear();
	LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
	mMap.addMarker(new MarkerOptions().position(mexico).title(""));
	mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
}

@Override
public void onMapLongClick(@NonNull LatLng latLng) {
	lat.setText(String.valueOf(latLng.latitude));
	lon.setText(String.valueOf(latLng.longitude));
	mMap.clear();
	LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
	mMap.addMarker(new MarkerOptions().position(mexico).title(""));
	mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
}
}