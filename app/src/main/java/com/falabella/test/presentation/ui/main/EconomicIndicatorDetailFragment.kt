package com.falabella.test.presentation.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.falabella.test.R
import com.falabella.test.databinding.FragmentEconomicIndicatorDetailBinding
import com.falabella.test.presentation.ui.main.model.EconomicIndicator
import java.text.SimpleDateFormat
import java.util.*

class EconomicIndicatorDetailFragment : Fragment() {

    private lateinit var binding: FragmentEconomicIndicatorDetailBinding
    private lateinit var economicIndicator: EconomicIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_economic_indicator_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            economicIndicator = EconomicIndicatorDetailFragmentArgs.fromBundle(it).economicIndicator
        }
        binding.apply {
            tvCode.text = economicIndicator.code
            tvName.text = economicIndicator.name
            tvMeasurementUnit.text = economicIndicator.measurementUnit
            economicIndicator.date?.let {
                val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                val outputFormatter = SimpleDateFormat("dd/MM/yyy", Locale("es", "ES"))
                val date = inputFormatter.parse(it)
                date?.let { itDate -> tvDate.text = outputFormatter.format(itDate) }
            }
            tvValue.text = economicIndicator.value.toString()
        }
    }

}