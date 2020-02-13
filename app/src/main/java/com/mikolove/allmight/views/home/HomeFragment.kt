package com.mikolove.allmight.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mikolove.allmight.R
import com.mikolove.allmight.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)

        return binding.root
    }


}