package Android.androidkingpowerclicktest

import Android.androidkingpowerclicktest.models.Image
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("albums/1/photos")
    fun getAllImage(): Observable<List<Image>>

    companion object {
        fun create(baseUrl: String): API {
            val retrofit = Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl).build()
            return retrofit.create(API::class.java)
        }
    }
}
