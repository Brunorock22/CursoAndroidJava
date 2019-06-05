package findsolucoes.com.whatsapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

import findsolucoes.com.whatsapp.CadastroActivity;
import findsolucoes.com.whatsapp.R;
import findsolucoes.com.whatsapp.config.ConfiguracaoFirebase;
import findsolucoes.com.whatsapp.helper.BAse64Custom;
import findsolucoes.com.whatsapp.helper.Permissao;
import findsolucoes.com.whatsapp.helper.Preferencias;
import findsolucoes.com.whatsapp.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;

    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsusarioLogado();

        email = (EditText) findViewById(R.id.edit_loginEmail);
        senha = (EditText) findViewById(R.id.edit_loginSenha);
        botaoLogar = (Button) findViewById(R.id.bt_Logar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();
            }
        });
    }

    private void verificarUsusarioLogado() {
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    Preferencias preferencias = new Preferencias(LoginActivity.this);
                    String identificadorUsuarioLogado = BAse64Custom.codificarBase64(usuario.getEmail());
                    preferencias.salvarDados(identificadorUsuarioLogado);

                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login!", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login!", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);

    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
