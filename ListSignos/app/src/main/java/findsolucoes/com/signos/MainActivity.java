package findsolucoes.com.signos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listaSignos;
    private String[] signos={"Áries ou Carneiro", "Touro, Gêmeos", "Câncer ou Caranguejo", "Leão", "Virgem", "Libra ou Balança", "Escorpião", "Sagitário", "Capricórnio", "Aquário e Peixes"};
    private String[] perfis={"Áries ou Carneiro", "Touro, Gêmeos", "Câncer ou Caranguejo", "Leão", "Virgem", "Libra ou Balança", "Escorpião", "Sagitário", "Capricórnio", "Aquário e Peixes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSignos = findViewById(R.id.listViewId);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );
        listaSignos.setAdapter(adapter);

        listaSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int codigoPosicao = position;
                Toast.makeText(getApplicationContext(),perfis[codigoPosicao],Toast.LENGTH_LONG).show();

            }
        });
    }
}
