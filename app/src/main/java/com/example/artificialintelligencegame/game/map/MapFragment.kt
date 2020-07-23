package com.example.artificialintelligencegame.game.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.MapFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {
    private val viewModel: MapViewModel by viewModel()
    private lateinit var binding: MapFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        setupObserverViewModel()
    }

    private fun configureRecyclerView() {
        val adapter = MapAdapter(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), viewModel)
        binding.mapRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mapRecyclerView.adapter = adapter
    }

    private fun setupObserverViewModel() {
        viewModel.openLevelFragment.observe(viewLifecycleOwner, Observer { level ->
            when (level) {
                1 -> findNavController().navigate(R.id.action_mapFragment2_to_level1)
                2 -> findNavController().navigate(R.id.action_mapFragment2_to_level2Fragment)
                3 -> findNavController().navigate(R.id.action_mapFragment2_to_level3Fragment)
            }
        })
    }

}