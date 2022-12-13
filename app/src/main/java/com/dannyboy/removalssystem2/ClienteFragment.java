package com.dannyboy.removalssystem2;

	import androidx.fragment.app.Fragment;
	import android.media.Ringtone;
	import android.media.RingtoneManager;
	import android.net.Uri;
	import android.os.Bundle;
	import android.text.InputFilter;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.EditText;
	import android.widget.ImageButton;
	import android.widget.Toast;
	import com.android.volley.Request;
	import com.android.volley.toolbox.StringRequest;
	import org.json.JSONException;
	import org.json.JSONObject;

public class ClienteFragment extends Fragment {

View vista;
EditText dni, nom, pat, mat, cel;
ImageButton cons;
StringRequest postRequest;


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	 vista = inflater.inflate(R.layout.activity_cliente_fragment, container, false);

	 dni = vista.findViewById(R.id.txt_dni);
	 nom = vista.findViewById(R.id.txt_nom);
	 pat = vista.findViewById(R.id.txt_pat);
	 mat = vista.findViewById(R.id.txt_mat);
	 cel = vista.findViewById(R.id.txt_cel);
	 cons = vista.findViewById(R.id.search_customer);
	 dni.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)} );
	 
	 cons.setOnClickListener(view -> {
		if (dni.getText().length() != 8){
			Toast.makeText(getContext(), "Dni no valido", Toast.LENGTH_SHORT).show();
			return;
		}
		ConsumeApi();
	 });
	return  vista;
}

public void ConsumeApi() {
	String a_principal = "https://apiperu.dev/api/dni/";
	String token = "k4d2956bd531ab61d44f4fa07304b20e13913815";
	postRequest = new StringRequest(Request.Method.GET, a_principal  +dni.getText()+"?api_token="+ token, response -> {
		
		try {
			JSONObject jsonObject = new JSONObject(response);
			nom.setText(jsonObject.getString("name"+ "n"));
			pat.setText(jsonObject.getString("first_name"));
			mat.setText(jsonObject.getString("last_name"));
			
			Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(getContext(), uri);
			r.play();
			
		} catch (JSONException ignored) {
			Toast.makeText(getContext(), "Error en la consulta", Toast.LENGTH_SHORT).show();
		}
	}, error -> Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show());

}
}