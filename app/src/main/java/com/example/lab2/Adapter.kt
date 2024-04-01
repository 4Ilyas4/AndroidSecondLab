package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(private var data: List<CatBreed>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    //Класс адаптера который используется в MainActivity
    //в него передаем данные типа viewData в списке или листе
    // этот класс насследуется от адаптера из библиотеки RecyclerView.Adapter
    //В который мы перердаем через тип наш класс ViewHolder через <Adapter.ViewHolder>
    //ViewHolder это класс который мы здесь создадим

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView4: TextView = view.findViewById(R.id.textView4)//для использования через holder.textView...
        val textView3: TextView = view.findViewById(R.id.textView3)
        val textView2: TextView = view.findViewById(R.id.textView2)
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        //В этом классе мы задаем View для элементов у нас это image и text View делая поиск по id
        //и задаем их для элемента в RecyclerView который item_layout.xml
    }
    //Вот этот класс , в него передаем view типа View из библиотеки этот класс насследует ViewHolder из библиотеки
    // через RecyclerView.ViewHolder(view) важно вставить туда view  !

    //Так как наш класс Адаптера насследовал от библиотечного адаптера
    // он должен реализовать (override) его методы такие как onCreateViewHolder, onBindViewHolder, getItemCount
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Кст запомни у классов : используется для насследования а у функии для подчеркивания типа возвращаемого
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }
    // onCreateViewHolder - создает новый ViewHolder для элемента списка.
    //LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false) - создает новое представление(view) из макета item_layout.xml,
    // который будет вставлен в родительский контейнер, но не добавлен непосредственно к нему.
    //Созданный view передается конструктору ViewHolder. ViewHolder - это оболочка вокруг представления, которая содержит ссылки на представления внутри элемента списка.
    // Возвращаемый ViewHolder будет использоваться для хранения и переиспользования представлений внутри элементов списка.

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data[position]
        holder.textView.text = dataItem.name// меняем свойства view(элементов в xml)
        holder.textView2.text = "The origin is = " + dataItem.origin
        holder.textView3.text = "The grooming is = " + dataItem.grooming
        holder.textView4.text = "The intelligence is = " + dataItem.intelligence
        Picasso.get().load(dataItem.imageLink).into(holder.imageView)
    }
    //`onBindViewHolder` - метод, который связывает данные с представлением внутри ViewHolder.
    //`val dataItem = data[position]` - получает элемент данных по позиции в списке data (как получение элемента в обычном массиве по индексу)
    //`holder.textView.text = dataItem.text` - устанавливает текстовое содержимое в TextView, хранящемся внутри ViewHolder, используя текст из элемента данных.
    //`Picasso.get().load(dataItem.imageUrl).into(holder.imageView)` - загружает изображение по URL-адресу из элемента данных с помощью Picasso и устанавливает его в ImageView, хранящийся внутри ViewHolder.

    override fun getItemCount(): Int {
        return data.size
    }
    //getItemCount() - это простой метод, который возвращает общее количество элементов в списке данных,
    // который адаптер должен отобразить в RecyclerView. В данном случае, это количество элементов в списке data.
    //просто получение длинны массива через метод списка .size

    //К сожелению в адаптере нет метода для обновления по этому создадим его
    fun updateData(newData: List<CatBreed>) {
        data = newData
        notifyDataSetChanged()
    }
    //Эта функция обновляет данные в адаптере и сообщает RecyclerView через notifyDataSetChanged(),
    // что данные были изменены, чтобы они были обновлены на экране.
}