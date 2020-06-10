package com.falabella.test.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.falabella.test.R
import com.falabella.test.databinding.ItemEconomicIndicatorBinding
import com.falabella.test.domain.model.EconomicIndicator

class EconomicIndicatorListAdapter(
    var economicIndicatorList: ArrayList<EconomicIndicator>,
    val listener: EconomicIndicatorAdapterListener
) : RecyclerView.Adapter<EconomicIndicatorListAdapter.EconomicIndicatorViewHolder>(), Filterable {

    private var filteredList: ArrayList<EconomicIndicator> = ArrayList()
    private val itemFilter = ItemFilter()

    init {
        filteredList.addAll(economicIndicatorList)
    }

    interface EconomicIndicatorAdapterListener {
        fun onEconomicIndicatorClicked(economicIndicator: EconomicIndicator)
    }

    inner class EconomicIndicatorViewHolder(private var binding: ItemEconomicIndicatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(economicIndicator: EconomicIndicator) {
            binding.apply {
                tvName.text = economicIndicator.name
                tvValue.text = economicIndicator.value.toString()
                root.setOnClickListener { listener.onEconomicIndicatorClicked(economicIndicator) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EconomicIndicatorViewHolder =
        EconomicIndicatorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_economic_indicator, parent, false
            )
        )

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: EconomicIndicatorViewHolder, position: Int) = holder.bind(filteredList[position])

    fun updateItems(newList: List<EconomicIndicator>) {
        economicIndicatorList.clear()
        economicIndicatorList.addAll(newList)
        filteredList.clear()
        filteredList.addAll(economicIndicatorList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter = itemFilter

    inner class ItemFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterString = constraint.toString().toLowerCase()
            val results = FilterResults()
            val list: List<EconomicIndicator> = economicIndicatorList
            val count = list.size
            val nlist = ArrayList<EconomicIndicator>(count)
            var indicator: EconomicIndicator
            for (i in 0 until count) {
                indicator = list[i]
                if (indicator.code.contains(filterString)) {
                    nlist.add(indicator)
                }
            }
            results.values = nlist
            results.count = nlist.size
            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            filteredList = results.values as ArrayList<EconomicIndicator>
            notifyDataSetChanged()
        }
    }
}