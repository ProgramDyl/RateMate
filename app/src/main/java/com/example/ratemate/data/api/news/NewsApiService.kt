import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApiService {
    @GET("v2/top-headlines?country=us&apiKey=36ee12ca9c294eb489198e796e34939c")
    suspend fun getTopHeadlines(): NewsResponse
}

