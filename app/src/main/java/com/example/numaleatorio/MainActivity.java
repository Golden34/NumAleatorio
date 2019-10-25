package com.example.numaleatorio;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.numaleatorio.R.color.colorAccent;
import static com.example.numaleatorio.R.color.colorGreen;
import static com.example.numaleatorio.R.color.colorYellow;

public class MainActivity extends AppCompatActivity {

    public int topex = 10;
    public int topey = 10;
    public int ini = 15;
    public int fin = 72;
    public int adi;
    public TextView tv[];
    public int total;
    int colorRed;
    int colorYel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorRed = getResources().getColor(colorAccent);
        colorYel = getResources().getColor(colorYellow);

        total = (topey*topex);
        tv = new TextView[total];

    }

    public void bPulsado(View v) {
        //entrada de inicio y fin

        //deducir la cantida de numero entre medias con ellos incluidos
        int dif = fin - ini;  // 72 - 34 = 38
        int r = dif%7;
        int z = ini;

        LinearLayout Layout_XML = findViewById(R.id.xml_layout);
        Layout_XML.setPadding(10, 10, 10, 10);
        for (int y = 0; y < topey; y++) {
            LinearLayout layout_y = GeneraLinearLayout(LinearLayout.HORIZONTAL);
            for (int x = 0; x < topex; x++) {
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
        if (posi<10) cero = "  0"; else cero = "  ";
        String sNum = cero + posi;
        text.setText(sNum);
        text.setTextSize(22);
        text.setGravity(Gravity.START);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setBackgroundColor(colorRed);
        text.setTextColor(colorYel);
        text.setPadding(2, 2, 2, 2);
        text.setOnClickListener(
                View.OnClickListener()
                onClick(text));
//                    public void onClick(View v)
//                    {
//                        //String sNum = (TextView)v.getTag();
//                    }
//                }
//        );

        tv[posi] = text;
        onClick(text);
        return text;
    }

    // este View no es el pulsado, por ahora, es la prueba
    public void bPulsado2(View view) {
        for (int z = 1; z < total; z++) {
            TextView text = (tv[z]);
            if (z < ini || z > fin) tv[z].setVisibility(View.INVISIBLE);

        }
    }

    public void onClick(TextView Caja_Tocada){
        Toast.makeText(this," ha tocado el numero ", Toast.LENGTH_LONG);
    }

    /*
        En el Onclick tiene que ver si ha acertado:
        Si sí ha acertado -->  intent Festejos
        Si no ha acertado -->  solo tiene que ocultar desde el ini o el fin
        hacia el numero que ha dicho dejando en la otra parte el numero a adivinar
     */
}
