package com.dannyboy.removalssystem2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class CreateUserLogin extends AppCompatActivity {
ImageView img;
EditText name, last, mail, pass, repe;
DataBaseHandler db;
Button add_worker;
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_create_user_login);

	img = findViewById(R.id.user_image);
	name = findViewById(R.id.txtname);
	last = findViewById(R.id.txtlast);
	mail = findViewById(R.id.txtmail);
	pass = findViewById(R.id.txtpass);
	repe = findViewById(R.id.txtrepet);
	add_worker = findViewById(R.id.btnlog);
	
	db = new DataBaseHandler(this);

}
private  byte [] imageViewToByte(ImageView image){
	Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
	ByteArrayOutputStream b = new ByteArrayOutputStream();
	bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
	return b.toByteArray();
}

public  void nose(View view){
	try {
		String n = name.getText().toString().trim();
		String l = last.getText().toString().trim();
		String m = mail.getText().toString().trim();
		String p = pass.getText().toString().trim();
		String r = repe.getText().toString().trim();
		
		if (!n.isEmpty() && !l.isEmpty() && !m.isEmpty() && !p.isEmpty() && !r.isEmpty()) {
			if (!pass.getText().toString().equals(repe.getText().toString())) {
				Toast.makeText(this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
			} else {
				db.insertImage(imageViewToByte(img), n, l, m, p);
				Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
				name.setText("");
				last.setText("");
				mail.setText("");
				pass.setText("");
				repe.setText("");
				img.setImageResource(R.drawable.user_img);
				Intent ven=new Intent( CreateUserLogin.this, MainActivity.class);
				startActivity(ven);
				finish();
			}
		} else {
			Toast.makeText(this, "Quizá debas llenar los registros ", Toast.LENGTH_SHORT).show();
		}
	} catch (Exception e) {
		Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
	}
}
@SuppressLint("IntentReset")
public void LoadImage(View v) {
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