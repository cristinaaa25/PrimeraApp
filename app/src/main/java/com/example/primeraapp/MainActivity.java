package com.example.primeraapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView iv_image;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<Intent> activityResultLauncherText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button aceptar = findViewById(R.id.aceptar);
        Button cancel = findViewById(R.id.cancelar);
        EditText nom = findViewById(R.id.editTextText);
        TextView textNom = findViewById(R.id.textView3);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nom.getText().toString();
                textNom.setText("Hola " + name + "! que tal el día?");
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom.setText("");
                textNom.setText("Esperant Nom");
            }
        });

        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData()!=null) {
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    iv_image.setImageBitmap(imageBitmap);
                }
            }
        });

        activityResultLauncherText=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK){
                            String text = o.getData().getStringExtra("text_retorn");
                            Log.v("proves", text);
                        }
                    }
                });
    }

   public void obrirSegona(View view) {
        EditText nom = findViewById(R.id.editTextText);
        String text = nom.getText().toString();
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra("text_enviat", text);
        //if(intent.resolveActivity(getPackageManager()) != null) {
          startActivity(intent);
        //}
    }

    public void obrirLlistat(View view) {
        Intent intent = new Intent(getApplicationContext(), LlistatActivity.class);
        startActivity(intent);
    }

    public void obrirSharedPerf(View view) {
        Intent intent = new Intent(getApplicationContext(), SharedPrefActivity.class);
        startActivity(intent);
    }

    public void obreSegona(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        activityResultLauncherText.launch(intent);

    }
    public void takePicture(View view) {
        Intent takePictureIntent = new
                Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (takePictureIntent.resolveActivity(getPackageManager())!=null){
        try {
            activityResultLauncher.launch(takePictureIntent);
        }catch(ActivityNotFoundException e)
        {
            Log.v("CameraText", "Camera not available");
        }
        //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); DEPRECATED!
        // }

    }

    public void compartirText(View view) {
        EditText nom = findViewById(R.id.editTextText);
        String text = nom.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, "Compartir con"));
    }

    public void enviarEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Texto a compartir");
        intent.putExtra(Intent.EXTRA_EMAIL,
                "cristinadelaiglesia2@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
        startActivity(intent);
    }

    public void obrirWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    public void obrirMaps(View view) {
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}