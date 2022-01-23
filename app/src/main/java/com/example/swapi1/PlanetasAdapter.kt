package com.example.swapi1

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapi1.databinding.ModeloPlanetaBinding

class PlanetasAdapter(var listaPlanetas: List<Planet>) : RecyclerView.Adapter<PlanetasAdapter.PlanetasViewHolder>() {

    lateinit var context: Context

    class PlanetasViewHolder(var itemBinding : ModeloPlanetaBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return listaPlanetas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetasViewHolder {
        val binding = ModeloPlanetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context=parent.context
        return PlanetasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetasViewHolder, position: Int) {

            holder.itemBinding.textView.text = listaPlanetas.get(position).name
            holder.itemBinding.textView.setOnClickListener {
                DatosPlanetas.launch(
                    holder.itemBinding.layoutPlanetas.context,
                    listaPlanetas.get(position).toString()
                )
            }
        }


    }




