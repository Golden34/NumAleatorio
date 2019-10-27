package com.example.numaleatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class PresentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenta);
    }

    public void bEmpezar(View view) {
        // Ir a la actividad via Intent
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void bSalir(View view) {
        finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }
}
