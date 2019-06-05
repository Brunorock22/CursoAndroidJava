package findsolucoes.com.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText user;
    private EditText pass;
    private Button botao;
    private Button vacadastrar;
    private TextView cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        user = (EditText) findViewById(R.id.userId);
        pass = (EditText) findViewById(R.id.passId);
        botao = (Button) findViewById(R.id.botaoId);
        cadastro = (TextView) findViewById(R.id.cadastroId);


        //Verifica de o usuario está logado
        if (firebaseAuth.getCurrentUser() != null ){
            Log.i("verificacao","Usuario Logado");
        }else{
            Log.i("verificacao","Usuario nao esta logado");
        }


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = user.getText().toString().trim();
                String senha = pass.getText().toString().trim();

                //Login Usuario
                firebaseAuth.signInWithEmailAndPassword(usuario,senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){//sucesso ao cadastrar
                            Toast.makeText(getApplicationContext(), "Acesso Permitido", Toast.LENGTH_SHORT).show();
                        }else{//erro ao cadastrar
                            Toast.makeText(getApplicationContext(), "Acesso Negado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //Va para o layout de cadastro
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Cadastro.class));
            }
        });



    }
}
