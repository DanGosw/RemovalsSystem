package com.dannyboy.removalssystem2;
	
	import static android.app.Activity.RESULT_OK;
	import androidx.annotation.Nullable;
	import androidx.fragment.app.Fragment;
	
	import android.annotation.SuppressLint;
	import android.content.Intent;
	import android.graphics.Bitmap;
	import android.graphics.drawable.BitmapDrawable;
	import android.net.Uri;
	import android.os.Bundle;
	import android.provider.MediaStore;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.ImageView;
	import android.widget.TextView;
	import android.widget.Toast;
	
	import java.io.ByteArrayOutputStream;


public class CreateUser extends Fragment {

ImageView img;
EditText name, last, mail, pass, repe;
DataBaseHandler db;
View vista;
TextView df;

Button add_worker;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	vista = inflater.inflate(R.layout.activity_create_user, container, false);
	df = vista.findViewById(R.id.w);
	img = vista.findViewById(R.id.user_image);
	name = vista.findViewById(R.id.txtname);
	last = vista.findViewById(R.id.txtlast);
	mail = vista.findViewById(R.id.txtmail);
	pass = vista.findViewById(R.id.txtpass);
	repe = vista.findViewById(R.id.txtrepet);
	add_worker = vista.findViewById(R.id.btnlog);
	db = new DataBaseHandler(getContext(), "addWorker.db", null, 3);
	img.setOnClickListener(view -> LoadImage());
	add_worker.setOnClickListener(view -> {
		try {
			String n = name.getText().toString().trim();
			String l = last.getText().toString().trim();
			String m = mail.getText().toString().trim();
			String p = pass.getText().toString().trim();
			String r = repe.getText().toString().trim();
			
			if (!n.isEmpty() && !l.isEmpty() && !m.isEmpty() && !p.isEmpty() && !r.isEmpty()) {
				if (!pass.getText().toString().equals(repe.getText().toString())) {
					Toast.makeText(getContext(), "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
				} else {
					db.insertImage(imageViewToByte(img), n, l, m, p);
					Toast.makeText(getContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
					name.setText("");
					last.setText("");
					mail.setText("");
					pass.setText("");
					repe.setText("");
					img.setImageResource(R.drawable.user_img);
				}
			} else {
				Toast.makeText(getContext(), "Quizá debas llenar los registros ", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			df.setText(e.getMessage());
			Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	});
	return vista;
}
	private  byte [] imageViewToByte(ImageView image){
		Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
		return b.toByteArray();
	}
	
@SuppressLint("IntentReset")
private void LoadImage() {
		@SuppressLint("IntentReset") Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setType("image/*");
		startActivityForResult(Intent.createChooser(intent, "Seleccione una app"), 10);
}

@Override
public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_OK){
		assert data != null;
		Uri path = data.getData();
		img.setImageURI(path);
	}
}
}