package com.example.appavaliacao2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etValor;
    private NumberPicker npQuantidade;
    private Button btCalcular, btLimpar;
    private TextView txtResultado, txtHistorico;

    DecimalFormat df = new DecimalFormat("0.00");
    ArrayList<Historico> historico;
    AlertDialog.Builder dialogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        historico = new ArrayList<Historico>();
        dialogo = new AlertDialog.Builder(MainActivity.this);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
            }
        });
        txtHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historico();
            }
        });
    }

    public ArrayList<Historico> getHistorico(){
        return historico;
    }

    public void addHistorico(double total, double resultado, int quantidade){
        getHistorico().add(new Historico(total, resultado, quantidade));
    }

    private void iniciarComponentes() {
        etValor = findViewById(R.id.etQuest1);
        npQuantidade = findViewById(R.id.npQuest2);
        btCalcular = findViewById(R.id.btCalcular);
        btLimpar = findViewById(R.id.btLimpar);
        txtResultado = findViewById(R.id.txtResultado);
        txtHistorico = findViewById(R.id.txtHistorico);
        npQuantidade.setMaxValue(20);
        npQuantidade.setMinValue(0);
    }

    public void calcular() {
        if (etValor.getText().toString().equals("") || npQuantidade.getValue() == 0) {
            Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            if (npQuantidade.getValue() == 1) {
                Toast.makeText(MainActivity.this, "Divida por mais pessoas", Toast.LENGTH_SHORT).show();
            } else {
                double valor = Double.parseDouble(etValor.getText().toString());
                int quant = npQuantidade.getValue();
                double resultado = valor / quant;
                resultado = Double.parseDouble(df.format(resultado).replace(",", "."));
                txtResultado.setText("Resultado: R$" + resultado);
                addHistorico(valor, resultado, quant);
            }
        }
    }

    public void limpar() {
        etValor.setText("0");
        npQuantidade.setValue(0);
    }

    public void historico(){
        dialogo.setTitle("Historico");
        String mensagem = "";
        for (int i = 0; i < historico.size(); i++){
            mensagem = mensagem + "Preço total: R$" + historico.get(i).getTotal() + ", quantidade de pessoas: " + historico.get(i).getQuantidade() + ", resultado: R$" + historico.get(i).getResultado() + ";\n\n";
        }
        dialogo.setMessage(mensagem).setNeutralButton("Ok", null).show();
    }
}