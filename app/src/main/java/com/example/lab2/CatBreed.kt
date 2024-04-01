package com.example.lab2

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("name")val name: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("grooming")val grooming: String,
    @SerializedName("intelligence")val intelligence: String,
    @SerializedName("image_link") val imageLink: String
    //название перерменной в @SerializedName("image_link") это название ее в json
    //val imageLink то которую мы используем в коде
)
//Класс `CatBreed` представляет элементарные данные в data,
// которые будут отображаться в элементах списка `RecyclerView`.
