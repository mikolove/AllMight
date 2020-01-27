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
import androidx.viewpager2.widget.ViewPager2.*
import com.google.android.material.tabs.TabLayoutMediator
import com.mikolove.allmight.R
import com.mikolove.allmight.adapters.SettingsPagerAdapter
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.databinding.FragmentHomeSettingsBinding
import timber.log.Timber


class HomeSettingsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentHomeSettingsBinding  = DataBindingUtil.inflate(inflater,R.layout.fragment_home_settings,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = AllmightDatabase.getInstance(application)

        val viewModelFactory = HomeSettingsViewModelFactory( dataSource, application)

        val homeSettingsViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeSettingsViewModel::class.java)

        binding.homeSettingsViewModel = homeSettingsViewModel

        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.homeSettingsViewpager.adapter = SettingsPagerAdapter(this)

        TabLayoutMediator(binding.homeSettingsTabLayout, binding.homeSettingsViewpager) { tab, position ->
            tab.text = getTitle(position)
        }.attach()

        homeSettingsViewModel.navigateToDetails.observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                when(binding.homeSettingsViewpager.currentItem){
                    0 ->{
                        this.findNavController().navigate(HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailWorkoutSettingsFragment().setWorkoutId(it))
                        homeSettingsViewModel.doneNavigatingToDetails() }
                    1 ->{
                        this.findNavController().navigate(HomeSettingsFragmentDirections.actionHomeSettingsFragmentToDetailExerciseSettingsFragment().setExerciseId(it))
                        homeSettingsViewModel.doneNavigatingToDetails() }
                    else -> {}
                }
            }
        })
        return binding.root
    }

    fun getTitle(position : Int) : String{
        return when(position){
            0 -> getString(R.string.workout_title)
            1 -> getString(R.string.exercice_title)
            else -> getString(R.string.test)
        }
    }
}