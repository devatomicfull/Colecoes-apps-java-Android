package rendimento.autonomiadasuamoto;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText totalDeLitrosNoTanqueVeicular;
    private TextInputEditText totalDeLitrosConsumidoNaViagem;
    private TextInputEditText km_inical;
    private TextInputEditText km_PercorridaFinal;
    private MaterialButton materialButton_CalcularDesempenho;
    // tabela
    private TextView textViewDistanciaPercorrida;
    private TextView textViewAutonomiaLitros;
    private TextView textViewAutonomiaMaxima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        materialButton_CalcularDesempenho.setOnClickListener(v -> {
            String litrosTanque = totalDeLitrosNoTanqueVeicular.getText().toString();
            String litrosConsumo = totalDeLitrosConsumidoNaViagem.getText().toString();
            String kmInicial = km_inical.getText().toString();
            String kmFinal = km_PercorridaFinal.getText().toString();

            if (litrosTanque.isBlank() || litrosTanque.isEmpty())
                totalDeLitrosNoTanqueVeicular.setError("Preencha corretamente");
            if (litrosConsumo.isBlank() || litrosConsumo.isEmpty())
                totalDeLitrosConsumidoNaViagem.setError("Preencha corretamente");
            if (kmInicial.isBlank() || kmInicial.isEmpty())
                km_inical.setError("Preencha corretamente");
            if (kmFinal.isBlank() || kmFinal.isEmpty())
                km_PercorridaFinal.setError("Preencha corretamente");

            try {
                Double litrosDoTanque = Double.parseDouble(litrosTanque);
                Double litrosConsumidos = Double.parseDouble(litrosConsumo);
                Double inicialKM = Double.parseDouble(kmInicial);
                Double finalKM = Double.parseDouble(kmFinal);

                Double distanciaPercorrida = finalKM - inicialKM;
                Double consumoMedio = distanciaPercorrida / litrosConsumidos;
                Double consumoMaximo = consumoMedio * litrosDoTanque;

                if (litrosConsumidos == 0) {
                    Toast.makeText(getApplicationContext(), "Litros consumidos não pode ser 0", Toast.LENGTH_SHORT).show();
                    totalDeLitrosConsumidoNaViagem.setError("Não é permitido 0");
                    return;
                }

                textViewDistanciaPercorrida.setText(String.format("%.2f km", distanciaPercorrida));
                textViewAutonomiaLitros.setText(String.format("%.2f km/L", consumoMedio));
                textViewAutonomiaMaxima.setText(String.format("%.2f km/L", consumoMaximo));


            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Valor inválido ou formato não aceito", Toast.LENGTH_SHORT).show();
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "O valor está vazio", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews() {
        totalDeLitrosNoTanqueVeicular = findViewById(R.id.filledTextField_LitrosNoTanque);
        totalDeLitrosConsumidoNaViagem = findViewById(R.id.filledTextField_LitrosConsumido);
        km_inical = findViewById(R.id.filledTextField_KmInicial);
        km_PercorridaFinal = findViewById(R.id.filledTextField_KmPercorridoFinal);
        materialButton_CalcularDesempenho = findViewById(R.id.calcularDesempenho);
        //tabela
        textViewDistanciaPercorrida = findViewById(R.id.txtDistanciaPercorrida);
        textViewAutonomiaLitros = findViewById(R.id.txtAutomiaDosLitros);
        textViewAutonomiaMaxima = findViewById(R.id.txtAutonomiaMaxima);
    }
}


