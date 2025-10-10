package com.example.quiz_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView; // Manter o ImageView, mas ele não será usado
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtQuestion;
    ImageView imgQuestion; // Mantido para evitar erros no findViewById, mas não será usado
    Button btnOption1, btnOption2, btnOption3;

    // --- 10 PERGUNTAS BÁSICAS ---
    String[] questions = {
            "1. Qual é a cor do céu em um dia sem nuvens?",
            "2. Quantos dias tem uma semana?",
            "3. Qual animal faz 'Au Au'?",
            "4. Qual o nome do nosso planeta?",
            "5. Qual número vem depois do 9?",
            "6. O que usamos para ver as horas?",
            "7. Qual a capital de Portugal?",
            "8. Qual é o oposto de 'quente'?",
            "9. Qual o maior oceano do mundo?",
            "10. Onde o Sol nasce?",
    };

    String[][] options = {
            {"Verde", "Azul", "Amarelo"},
            {"5", "7", "10"},
            {"Gato", "Cachorro", "Pássaro"},
            {"Marte", "Terra", "Vênus"},
            {"8", "10", "11"},
            {"Um copo", "Um relógio", "Uma régua"},
            {"Porto", "Coimbra", "Lisboa"},
            {"Frio", "Molhado", "Seco"},
            {"Atlântico", "Índico", "Pacífico"},
            {"No Oeste", "No Sul", "No Leste"},
    };

    // Respostas corretas (índice 0, 1 ou 2)
    int[] correctAnswers = {1, 1, 1, 1, 1, 1, 2, 0, 2, 2};

    // REMOVIDA a array 'images'

    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar os componentes do XML
        txtQuestion = findViewById(R.id.txtQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);

        loadQuestion();

        // Clique das opções e avanço automático
        btnOption1.setOnClickListener(v -> checkAnswer(0));
        btnOption2.setOnClickListener(v -> checkAnswer(1));
        btnOption3.setOnClickListener(v -> checkAnswer(2));
    }

    // Carrega pergunta atual (sem carregar imagem)
    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            txtQuestion.setText(questions[currentQuestion]);

            // REMOVIDA a linha que carregava a imagem

            btnOption1.setText(options[currentQuestion][0]);
            btnOption2.setText(options[currentQuestion][1]);
            btnOption3.setText(options[currentQuestion][2]);
        }
    }

    // Verifica resposta e avança
    private void checkAnswer(int selectedOption) {
        if (selectedOption == correctAnswers[currentQuestion]) {
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Errado!", Toast.LENGTH_SHORT).show();
        }

        // Lógica de AVANÇO AUTOMÁTICO
        if (currentQuestion < questions.length - 1) {
            currentQuestion++;
            loadQuestion();
        } else {
            finishQuiz();
        }
    }

    // Função para ir para a tela de Resultado
    private void finishQuiz() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("SCORE_FINAL", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.length);
        startActivity(intent);
        finish();
    }
}