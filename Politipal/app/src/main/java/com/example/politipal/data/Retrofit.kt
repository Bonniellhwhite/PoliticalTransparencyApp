package com.example.politipal.data
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


data class ApiResponse(val message: String)


interface ApiService {
    @GET("/endpoint") // Change "/endpoint" to your actual endpoint path
    suspend fun fetchData(): Response<ApiResponse>
}



object RetrofitClient {
    private const val BASE_URL = "https://your-api-base-url.com/" // Change to your API base URL

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}