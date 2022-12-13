package com.dannyboy.removalssystem2;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener{

MapView mMapView;
GoogleMap mMap;
FusedLocationProviderClient fusedLocationProviderClient;
View vista;
TextView lat;
Location loc;
TextView lon;

@SuppressLint("StaticFieldLeak")
static TextView ubi;
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
	ubi = vista.findViewById(R.id.ubi);
	fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(requireContext());
	if (mMapView != null) {
		mMapView.onCreate(null);
		mMapView.onResume();
		mMapView.getMapAsync(this);
	}
	if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
		ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
	}
}
@Override
public void onMapReady(@NonNull GoogleMap googleMap) {
	mMap = googleMap;
	this.mMap.setOnMapClickListener(this);
	this.mMap.setOnMapLongClickListener(this);
	
	LatLng pe = new LatLng(-9.125574839157506, -78.51786661473645);
	mMap.addMarker(new MarkerOptions().position(pe).title("Aqui vivo :D"));
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
	Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
	List <Address> direct;
	try {
		direct = geocoder.getFromLocation(latLng.latitude, latLng.longitude,1);
		ubi.setText(direct.get(0).getAddressLine(0));
	} catch (IOException e) {
		e.printStackTrace();
	}
}

@Override
public void onMapLongClick(@NonNull LatLng latLng) {
	lat.setText(String.valueOf(latLng.latitude));
	lon.setText(String.valueOf(latLng.longitude));
	mMap.clear();
	LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
	mMap.addMarker(new MarkerOptions().position(mexico).title(""));
	mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
	
	Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
	List <Address> direct;
	try {
		direct = geocoder.getFromLocation(latLng.latitude, latLng.longitude,1);
		ubi.setText(direct.get(0).getAddressLine(0));
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}