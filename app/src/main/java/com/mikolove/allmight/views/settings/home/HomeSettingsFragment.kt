package com.mikolove.allmight.views.settings.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mikolove.allmight.R
import com.mikolove.allmight.adapters.SettingsPagerAdapter
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentHomeSettingsBinding

class HomeSettingsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentHomeSettingsBinding  = DataBindingUtil.inflate(inflater,R.layout.fragment_home_settings,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory =
            HomeSettingsViewModelFactory(
                dataSource,
                application
            )

        val homeSettingsViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            HomeSettingsViewModel::class.java)

        binding.homeSettingsViewModel = homeSettingsViewModel

        binding.lifecycleOwner = this

        binding.homeSettingsViewpager.adapter = childFragmentManager?.let { SettingsPagerAdapter(it) }

        homeSettingsViewModel.navigateToDetailWorkout.observe(this, Observer { it ->
            it?.let{
                this.findNavController().navigate(HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailWorkoutSettingsFragment().setWorkoutId(it))
                homeSettingsViewModel.doneNavigatingToDetailWorkout()
            }
        })
        return binding.root
    }
}