package com.example.johasko_oblig2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.element.view.*

class ListAdapter (list : MutableList<Alpaca>) : RecyclerView.Adapter<ViewHolder>() {

    private var liste : MutableList<Alpaca> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AlpViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is AlpViewHolder -> {
                holder.bind(liste[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    class AlpViewHolder
    constructor(
        itemView: View
    ): ViewHolder(itemView){

        val bilde = itemView.bilde
        val navn = itemView.navn
        val lok = itemView.lok
        val alder = itemView.alder

        fun bind(alp: Alpaca){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(alp.imgSrc)
                .into(bilde)
            navn.setText(alp.name)
            lok.setText(alp.location)
            alder.setText(alp.age)

        }

    }

}