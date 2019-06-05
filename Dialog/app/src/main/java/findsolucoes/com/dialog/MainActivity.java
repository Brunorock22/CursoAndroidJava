package findsolucoes.com.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botao;
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.botaoId);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criar alert dialog
                dialog = new AlertDialog.Builder(MainActivity.this);

                //Configurar Titulo
                dialog.setTitle("Titulo da Dialog");
                dialog.setMessage("Mensagem");
                dialog.setCancelable(false);

                //Botao negativo
                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Pressionado botao NAO",Toast.LENGTH_SHORT).show();
                    }
                });
                //Botao positivo
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Pressionado botao SIM",Toast.LENGTH_LONG).show();
                    }
                });

                dialog.create();
                dialog.show();

            }
        });
    }
}
