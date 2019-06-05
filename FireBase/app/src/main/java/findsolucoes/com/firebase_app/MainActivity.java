package findsolucoes.com.firebase_app;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuatrioReferencia= databaseReference.child("usuario");
    private DatabaseReference produtoReferencia= databaseReference.child("produto").child("001");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Usuario usuario = new Usuario();
//        usuario.setNome("Mateus");
//        usuario.setSobrenome("Camargos");
//        usuario.setIdade(15);
//        usuario.setSexo("M");
//
//        usuatrioReferencia.child("003").setValue(usuario);

//        Produtos produtos = new Produtos();
//        produtos.setProduto("Motorola");
//        produtos.setPreco(2000);
//        produtos.setDataVenda("20/05/2019");
//        produtoReferencia.child("001").setValue(produtos);

          usuatrioReferencia.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  Log.i("FIREBASE",dataSnapshot.getValue().toString());
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  
              }
          });

    }
}
