package findsolucoes.com.whatsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import findsolucoes.com.whatsapp.activity.LoginActivity;
import findsolucoes.com.whatsapp.config.ConfiguracaoFirebase;
import findsolucoes.com.whatsapp.helper.BAse64Custom;
import findsolucoes.com.whatsapp.helper.Preferencias;
import findsolucoes.com.whatsapp.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastro;

    private FirebaseAuth autenticacao;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.editCadastroNome);
        email = (EditText) findViewById(R.id.editCadastroEmail);
        senha = (EditText) findViewById(R.id.editCadastroSenha);
        botaoCadastro = (Button) findViewById(R.id.bt_cadastrar);

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar", Toast.LENGTH_SHORT).show();
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    String identificadorUsuario = BAse64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId( identificadorUsuario );
                    usuario.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarDados(identificadorUsuario);

                    abrirLoginUsuario();

                } else {

                    String errorExcecao = null;

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        errorExcecao = "Digite uma senha mais forte.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        errorExcecao = "Digite um email valido.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        errorExcecao = "Este email ja esta cadastrado.";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar " + errorExcecao, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
