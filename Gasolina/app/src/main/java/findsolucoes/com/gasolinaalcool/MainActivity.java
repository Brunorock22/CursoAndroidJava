package findsolucoes.com.gasolinaalcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText precoAlccol;
    private EditText precoGasolina;
    private Button botaoverificar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoAlccol = (EditText) findViewById(R.id.precoalcoolId);
        precoGasolina= (EditText) findViewById(R.id.precogasolinaId);
        botaoverificar = (Button) findViewById(R.id.botaoverificarId);
        textoResultado = (TextView) findViewById(R.id.textoResultadoId);

        botaoverificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperar valores digitados:

                String textoPrecoAlcool = precoAlccol.getText().toString();
                String textoPrecoGasolina = precoGasolina.getText().toString();

                //Converter valores para numeros
                Double valorAlcool = Double.parseDouble(textoPrecoAlcool);
                Double valorGasolina = Double.parseDouble(textoPrecoGasolina);

                // alcool /  gasolina
                double resultado = valorAlcool/valorGasolina;

                if(resultado >= 0.7){
                    //Gasolina
                    textoResultado.setText("Use Gasolina");
                }else{
                    //Alcool
                    textoResultado.setText("Use Alcool");
                }
            }
        });
    }
}
