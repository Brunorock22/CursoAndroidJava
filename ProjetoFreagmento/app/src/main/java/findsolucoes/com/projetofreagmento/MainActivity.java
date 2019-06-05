package findsolucoes.com.projetofreagmento;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentTransitionImpl;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botaoLogar;
    private Boolean stautus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoLogar = (Button) findViewById(R.id.bt_logar);
        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (stautus) {

                    LoginFragment loginFragment = new LoginFragment();
                    fragmentTransaction.add(R.id.rl_conteinerFragmento, loginFragment);
                    fragmentTransaction.commit();
                    botaoLogar.setText("Cadastre-se");
                    stautus = false;

                }else{
                    CadastroFragment cadastroFragment = new CadastroFragment();
                    fragmentTransaction.add(R.id.rl_conteinerFragmento, cadastroFragment);
                    fragmentTransaction.commit();
                    botaoLogar.setText("Logar");
                    stautus = true;
                }
            }
        });
    }
}
