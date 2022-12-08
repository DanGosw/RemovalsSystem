package com.dannyboy.removalssystem2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

	DrawerLayout mDrawerLayout;
	NavigationView navigationView;
	Toolbar toolbar;
	ActionBarDrawerToggle toggle;
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main_menu);
	
	navigationView = (NavigationView)findViewById(R.id.nav_view);
	navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
	navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.purple_200)));

	mDrawerLayout = findViewById(R.id.drawerLayout);
	navigationView = findViewById(R.id.nav_view);
	toolbar = findViewById(R.id.toolbar);
	
	getSupportFragmentManager().beginTransaction().add(R.id.content , new HomeFragment()).commit();
	setTitle("Inicio");

	setSupportActionBar(toolbar);
	navigationView.setNavigationItemSelectedListener(this);

	toggle = new ActionBarDrawerToggle(MainMenu.this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
	mDrawerLayout.addDrawerListener(toggle);
	
	setToolbar();
	}


private void setToolbar() {
	toolbar = findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}


@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item) {
	selectItemNav(item);
	return true;
}

@SuppressLint("NonConstantResourceId")
private void selectItemNav(MenuItem item) {
	
	FragmentManager fm = getSupportFragmentManager();
	FragmentTransaction ft = fm.beginTransaction();
	switch (item.getItemId()){
		case R.id.home:
			ft.replace(R.id.content, new HomeFragment()).commit();
			break;
		case R.id.cliente:
			ft.replace(R.id.content, new ClienteFragment()).commit();
			break;
		case R.id.empleados:
			ft.replace(R.id.content, new EmpleadosFragment()).commit();
			break;
		case R.id.servicios:
			ft.replace(R.id.content, new ServiciosFragment()).commit();
			break;
		case R.id.solicitud:
			ft.replace(R.id.content, new SolicitudFragment()).commit();
		break;
		case R.id.vehiculos:
			ft.replace(R.id.content, new VehiculosFragment()).commit();
		break;
		case R.id.salir:
			salir();
		break;
	}
	setTitle(item.getTitle());
	mDrawerLayout.closeDrawers();
}

public  void salir(){
	MainActivity.changeCondittion(MainMenu.this, false);
	Intent i = new Intent(MainMenu.this, MainActivity.class);
	startActivity(i);
	finish();
}

@Override
public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
	super.onPostCreate(savedInstanceState, persistentState);
	toggle.syncState();
}

@Override
public void onConfigurationChanged(@NonNull Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		toggle.onConfigurationChanged(newConfig);
}

@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
	if(toggle.onOptionsItemSelected(item))	{
		return  true;
	}
	return super.onOptionsItemSelected(item);
	}
}