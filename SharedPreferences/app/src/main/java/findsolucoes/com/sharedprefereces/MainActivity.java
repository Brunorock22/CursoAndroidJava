package findsolucoes.com.sharedprefereces;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private static final String ARQUIVO_REFERENCIA="ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.textoNomeId);
        textoExibicao = (TextView) findViewById(R.id.textId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_REFERENCIA,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(textoNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Preencha os campos por favor ",Toast.LENGTH_LONG).show();
                }else{
                    editor.putString("Nome,",textoNome.getText().toString());
                    editor.commit();
                    textoExibicao.setText("Ola, "+textoNome.getText().toString());
                }
            }
        });
        //Recuperar os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_REFERENCIA,0);
        if( sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome","usario não definido");
            textoExibicao.setText("Ola, "+nomeUsuario);
        }else{
            textoExibicao.setText("Ola usario nao definido");
        }
    }
}
