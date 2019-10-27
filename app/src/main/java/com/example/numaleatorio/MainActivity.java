package com.example.numaleatorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.numaleatorio.R.color.colorBurdeos;
import static com.example.numaleatorio.R.color.colorYellow;

public class MainActivity extends AppCompatActivity {

    public int tope_layout_x = 10;
    public int tope_layout_y = 10;
    public int numero_primero;
    public int numero_ultimo;
    public int numero_inferior;
    public int numero_superior;
    public int numero_adivinar;
    public TextView matriz_textViews[];
    public int total_numeros;
    public int colorRed;
    public int colorYel;
    public int intentos;
    public TextView tvIntentos;
    public boolean acertado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorRed = getResources().getColor(colorBurdeos);
        colorYel = getResources().getColor(colorYellow);

        total_numeros = (tope_layout_y * tope_layout_x);
        matriz_textViews = new TextView[total_numeros];
        numero_primero = 0;
        numero_ultimo = total_numeros - 1;
        numero_inferior = numero_primero;
        numero_superior = numero_ultimo;
        intentos = 0;

        double nAle = Math.random()*tope_layout_x*tope_layout_y;
        numero_adivinar = (int)nAle;

        tvIntentos = findViewById(R.id.intentos);

        Empezar();

    }

    public void Empezar() {
        //entrada de inicio y numero_ultimo

        LinearLayout Layout_XML = findViewById(R.id.xml_layout);
        Layout_XML.setPadding(10, 10, 10, 10);
        for (int y = 0; y < tope_layout_y; y++) {
            LinearLayout layout_y = GeneraLinearLayout(LinearLayout.HORIZONTAL);
            for (int x = 0; x < tope_layout_x; x++) {
                LinearLayout layout_x = GeneraLinearLayout(LinearLayout.HORIZONTAL);
                layout_x.addView(GeneraCaja(y, x));
                layout_y.addView(layout_x);
            }
            Layout_XML.addView(layout_y);
        }
    }

    public LinearLayout GeneraLinearLayout(int Orientacion){
        LinearLayout.LayoutParams
                lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0F);
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(Orientacion);
        if (Orientacion == LinearLayout.HORIZONTAL ){
            lp.setMargins(15, 7, 15, 7);
            LL.setLayoutParams(lp);
        }
        LL.setLayoutParams(lp);
        return LL;
    }

    public TextView GeneraCaja(int ty, int tx){
        int posi = (ty*10 + tx);
        String cero;
        TextView text = new TextView(this);
        if (posi<10) cero = "0"; else cero = "";
        String sNum = cero + posi;
        text.setText(sNum);
        if (posi>99)
            text.setTextSize(15);
        else
            text.setTextSize(22);
        text.setGravity(Gravity.START);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //text.setBackgroundColor(colorRed);

        Resources res = getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.redon, null);
        text.setBackgroundDrawable(drawable);

        text.setTextColor(colorYel);
        text.setPadding(3, 2, 3, 2);
        text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onTick((TextView)v);
            }
        });

        matriz_textViews[posi] = text;

        return text;
    }

    // este View no importa
    public void bVolver(View view) {
        // Ir a la actividad via Intent
        Intent intent = new Intent(this, PresentaActivity.class);
        startActivity(intent);
    }

    public void onTick(TextView Caja_Tocada) {
        String sNumero_tocado = Caja_Tocada.getText().toString();

        if (!acertado) {
            intentos++;
            tvIntentos.setText(String.valueOf("Intentos: " + intentos));
        }
        int numero_tocado = Integer.parseInt(sNumero_tocado);
        if (numero_tocado == numero_adivinar) {
            Toast.makeText(this, "Has acertado el numero: " + sNumero_tocado, Toast.LENGTH_SHORT).show();
            // Quita todas las cajas excepto el numero ACERTADO!!
            numero_inferior = numero_tocado;
            numero_superior = numero_tocado;
            acertado = true;
        } else if (numero_tocado < numero_adivinar) {
            // Calcula el nuevo tope/limite inferior y le suma
            // (+) 1 porque ese numero le ha fallado y ya no vale,
            // por eso se excluye del tope inferior, sumando uno.
            numero_inferior = numero_tocado + 1;
        } else if (numero_tocado > numero_adivinar) {
            // Calcula el nuevo tope/limite superior y le resta
            // (-) 1 porque ese numero le ha fallado y ya no vale,
            // por eso se excluye del tope superior, restando uno.
            numero_superior = numero_tocado - 1;
        }
        OcultarCajasSobrantes();
    }

    public void OcultarCajasSobrantes(){
        //Oculta las cajas de los numeros que por lógica ya no pueden ser elegidos
        for (int num_no_sera_visible = 0;
                 num_no_sera_visible < total_numeros;
                 num_no_sera_visible++)
        {
            if (num_no_sera_visible < numero_inferior || num_no_sera_visible > numero_superior) {
                matriz_textViews[num_no_sera_visible].setVisibility(View.INVISIBLE);
            }
        }
    }
}
