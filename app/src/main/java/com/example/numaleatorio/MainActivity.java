package com.example.numaleatorio;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.numaleatorio.R.color.colorAccent;
import static com.example.numaleatorio.R.color.colorBurdeos;
import static com.example.numaleatorio.R.color.colorYellow;

public class MainActivity extends AppCompatActivity {

    public int tope_layout_x = 10;
    public int tope_layout_y = 10;
    public int numero_primero = 0;
    public int numero_ultimo = 99;
    public int numero_inferior = numero_primero;
    public int numero_superior = numero_ultimo;
    public int numero_adivinar = 57;
    public TextView matriz_textViews[];
    public int total_numeros;
    int colorRed;
    int colorYel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorRed = getResources().getColor(colorBurdeos);
        colorYel = getResources().getColor(colorYellow);

        total_numeros = (tope_layout_y * tope_layout_x);
        matriz_textViews = new TextView[total_numeros];

    }

    public void bPulsado(View v) {
        //entrada de inicio y numero_ultimo

        //deducir la cantida de numero entre medias con ellos incluidos
        int dif = numero_ultimo - numero_primero;  // 72 - 34 = 38
        int r = dif%7;
        int z = numero_primero;

        LinearLayout Layout_XML = findViewById(R.id.xml_layout);
        Layout_XML.setPadding(10, 10, 10, 10);
        for (int y = 0; y < tope_layout_y; y++) {
            LinearLayout layout_y = GeneraLinearLayout(LinearLayout.HORIZONTAL);
            for (int x = 0; x < tope_layout_x; x++) {
                LinearLayout layout_x = GeneraLinearLayout(LinearLayout.HORIZONTAL);
                layout_x.addView(GeneraCaja(y, x));
                layout_y.addView(layout_x);
                z++;
            }
            Layout_XML.addView(layout_y);
        }
        System.out.println("**** Adding text view " + dif);
    }

    public LinearLayout GeneraLinearLayout(int Orientacion){
        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(Orientacion);
        if (Orientacion == LinearLayout.HORIZONTAL ){
            LL.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 3F));
        }
        else{
            LL.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 2F));
            LL.setBackgroundColor(colorAccent);
            LL.setPadding(10, 10, 10, 10);
        }


        return LL;
    }
    @SuppressLint("ResourceAsColor")
    public TextView GeneraCaja(int ty, int tx){
        int posi = (ty*10 + tx);
        String cero;
        TextView text = new TextView(this);
        if (posi<10) cero = "0"; else cero = "";
        String sNum = cero + posi;
        text.setText(sNum);
        text.setTextSize(22);
        text.setGravity(Gravity.START);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setBackgroundColor(colorRed);
        text.setTextColor(colorYel);
        text.setPadding(2, 2, 2, 2);
        text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onTick((TextView)v);
            }
        });

        matriz_textViews[posi] = text;

        return text;
    }

    // este View no es el pulsado, por ahora, es la prueba
    public void bPulsado2(View view) {
        OcultarCajasSobrantes();
    }

    public void onTick(TextView Caja_Tocada){
        String sNumero_tocado = Caja_Tocada.getText().toString();
        int numero_tocado = Integer.parseInt(sNumero_tocado);
        if (numero_tocado == numero_adivinar){
            Toast.makeText(this,"Has acertado el numero: " + sNumero_tocado, Toast.LENGTH_SHORT).show();
            numero_inferior = numero_tocado;
            numero_superior = numero_tocado;
            OcultarCajasSobrantes();
        } else if (numero_tocado < numero_adivinar){
            numero_inferior = numero_tocado + 1;
            OcultarCajasSobrantes();
        } else if (numero_tocado > numero_adivinar){
            numero_superior = numero_tocado - 1;
            OcultarCajasSobrantes();
        }
    }

    public void OcultarCajasSobrantes(){
        for (int z = 0; z < total_numeros; z++) {
            if (z < numero_inferior || z > numero_superior) matriz_textViews[z].setVisibility(View.INVISIBLE);
        }
    }
    /*
        En el Onclick tiene que ver si ha acertado:
        Si sí ha acertado -->  intent Festejos
        Si no ha acertado -->  solo tiene que ocultar desde el numero_primero o el numero_ultimo
        hacia el numero que ha dicho dejando en la otra parte el numero a adivinar
     */
}
