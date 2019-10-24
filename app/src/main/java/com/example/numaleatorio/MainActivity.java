package com.example.numaleatorio;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int ini = 1;
    public int fin = 72;
    public int adi;
    public int tv[10,10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        TextView titleView = new TextView(this);
        titleView.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        titleView.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        titleView.setText("Hallo Welt!");
        titleView.setVisibility(View.VISIBLE);

        FrameLayout myRoot = findViewById(R.id.cajroot);
        LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.VERTICAL);
        a.addView(titleView);
        //a.addView(titleView);

        myRoot.addView(a); */

    }

    public void bPulsado(View v) {
        //entrada de inicio y fin


        //deducir la cantida de numero entre medias con ellos incluidos
        int dif = fin - ini;  // 72 - 34 = 38
        int r = dif%7;
        int z = ini;

//        LinearLayout layout_y = findViewById(R.id.dyn_layoutY);
//        LinearLayout layout_x = findViewById(R.id.dyn_layoutX);

        LinearLayout Layout_XML = findViewById(R.id.xml_layout);
        for (int y = 0; y < 10; y++) {
            LinearLayout layout_y = GeneraLinearLayout(LinearLayout.HORIZONTAL);
            for (int x = 0; x < 10; x++) {
                LinearLayout layout_x = GeneraLinearLayout(LinearLayout.VERTICAL);
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
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LL.setPadding(10, 10, 10, 10);
        }

        return LL;
    }
    public TextView GeneraCaja(int ty, int tx){
        TextView text = new TextView(this);
        tv[ty,tv] = text.getId() ;
        text.setText("" + i);
        text.setTextSize(12);
        text.setGravity(Gravity.LEFT);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setBackgroundColor(R.color.colorPrimary);
        return text;
    }



    /*
        En el Onclick tiene que ver si ha acertado:
        Si sí ha acertado -->  intent Festejos
        Si no ha acertado -->  solo tiene que ocultar desde el ini o el fin
        hacia el numero que ha dicho dejando en la otra parte el numero a adivinar
     */
}
