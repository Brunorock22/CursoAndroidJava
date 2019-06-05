package findsolucoes.com.whatsapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import findsolucoes.com.whatsapp.R;
import findsolucoes.com.whatsapp.activity.ConversaActivity;
import findsolucoes.com.whatsapp.adapter.ContatoAdapter;
import findsolucoes.com.whatsapp.config.ConfiguracaoFirebase;
import findsolucoes.com.whatsapp.helper.Preferencias;
import findsolucoes.com.whatsapp.model.Contato;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContatosFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Contato> contatos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerContatos;

    public ContatosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerContatos );
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerContatos );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        contatos = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        listView = (ListView)view.findViewById(R.id.lv_contatos);

        adapter = new ContatoAdapter(getActivity(), contatos);
        listView.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        String indetificadorUsusariosLogado = preferencias.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
            .child("contatos")
        .child( indetificadorUsusariosLogado );

        //Listener para recuperar contatos
        valueEventListenerContatos = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Limpar Lista
                contatos.clear();

                //Listar contatos
                for (DataSnapshot dados : dataSnapshot.getChildren()){

                    Contato contato = dados.getValue(Contato.class);
                    contatos.add(contato);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ConversaActivity.class);

                //recuperar dados a serem passados
                Contato contato = contatos.get(position);


                //enviando dados para a activity
                intent.putExtra("nome",contato.getNome());
                intent.putExtra("email",contato.getEmail());
                startActivity(intent);

            }
        });

        return  view;
    }

}
