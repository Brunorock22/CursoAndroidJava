package findsolucoes.com.caraoucoroa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button botaoJogar;
    private String [] opcao ={"cara","coroa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoJogar = (Button) findViewById(R.id.botaoJogarId);

        botaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Gerar numeros aleatorios
                Random random = new Random();
                int numeroAleatorio = random.nextInt(2);


                //0 é cara 1 coroa
                //opcao[numeroAleatorio]
                Intent intent = new Intent(MainActivity.this,DetalheActivity.class);
                intent.putExtra("opcao",opcao[numeroAleatorio]);

                startActivity(intent);
            }
        });
    }
}
