package findsolucoes.com.somdosbichos;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

    private ImageView imagemMacaco;
    private ImageView imagemLeao;
    private ImageView imagemCachorro;
    private ImageView imagemOvelha;
    private ImageView imagemGato;
    private ImageView imagemVaca;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagemCachorro = (ImageView) findViewById(R.id.cachorroId);
        imagemGato = (ImageView) findViewById(R.id.gatoId);
        imagemLeao = (ImageView) findViewById(R.id.leaoId);
        imagemVaca = (ImageView) findViewById(R.id.vacaId);
        imagemMacaco = (ImageView) findViewById(R.id.macacoId);
        imagemOvelha = (ImageView) findViewById(R.id.ovelhaId);

        imagemCachorro.setOnClickListener(this);
        imagemGato.setOnClickListener(this);
        imagemVaca.setOnClickListener(this);
        imagemOvelha.setOnClickListener(this);
        imagemLeao.setOnClickListener(this);
        imagemMacaco.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.cachorroId:
                mediaPlayer =MediaPlayer.create(MainActivity.this,R.raw.cao);
                tocarSom();
                break;
            case R.id.vacaId:
                mediaPlayer =MediaPlayer.create(MainActivity.this,R.raw.vaca);
                tocarSom();
                break;
            case R.id.ovelhaId:
                mediaPlayer =MediaPlayer.create(MainActivity.this,R.raw.ovelha);
                tocarSom();
                break;
            case R.id.leaoId:
                mediaPlayer =MediaPlayer.create(MainActivity.this,R.raw.leao);
                tocarSom();
                break;
            case R.id.gatoId:
                mediaPlayer =MediaPlayer.create(MainActivity.this,R.raw.gato);
                tocarSom();
                break;
            case R.id.macacoId:
                mediaPlayer =MediaPlayer.create(MainActivity.this,R.raw.macaco);
                tocarSom();
                break;
        }
    }
    public  void tocarSom(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
