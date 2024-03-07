package com.example.app_jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView tvJogoOponente;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.dropdownOpcaoDeJogo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.possibilidadeDeJogo,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        tvJogoOponente = findViewById(R.id.jogoOponente);
        tvResultado = findViewById(R.id.resultado);
    }

    public void onBtnJogarClick (View view) {

        String jogoUsuario = spinner.getSelectedItem().toString();
        if (jogoUsuario.equals("Selecione uma opção")) {
            tvResultado.setText("Selecione uma opção válida");
            tvResultado.setVisibility(View.VISIBLE);
        } else {
            String textoResultado = "";
            String jogoOponente = sortearJogo();

            int resultado = verificaVitoria(jogoUsuario, jogoOponente);

            if (resultado == 0){
                textoResultado = "Empatou";
            }

            if (resultado == 1){
                textoResultado = "Você venceu";
            }

            if (resultado == 2){
                textoResultado = "Você perdeu";
            }

            tvJogoOponente.setText(jogoOponente);
            tvJogoOponente.setVisibility(View.VISIBLE);

            tvResultado.setText(textoResultado);
            tvResultado.setVisibility(View.VISIBLE);
        }
    }

    public int verificaVitoria(String usuario, String oponente){
        if (usuario.equals("Pedra")) {
            if (oponente.equals("Pedra")) {
                return 0;
            }
            if (oponente.equals("Papel")) {
                return 2;
            }
            if (oponente.equals("Tesoura")) {
                return 1;
            }
        }
        if (usuario.equals("Papel")){
            if (oponente.equals("Pedra")){
                return 1;
            }
            if (oponente.equals("Papel")){
                return 0;
            }
            if (oponente.equals("Tesoura")){
                return 2;
            }
        }
        if (usuario.equals("Tesoura")) {
            if (oponente.equals("Pedra")) {
                return 2;
            }
            if (oponente.equals("Papel")) {
                return 1;
            }
            if (oponente.equals("Tesoura")) {
                return 0;
            }
        }
        return 0;
    }

    public String sortearJogo () {
        String[] jogos = {"Pedra", "Papel", "Tesoura"};
        int randomInt = (int) (Math.random() * (3));
        return jogos[randomInt];
    }
}