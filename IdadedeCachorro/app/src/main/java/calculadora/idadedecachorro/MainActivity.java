package calculadora.idadedecachorro;

// Importações necessárias para controle de visibilidade e componentes da UI
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    // Declaração das views (componentes visuais) usadas na activity
    private TextView textViewResultado;
    private EditText editTextValor;
    private MaterialButton materialButtonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML que essa Activity vai usar
        setContentView(R.layout.activity_main);

        // Inicializa as views vinculando aos componentes do layout
        initViews();

        // Define o que acontece quando o botão calcular é clicado
        materialButtonCalcular.setOnClickListener(v ->{
            // Pega o texto digitado no campo de entrada
            String valorDigitado = editTextValor.getText().toString();

            // Verifica se o texto não está vazio nem em branco
            if (!valorDigitado.isEmpty() && !valorDigitado.isBlank()){
                try {
                    // Tenta converter o valor digitado para inteiro
                    int valorConvertido = Integer.parseInt(valorDigitado);
                    // Calcula a idade humana (exemplo: multiplicando por 7)
                    int resultadoCalculado = (valorConvertido * 7);

                    // Mostra o resultado na TextView
                    textViewResultado.setText("Convertida para idade humana é : "+ resultadoCalculado + " Anos");

                    // Se o resultado não estiver visível, torna visível
                    if (textViewResultado.getVisibility()!= VISIBLE)
                        textViewResultado.setVisibility(VISIBLE);

                } catch (NumberFormatException e) {
                    // Caso a conversão falhe, mostra um toast de erro para o usuário
                    Toast.makeText(MainActivity.this, "Erro: Não foi possivel calcular, tente Novamente!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Caso o campo esteja vazio ou só com espaços, mostra mensagem informando
                Toast.makeText(MainActivity.this, "Somente permitido número", Toast.LENGTH_SHORT).show();
            }
        });

        // Quando o usuário clicar no campo de texto para digitar, esconde o resultado antigo
        editTextValor.setOnClickListener(v -> {
            textViewResultado.setVisibility(GONE);
        });
    }

    // Método para inicializar as views usando findViewById
    private void initViews() {
        textViewResultado = findViewById(R.id.textViewResultado);
        editTextValor = findViewById(R.id.editTextIdade);
        materialButtonCalcular = findViewById(R.id.btnCalculadora);
    }
}