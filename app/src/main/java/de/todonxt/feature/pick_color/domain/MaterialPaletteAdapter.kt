package de.todonxt.feature.pick_color.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.todonxt.core.ui.MATERIAL_COLORS
import de.todonxt.databinding.RviColorPaletteBinding
import de.todonxt.feature.pick_color.presentation.MaterialPaletteViewHolder

class MaterialPaletteAdapter : RecyclerView.Adapter<MaterialPaletteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialPaletteViewHolder {
        return MaterialPaletteViewHolder(
            RviColorPaletteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MaterialPaletteViewHolder, position: Int) {
        holder.binding.root.adapter = MaterialColorAdapter(
            MATERIAL_COLORS[holder.adapterPosition]
        )
    }

    override fun getItemCount(): Int = MATERIAL_COLORS.size

}