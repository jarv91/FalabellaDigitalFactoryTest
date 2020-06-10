package com.falabella.test.presentation.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.falabella.test.R
import com.falabella.test.databinding.FragmentEconomicIndicatorListBinding
import com.falabella.test.domain.model.EconomicIndicator
import com.falabella.test.presentation.ui.main.adapter.EconomicIndicatorListAdapter
import com.falabella.test.presentation.viewmodel.EconomicIndicatorListViewModel
import com.falabella.test.presentation.viewmodel.SignInViewModel

class EconomicIndicatorListFragment : Fragment(),
    EconomicIndicatorListAdapter.EconomicIndicatorAdapterListener {

    private val economicIndicatorListAdapter = EconomicIndicatorListAdapter(arrayListOf(), this)

    private lateinit var binding: FragmentEconomicIndicatorListBinding
    private lateinit var viewModel: EconomicIndicatorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_economic_indicator_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EconomicIndicatorListViewModel::class.java)
        binding.rvEconomicIndicatorList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = economicIndicatorListAdapter
        }
        viewModel.economicIndicatorList.observe(viewLifecycleOwner, Observer {
            economicIndicatorListAdapter.updateItems(it)
            binding.pbLoader.visibility = View.GONE
        })
        viewModel.getEconomicIndicators()
    }

    override fun onEconomicIndicatorClicked(economicIndicator: EconomicIndicator) {
        val action =
            EconomicIndicatorListFragmentDirections.actionEconomicIndicatorListToEconomicIndicatorDetail(
                com.falabella.test.presentation.ui.main.model.EconomicIndicator.fromDomainModel(economicIndicator)
            )
        Navigation.findNavController(binding.root).navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem: MenuItem? = menu.findItem(R.id.search)
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                economicIndicatorListAdapter.filter.filter(p0)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
    }
}