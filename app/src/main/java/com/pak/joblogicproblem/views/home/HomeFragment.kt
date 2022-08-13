package com.pak.joblogicproblem.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pak.joblogicproblem.R
import com.pak.joblogicproblem.databinding.FragmentHomeBinding
import com.pak.joblogicproblem.fragments.HomeFragmentDirections

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root = binding.root


        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.insertDataIntoTable(requireContext())

        binding.contactBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToContactsFragment("call")

            findNavController().navigate(action)
        }
        binding.buyBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToContactsFragment("buy")
            findNavController().navigate(action)
        }

        binding.sellBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToContactsFragment("sell")
            findNavController().navigate(action)
        }

        return root
    }


}