package findsolucoes.com.listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private ListView listaItens;
    private String[] itens={
       "Angra dos Reis","Paraty","Cajuru","Divinopolis","Nova Serrana",
       "Rio de Janeiro","São Paulo","Campo Belo","Curitiba"
            ,"Angra dos Reis","Paraty","Cajuru","Divinopolis","Nova Serrana",
            "Rio de Janeiro","São Paulo","Campo Belo","Curitiba",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaItens = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );
        listaItens.setAdapter(adapter);

        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                String valorClicado =listaItens.getItemAtPosition(codigoPosicao).toString();
                Toast.makeText(getApplicationContext(),valorClicado,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
