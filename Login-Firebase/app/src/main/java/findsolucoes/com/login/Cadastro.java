package findsolucoes.com.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Cadastro extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;
    private Button btnCadastro ;

    private FirebaseAuth firebaseAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        usuario = (EditText) findViewById(R.id.usuarioId);
        senha = (EditText) findViewById(R.id.senhaId);
        btnCadastro = (Button) findViewById(R.id.btnCadastroId);
        //Iniciar o firebaseAuth
        firebaseAuth1 =FirebaseAuth.getInstance();



        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textousuario = usuario.getText().toString().trim();
                String textosenha = senha.getText().toString().trim();

                //Cadastro
                firebaseAuth1.createUserWithEmailAndPassword(textousuario,textosenha)
                        .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Cadastro Realizado", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Cadastro Negado", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
