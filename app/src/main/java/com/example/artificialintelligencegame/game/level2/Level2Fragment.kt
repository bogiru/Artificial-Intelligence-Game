package com.example.artificialintelligencegame.game.level2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.artificialintelligencegame.R
import com.example.artificialintelligencegame.databinding.FragmentLevel2Binding
import org.koin.android.viewmodel.ext.android.viewModel

class Level2Fragment : Fragment() {

    private val viewModel: Level2ViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLevel2Binding>(inflater, R.layout.fragment_level2, container, false)

        binding.pager.adapter = Level2Adapter(activity!!.application, viewModel)

        return binding.root

    }


}