package com.example.lab2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//abyssinian
class MainActivity : ComponentActivity() {// создаем класс MainActivity и насследуем его от ComponentActivity
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private val baseUrl = "https://api.api-ninjas.com/v1/"
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // его методы lifecycle которые начинаются с on
        //Когда активность в Android создается заново после того, как она была уничтожена системой (например, из-за поворота экрана или из-за нехватки памяти),
        //метод onCreate вызывается с аргументом savedInstanceState: Bundle?, который содержит данные о предыдущем состоянии активности.
        //Bundle - это структура данных, которая представляет собой мешок с ключами и значениями, которые можно использовать для передачи данных между компонентами Android.

        super.onCreate(savedInstanceState)//вызываем этот метод от родителя через super(ООП концепт)
        setContentView(R.layout.activity_main)//метод ComponentActivity который ставит какой xml использовать для данного MainActivity
        recyclerView = findViewById(R.id.main_activity_recycler_view)//создаем объект RecyclerView в этом классе так как нам нужно вставить туда данные
        // которые в этом классе и на этой странице будет RecyclerView
        // задаем этот xml элемент в коде через метод findViewById<ТипXMLЭлемента>(R.id.IDлемента)

        recyclerView.layoutManager = LinearLayoutManager(this) // устанавливает (свойство в recyclerView XML) менеджер компоновки (layout manager) для RecyclerView

        adapter = Adapter(emptyList())// Инициализируем пустой адаптер и устанавливаем его в RecyclerView
        recyclerView.adapter = adapter

        val retrofitService: CatApiService = RetrofitInstance.getClient(baseUrl).create(CatApiService::class.java)

        val apiKey = "Y8Ut/zBDC2VNFAoWkv7IIA==7pTETOGAVWBDT71h" // X-Api-Key для доступа к апи

        var name : String = "abyssinian"//переменная имени породы кошки

        fun performSearch(name: String) {
            retrofitService.getCats(name, apiKey).enqueue(object : Callback<List<CatBreed>> {
                //отправляет GET-запрос на сервер для получения списка пород кошек. Метод getCats принимает два параметра:
                // name, который является именем породы кошки, и apiKey, который является вашим API-ключом.
                override fun onResponse(
                    call: Call<List<CatBreed>>,
                    response: Response<List<CatBreed>>
                ) {
                    if (response.isSuccessful) {
                        val catBreeds = response.body()
                        catBreeds?.let {// Это стандартный способ проверки, не является ли объект catBreeds равным null.
                            // Если catBreeds не равен null, то код внутри блока let будет выполнен.
                            adapter.updateData(it)
                            //it это сокращенное имя переменной, которое в let. Оно ссылается на catBreeds тк catBreeds?.let
                            //то есть it==catBreeds
                        }
                        //Если ответ успешный (код ответа 2xx), то мы получаем список пород кошек из тела ответа (response.body()),
                        // и затем обновляем адаптер, передавая этот список методу updateData адаптера (данные в адаптере теперь это catBreeds)
                    } else {
                        Log.e("TAG", response.message())
                        Log.e("API_CALL", "Failed to fetch data: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<List<CatBreed>>, t: Throwable) {
                    Log.e("API_CALL", "Failed to fetch data: ${t.message}", t)
                }
                //обработка ошибок
            })
        }

        searchEditText = findViewById(R.id.editTextText)
        searchButton = findViewById(R.id.button)
        searchButton.setOnClickListener {
            val searchText = searchEditText.text.toString() // берем текст xml edit text элемента
            // в который пишем name
            if (searchText.isNotEmpty()) { // выполняем если текст не пустой
                performSearch(searchText)//делаем прошлые процедуры по name значение которого в searchText
                //только при вводе чего то
            } else {
                Toast.makeText(this, "Введите имя породы кошки", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



