package findsolucoes.com.myapplication.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityService {

    public static  final String BASE_URL ="https://www.udacity.com/public-apli/v0/";

    @GET("courses")
    Call<UdacityCatalog> listCatalog();
}