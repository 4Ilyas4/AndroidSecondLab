package com.example.lab2

import androidx.recyclerview.widget.DiffUtil

//DiffUtil - это утилита, предоставляемая Android Jetpack, которая помогает оптимизировать обновление элементов в RecyclerView,
//особенно когда у вас большие списки данных или когда данные часто изменяются.
//Чтобы использовать DiffUtil, вам нужно сначала создать класс,
//который наследуется от DiffUtil.Callback. Этот класс содержит методы,
//которые позволяют DiffUtil вычислять разницу между двумя списками данных.
//Затем вы можете использовать результаты этого вычисления для обновления
//адаптера RecyclerView только тех элементов, которые изменились,
//вместо полного обновления списка.
class MyDiffUtil(private val oldList: List<CatBreed>, private val newList: List<CatBreed>) : DiffUtil.Callback() {
    //В конструкторе класса написанно что он принимает старые и новые данные и насследуется
    // от библиотечной через DiffUtil.Callback()

    //Этот класс насследует библиотечную по этому должен имплементировать его методы через override
    //функции для получения размеров обоих массивов
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    //функция сравнения содержания элементов в старом и новом массивах принимающая их индексы
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]//получение старого элемента и нового через индексы
        val newItem = newList[newItemPosition]
        return oldItem.name == newItem.name  && oldItem.imageLink == newItem.imageLink && oldItem.origin == newItem.origin && oldItem.grooming == newItem.grooming && oldItem.intelligence == newItem.intelligence
        //возвращает равны ли содержания в  старом и новом элеменах или нет
    }
}
