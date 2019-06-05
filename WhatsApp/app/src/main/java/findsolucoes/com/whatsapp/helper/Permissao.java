package findsolucoes.com.whatsapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    public  static  boolean validadePermissoes(int requestCode, Activity activity, String[]permissoes){

        if (Build.VERSION.SDK_INT >= 23){
            //Percor as permisao passadas

            List<String> listaPermissoes = new ArrayList<String>();

            for (String permissao : permissoes){
                boolean validadePermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if (!validadePermissao ) listaPermissoes.add(permissao);
            }

            //Caso a lista esteja vazia, não é necessario solicitar permissão
            if (listaPermissoes.isEmpty() )return true;

            String[] novasPermissies = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissies);

            //Solicitar permissão
            ActivityCompat.requestPermissions(activity, novasPermissies, requestCode);

        }

        return true;
    }
}
