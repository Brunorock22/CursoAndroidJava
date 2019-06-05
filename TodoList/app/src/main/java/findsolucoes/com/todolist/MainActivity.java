package findsolucoes.com.todolist;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText textoTarefa;
    private Button botaoAdcionar;
    private ListView listaTarefas;
    private SQLiteDatabase bancoDeDados;

    private ArrayAdapter<String> itensAdapter;
    private ArrayList<String> intens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {


            //Recuperar componentes
            textoTarefa = (EditText) findViewById(R.id.textoId);
            botaoAdcionar = (Button) findViewById(R.id.botaoId);


            //Banco de Dados
            bancoDeDados = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabelas tarefas
            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT,tarefa VARCHAR)");

            botaoAdcionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textodigitado = textoTarefa.getText().toString();
                    salvarTarefa(textodigitado);


                }
            });

            listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

            //listar tarefas
            recuperTarefas();


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void salvarTarefa(String texto){
        try{

            if(texto.equals("")){
                Toast.makeText(MainActivity.this,"Digite uma tarefa",Toast.LENGTH_SHORT).show();
            }else {
                bancoDeDados.execSQL("INSERT INTO tarefas (tarefa) VALUES('" + texto + "')");
                Toast.makeText(MainActivity.this,"Tarefa SALVA",Toast.LENGTH_SHORT).show();
                recuperTarefas();
                textoTarefa.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void recuperTarefas(){

        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDeDados.rawQuery("SELECT * FROM tarefas ORDER BY id DESC",null);

            // RECUPERAR OS IDS das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("tarefa");
            //Lista
            listaTarefas = (ListView) findViewById(R.id.listViewId);

            //Criar Adaptador
            intens = new ArrayList<String>();
            itensAdapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    intens);

            listaTarefas.setAdapter(itensAdapter);

            //listar as tarefas
            cursor.moveToFirst();
            while (cursor != null){

                Log.i("Resultado -","Tarefa: "+cursor.getString(indiceColunaTarefa));
                intens.add( cursor.getString(indiceColunaTarefa));
                ids.add( Integer.parseInt(cursor.getString(indiceColunaId)));



                cursor.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefas(Integer id){
        try{

            bancoDeDados.execSQL("DELETE FROM TAREFAS WHERE id="+id);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

