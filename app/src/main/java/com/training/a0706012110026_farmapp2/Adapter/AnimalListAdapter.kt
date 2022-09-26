package com.training.a0706012110026_farmapp2.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.a0706012110026_farmapp2.DB.AnimalDatabase.Companion.animalArr
import com.training.a0706012110026_farmapp2.Interface.CardClick
import com.training.a0706012110026_farmapp2.Interface.CardToastClick
import com.training.a0706012110026_farmapp2.Model.*
import com.training.a0706012110026_farmapp2.R
import com.training.a0706012110026_farmapp2.databinding.AnimalCardBinding

class AnimalListAdapter(val listAnimal: ArrayList<Animal>, val cardClick: CardClick, val toastClick: CardToastClick):
    RecyclerView.Adapter<AnimalListAdapter.viewHolder>() {

    class viewHolder (itemView: View, cardClick: CardClick, toastClick: CardToastClick): RecyclerView.ViewHolder(itemView) {
        //Bind
        val binding = AnimalCardBinding.bind(itemView)

        fun setData(data: Animal, cardClick: CardClick, toastClick: CardToastClick){
            binding.animalListName.text = data.name
            binding.animalListAge.text = "Usia : " + data.age.toString() + " tahun"
            binding.feedBtn.setOnClickListener{
                if(data is Sapi){
                    toastClick.onToastCardClick(data.feed(Rumput("Rumput")))

                }else if(data is Ayam){
                    toastClick.onToastCardClick(data.feed(BijiBijian("Biji")))

                }else if(data is Kambing){
                    toastClick.onToastCardClick(data.feed(Rumput("Rumput")))
                }

            }

            binding.soundBtn.setOnClickListener {
                toastClick.onToastCardClick(data.voice())
            }


            binding.animalListType.text = data.type
            if(data.imageUri?.isNotEmpty() == true)
                binding.imageUri.setImageURI(Uri.parse(data.imageUri))
            binding.editAnimal.setOnClickListener {
                cardClick.onCardClick("edit", data)
            }
            binding.deleteAnimal.setOnClickListener {
                cardClick.onCardClick("delete", data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.animal_card, parent, false)
        return viewHolder(view, cardClick, toastClick)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listAnimal[position], cardClick, toastClick)
    }

    override fun getItemCount(): Int {
        return listAnimal.size
    }

}