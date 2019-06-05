package findsolucoes.com.atmconsultoria;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView botaoEmpresa;
    private ImageView botaoServiso;
    private ImageView botaoCliente;
    private ImageView botaoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEmpresa=(ImageView) findViewById(R.id.empresaId);
        botaoServiso=(ImageView) findViewById(R.id.servisoId);
        botaoCliente=(ImageView) findViewById(R.id.clienteId);
        botaoContato=(ImageView) findViewById(R.id.contatoId);

        botaoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EmpresaActivity.class));
            }
        });

        botaoServiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Serviso.class));
            }
        });

        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ClienteActivity.class));
            }
        });

        botaoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContatoActivity.class));
            }
        });
    }
}
