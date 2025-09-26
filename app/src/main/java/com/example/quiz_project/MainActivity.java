package com.example.quiz_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtQuestion;
    ImageView imgQuestion;
    Button btnOption1, btnOption2, btnOption3, btnNext;

    // Perguntas
    String[] questions = {
            "Qual é a capital do Brasil?",
            "Quanto é 2 + 2?",
            "Quem descobriu o Brasil?"
    };

    // Opções de resposta
    String[][] options = {
            {"São Paulo", "Brasília", "Rio de Janeiro"},
            {"3", "4", "5"},
            {"Pedro Álvares Cabral", "Cristóvão Colombo", "Dom Pedro I"}
    };

    // Respostas corretas (índice)
    int[] correctAnswers = {1, 1, 0};

    // Imagens para cada pergunta (colocar na pasta res/drawable)
    int[] images = {
            R.drawable.brasilia,  // imagem da primeira pergunta
    };
    int currentQuestion = 0; // começa na primeira pergunta
    int score = 0; // contador de pontos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Certifique-se que o XML chama activity_main

        // Conectar os componentes do XML
        txtQuestion = findViewById(R.id.txtQuestion);
        imgQuestion = findViewById(R.id.imgQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnNext = findViewById(R.id.btnNext);

        // Carregar primeira pergunta
        loadQuestion();

        // Clique das opções
        btnOption1.setOnClickListener(v -> checkAnswer(0));
        btnOption2.setOnClickListener(v -> checkAnswer(1));
        btnOption3.setOnClickListener(v -> checkAnswer(2));

        // Clique no botão Próxima
        btnNext.setOnClickListener(v -> {
            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                loadQuestion();
            } else {
                Toast.makeText(this, "Quiz finalizado! Pontos: " + score, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Carrega pergunta atual
    private void loadQuestion() {
        txtQuestion.setText(questions[currentQuestion]);
        imgQuestion.setImageResource(images[currentQuestion]);
        btnOption1.setText(options[currentQuestion][0]);
        btnOption2.setText(options[currentQuestion][1]);
        btnOption3.setText(options[currentQuestion][2]);
    }

    // Verifica resposta
    private void checkAnswer(int selectedOption) {
        if (selectedOption == correctAnswers[currentQuestion]) {
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Errado!", Toast.LENGTH_SHORT).show();
        }
    }
}