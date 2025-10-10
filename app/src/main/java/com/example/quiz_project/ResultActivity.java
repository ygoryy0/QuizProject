package com.example.quiz_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class ResultActivity extends AppCompatActivity {

    TextView txtScore;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Conectar os IDs do XML
        // CERTIFIQUE-SE DE QUE ESTES IDs ESTÃO NO SEU activity_result.xml
        txtScore = findViewById(R.id.txtScore);
        btnRestart = findViewById(R.id.btnRestart);

        // --- Lógica para buscar e exibir a pontuação ---
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int score = extras.getInt("SCORE_FINAL", 0);
            int total = extras.getInt("TOTAL_QUESTIONS", 10);

            txtScore.setText("Parabéns! Você acertou " + score + " de " + total + " perguntas.");
        }

        // --- Lógica do Botão 'Reiniciar Quiz' ---
        btnRestart.setOnClickListener(v -> {
            // Volta para a MainActivity (a tela de início do Quiz)
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}