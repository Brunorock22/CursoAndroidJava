package findsolucoes.com.myapplication;

import android.Manifest;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import findsolucoes.com.myapplication.models.Course;
import findsolucoes.com.myapplication.models.Instructor;
import findsolucoes.com.myapplication.models.UdacityCatalog;
import findsolucoes.com.myapplication.models.UdacityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Sucesso",Toast.LENGTH_SHORT).show();
                }else {
                    //Requisicao retornou com sucesso
                    UdacityCatalog catalog = response.body();

                    for (Course c : catalog.courses){
                        Log.i("Bruno",String.format("%s: $s",c.title,c.subtitle));

                        for (Instructor i : c.instructors){
                            Log.i("Bruno",i.name);
                        }
                        Log.i("Bruno","----------------");
                    }

                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e("Error","Erro "+t.getMessage());
            }
        });
    }
}
