package com.example.calculadoraimc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculadoraimc.R
import com.example.calculadoraimc.domain.IMC

class HistoryListAdapter(
    private val deleteItem: (id: Int) -> Unit
) : ListAdapter<IMC, HistoryListViewHolder>(HistoryListAdapter) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_imc_history, parent, false)
        return HistoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        val imc = getItem(position)
        holder.bind(imc, deleteItem)
    }

    companion object : DiffUtil.ItemCallback<IMC>() {
        override fun areItemsTheSame(oldItem: IMC, newItem: IMC): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: IMC, newItem: IMC): Boolean {
            return oldItem.weight == newItem.weight &&
                    oldItem.height == newItem.height &&
                    oldItem.classification == newItem.classification &&
                    oldItem.imc == newItem.imc
        }
    }
}

class HistoryListViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(historyIMC: IMC, deleteItem: (id: Int) -> Unit) = with(itemView) {

        val weight = findViewById<TextView>(R.id.text_weight)
        val height = findViewById<TextView>(R.id.text_height)
        val classification = findViewById<TextView>(R.id.text_classification_history)
        val imc = findViewById<TextView>(R.id.text_imc)
        val btnDelete = findViewById<ImageButton>(R.id.delete_item_history)

        weight.text = "${historyIMC.weight} Kg"
        height.text = "${historyIMC.height} M"
        classification.text = historyIMC.classification
        imc.text = "${historyIMC.imc} Kg/m²"

        btnDelete.setOnClickListener {
            deleteItem.invoke(historyIMC.id)
        }
    }
}