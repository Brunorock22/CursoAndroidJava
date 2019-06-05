package findsolucoes.com.idadecachorro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botaoIdade;
    private EditText caixaTexto;
    private TextView resultadoIdade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIdade = (Button) findViewById(R.id.botaoIdade);
        caixaTexto = (EditText) findViewById(R.id.caixaIdadeId);
        resultadoIdade = (TextView) findViewById(R.id.resultadoIdadeId);

        botaoIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoDigito = caixaTexto.getText().toString();

                if(textoDigito.isEmpty()){
                    resultadoIdade.setText("");
                }else{
                    int valorDigitado = Integer.parseInt(textoDigito);
                    int resultadoFinal = valorDigitado * 7;

                    resultadoIdade.setText("A idade do cachorro é "+resultadoFinal);
                }
            }
        });


    }
}
