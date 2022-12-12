package com.dannyboy.removalssystem2;

	import androidx.fragment.app.Fragment;
	import android.os.Bundle;
	import android.os.StrictMode;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.ImageButton;
	import android.widget.Toast;
	
	import com.android.volley.Request;
	import com.android.volley.Response;
	import com.android.volley.VolleyError;
	import com.android.volley.toolbox.StringRequest;
	
	import org.json.JSONException;
	import org.json.JSONObject;
	import org.jsoup.Connection;


public class ClienteFragment extends Fragment {

View vista;
EditText dni, nom, pat, mat, cel;
ImageButton cons;

private String api = "";
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	 vista = inflater.inflate(R.layout.activity_cliente_fragment, container, false);
	String a_principal = "https://api.sunat.cloud/ruc/20454910215";
	api = a_principal + "persons/";
	 dni = vista.findViewById(R.id.txt_dni);
	 nom = vista.findViewById(R.id.txt_nom);
	 pat = vista.findViewById(R.id.txt_pat);
	 mat = vista.findViewById(R.id.txt_mat);
	 cel = vista.findViewById(R.id.txt_cel);
	 cons = vista.findViewById(R.id.search_customer);
	 
	 cons.setOnClickListener(view -> ConsumeApi());
	return  vista;
}

public void ConsumeApi() {
	StringRequest postRequest = new StringRequest(Request.Method.GET, api + dni.getText(), response -> {
		
		try {
			JSONObject jsonObject = new JSONObject(response.toString());
			nom.setText(jsonObject.getString("name"));
			pat.setText(jsonObject.getString("first_name"));
			mat.setText(jsonObject.getString("last_name"));
		} catch (JSONException ignored) {
			Toast.makeText(getContext(), "Error en la consulta", Toast.LENGTH_SHORT).show();
		}
	}, error -> Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show());
}
}