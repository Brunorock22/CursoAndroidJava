package findsolucoes.com.whatsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import findsolucoes.com.whatsapp.R;
import findsolucoes.com.whatsapp.model.Contato;

public class ContatoAdapter extends ArrayAdapter<Contato> {

    private  ArrayList<Contato>contatos;
    private Context context;
    public  ContatoAdapter(Context c, ArrayList<Contato> objects){
        super(c,0 ,objects);
        this.contatos = objects;
        this.context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        //Verificar se a lista esta vazia
        if (contatos != null){

            //inicializar objeto para a montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Montar view apartir do XML
            view = inflater.inflate(R.layout.lista_contato, parent, false);

            //recuperar elemento para exibição
            TextView nomeContato = (TextView) view.findViewById(R.id.tv_nome);
            TextView emailContato = (TextView) view.findViewById(R.id.tv_email);

            Contato contato = contatos.get(position);
            nomeContato.setText(contato.getNome());
            emailContato.setText(contato.getEmail());
        }
        return view;
    }
}
