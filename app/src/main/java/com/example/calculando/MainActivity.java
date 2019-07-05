package com.example.calculando;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton sexualidade;
    private EditText idade;
    private EditText altura;
    private EditText peso;
    private EditText alturaPai;
    private EditText alturaMae;
    private TextView resultadoIn;
    private TextView resultadoCm;
    private Button calcular;
    private double resultado;
    private Pessoa pessoa;
    private LinearLayout layoutResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008B8B")));

        calcular = (Button) findViewById(R.id.btnCalcular);
        sexualidade = (RadioButton) findViewById(R.id.rbtnMasculino);
        idade = (EditText) findViewById(R.id.campoIdade);
        altura = (EditText) findViewById(R.id.campoAltura);
        peso = (EditText) findViewById(R.id.campoPeso);
        alturaPai = (EditText) findViewById(R.id.campoAlturaPai);
        alturaMae = (EditText) findViewById(R.id.campoAlturaPai);
        resultadoIn = (TextView) findViewById(R.id.txtResultadoIn);
        resultadoCm = (TextView) findViewById(R.id.txtResultadoCm);
        layoutResultado = (LinearLayout) findViewById(R.id.layoutResul);
        layoutResultado.setVisibility(View.INVISIBLE);

        calcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculando();
                layoutResultado.setVisibility(View.VISIBLE);
            }
        });
    }

    public void calculando(){
        if(sexualidade.isChecked()) {
            pessoa = new Masculino();
        } else {
            pessoa = new Feminino();
        }

        pessoa.setIdade(String.valueOf(idade.getText()));
        pessoa.setAltura(String.valueOf(altura.getText()));
        pessoa.setPeso(String.valueOf(peso.getText()));
        pessoa.setAlturaPai(String.valueOf(alturaPai.getText()));
        pessoa.setAlturaMae(String.valueOf(alturaMae.getText()));

        resultado = pessoa.calcular(pessoa.getIdade());
        DecimalFormat df = new DecimalFormat("#####.####");
        df.setRoundingMode(RoundingMode.UP);
        resultadoIn.setText(df.format(resultado)+" in");
        resultadoCm.setText(df.format(pessoa.transformaEmCm(resultado))+" cm");

    }
}
