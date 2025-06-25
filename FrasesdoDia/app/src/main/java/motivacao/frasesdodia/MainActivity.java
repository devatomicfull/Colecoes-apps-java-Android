package motivacao.frasesdodia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn_Frase;
    private TextView textViewFrase;
    private String[] vetorArray;
    private Random random = new Random();

    // Outra forma que poderia ser usada:
    // -- Usando chaves {} para inicializar: os elementos dentro das chaves são os dados do vetor.
    //    O tamanho do vetor (array) será definido automaticamente pela quantidade de elementos fornecidos.
    //    Exemplo:
        private String[] vetorArray1 = new String[] {"Frase1", "Frase2", "Frase3", "Frase4", "Frase5"};
        private String[] vetorArray2 = {"Frase1", "Frase2", "Frase3", "Frase4", "Frase5"};

    // -- Também é possível declarar o tamanho do vetor com colchetes, mas sem inicializar os valores:
    //    Aqui, o vetor terá 5 posições (índices de 0 a 4), todas inicialmente com valor null.
        private String[] vetorArray3 = new String[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da tela

        initViews(); // Inicializa os componentes da interface (botão e TextView)

        // Carrega o array de frases do arquivo strings.xml (res/values/strings.xml)
        vetorArray = getResources().getStringArray(R.array.frases_motivacao);

        // Define o que acontece ao clicar no botão
        btn_Frase.setOnClickListener(v -> {
            // Se o TextView estiver invisível, torna-o visível
            if (textViewFrase.getVisibility() == View.GONE){
                textViewFrase.setVisibility(View.VISIBLE);
            }

            // Gera um número aleatório entre 0 e o tamanho do array de frases
            int valor_Aleatorio = random.nextInt(vetorArray.length);

            // Exibe uma frase aleatória no TextView
            textViewFrase.setText(vetorArray[valor_Aleatorio]);
        });
    }

    // Método auxiliar para ligar os componentes da interface aos objetos da classe
    private void initViews() {
        btn_Frase = findViewById(R.id.btn_frase); // Referência ao botão
        textViewFrase = findViewById(R.id.txtview_frase); // Referência ao TextView
    }
}
