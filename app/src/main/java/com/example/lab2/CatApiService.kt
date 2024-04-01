package com.example.lab2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatApiService {
    @GET("cats")
    fun getCats(
        @Query("name") name: String?,
        @Query("grooming") grooming: String?,
        @Query("playfulness") playfulness: String?,
        @Query("shedding") shedding: String?,
        @Header("X-Api-Key") apiKey: String?
    ): Call<List<CatBreed>>
}

//Этот код определяет интерфейс `CatApiService`, который предоставляет метод `getCats()`.
// Аннотация `@GET("cats")` указывает, что этот метод выполняет HTTP GET запрос к эндпоинту "cats".
// Метод возвращает список объектов `CatBreed` в виде асинхронного(обсервер) вызова типа `Call`.
//Аннотация @Query("name") используется для добавления параметра запроса в URL,
// значение переменной name будет добавлено к URL в виде ?name=value.
//Аннотация @Header("X-Api-Key") указывает на то, что необходимо добавить заголовок с именем X-Api-Key к запросу.
//Параметр apiKey: String представляет собой значение API ключа, которое будет добавлено в заголовок запроса.