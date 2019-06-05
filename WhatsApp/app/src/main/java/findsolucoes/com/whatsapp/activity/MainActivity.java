package findsolucoes.com.whatsapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import findsolucoes.com.whatsapp.R;
import findsolucoes.com.whatsapp.adapter.TabAdapter;
import findsolucoes.com.whatsapp.config.ConfiguracaoFirebase;
import findsolucoes.com.whatsapp.helper.BAse64Custom;
import findsolucoes.com.whatsapp.helper.Preferencias;
import findsolucoes.com.whatsapp.helper.SlidingTabLayout;
import findsolucoes.com.whatsapp.model.Contato;
import findsolucoes.com.whatsapp.model.Usuario;

public class MainActivity<Firebase> extends AppCompatActivity {

    private Firebase firebase;
    private Button botaosair;
    private Toolbar toolbar;
    private String indentificadorContado;
    private DatabaseReference firebase2;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private FirebaseAuth usuarioAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioAutenticacao = ConfiguracaoFirebase.getAutenticacao();

        firebase = (Firebase) ConfiguracaoFirebase.getFirebase();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);

        slidingTabLayout =(SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //Configurar sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));

        //Configuração adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_sair:
                deslogarUsuario();
                return true;
            case R.id.item_config:
                return true;
            case R.id.item_adcionar :
                abrirCadastro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void abrirCadastro(){

        AlertDialog.Builder alertDialog =  new AlertDialog.Builder(MainActivity.this);

        //Configurando Dialog
        alertDialog.setTitle("Novo Contato");
        alertDialog.setMessage("E-mail do Usuario");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);
        alertDialog.setView(editText);

        //Configura botoes
        alertDialog.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String emailContato = editText.getText().toString();

                //Validader e-mail
                if(emailContato.isEmpty()){
                    Toast.makeText(MainActivity.this,"Preencha o e-mail",Toast.LENGTH_SHORT).show();
                }else{
                    //Verificar se o usuario ja esta cadastrado no App
                    indentificadorContado = BAse64Custom.codificarBase64(emailContato);

                    //Recuperar instancia Firebase


                    firebase2 = ConfiguracaoFirebase.getFirebase().child("usuarios").child(indentificadorContado);

                    firebase2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.getValue() != null){

                                //Recuperar dados do contato a ser adcionado
                                Usuario usuarioContato =dataSnapshot.getValue(Usuario.class);


                                //Recuperar identificadpr usuario logado (base64)
                                Preferencias preferencias = new Preferencias(MainActivity.this);
                                String identificadorUsuarioLogado = preferencias.getIdentificador();

                                firebase2 = ConfiguracaoFirebase.getFirebase();
                                firebase2 = firebase2.child("contatos").child(identificadorUsuarioLogado).child(indentificadorContado);

                                Contato contato = new Contato();
                                contato.setIdentificadorUsuario(identificadorUsuarioLogado);
                                contato.setEmail(usuarioContato.getEmail());
                                contato.setNome(usuarioContato.getNome());

                                firebase2.setValue(contato);
                                Toast.makeText(MainActivity.this,"Usuario adcionado",Toast.LENGTH_SHORT).show();


                            }else {
                                Toast.makeText(MainActivity.this,"Usuario não possui cadastro",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();
    }
    private void deslogarCadastro(){

    }
    public void  deslogarUsuario(){
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
