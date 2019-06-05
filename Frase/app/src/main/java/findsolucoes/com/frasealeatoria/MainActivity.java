package findsolucoes.com.frasealeatoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView novaFrase;
    private Button botao;

    private String[] frases={"Dia 1", "Dia 2","Dia 3","Dia 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novaFrase=findViewById(R.id.campo_texto);
        botao=findViewById(R.id.button);

        novaFrase.setText(frases[0]);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random= new Random();
                int qualquer = random.nextInt(frases.length);
                novaFrase.setText(frases[qualquer]);
            }
        });

    }
}
