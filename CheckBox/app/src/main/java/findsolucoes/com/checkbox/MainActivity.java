package findsolucoes.com.checkbox;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CheckBox checkBoxCao;
    private CheckBox checkBoxGato;
    private CheckBox checkBoxPapagaio;

    private Button botaoMonstrar;
    private TextView textoExbicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxCao = (CheckBox) findViewById(R.id.caoId);
        checkBoxGato = (CheckBox) findViewById(R.id.gatoId);
        checkBoxPapagaio =(CheckBox)  findViewById(R.id.papagaioId);

        botaoMonstrar = (Button)findViewById(R.id.botaoId);
        textoExbicao = (TextView) findViewById(R.id.textoExibicaoId);

        botaoMonstrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itensSecionados="";
                itensSecionados +=""+checkBoxCao.getText()+"Status: "+checkBoxCao.isChecked()+"\n";
                itensSecionados +=""+checkBoxGato.getText()+"Status: "+checkBoxGato.isChecked()+"\n";
                itensSecionados +=""+checkBoxPapagaio.getText()+"Status: "+checkBoxPapagaio.isChecked()+"\n";

                textoExbicao.setText(itensSecionados);

            }
        });
    }
}
