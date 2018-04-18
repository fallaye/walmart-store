package cvs.com.cvs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fallaye on 4/16/18.
 */

public interface Api {

    String BASE_URL = "http://api.walmartlabs.com/";

    @GET("v1/paginated/items?category=3944&specialOffer=rollback&apiKey=jqkppatf3su4ma38cpj3tevh&format=json")
    Call<Data> getData();

}
