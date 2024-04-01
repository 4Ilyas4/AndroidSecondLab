package com.example.lab2
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Этот код создает объект `RetrofitInstance`, который предоставляет готовый
// к использованию экземпляр Retrofit, настроенный для работы
// с определенным базовым URL и сериализатором Gson.

object RetrofitInstance {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        //создаем новый экземпляр Retrofit с помощью Retrofit.Builder().
        // Мы используем базовый URL, указанный в параметре baseUrl,
        // добавляем конвертер для сериализации/десериализации JSON с помощью GsonConverterFactory,
        // и строим экземпляр Retrofit.
        }
        return retrofit!!
    //После этого мы возвращаем экземпляр Retrofit который никогда не должен быть null (!!),
    // и мы хотим получить его значение непосредственно, игнорируя проверку на null.
    // Если retrofit окажется null, это приведет к исключению NullPointerException.
    }
}




