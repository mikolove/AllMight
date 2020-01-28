package com.mikolove.allmight.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SpinnerIntAdapter(context : Context, private val layout : Int, private val dataSet : List<Int>) : ArrayAdapter<Int>(context , layout, dataSet) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(layout,parent,false)
        val text = view.findViewById<TextView>(android.R.id.text1)
        text.text = getItem(position).toString()
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position,convertView,parent)
    }

    override fun getItem(position: Int): Int {
        return dataSet[position]
    }

    override fun getCount(): Int {
        return dataSet.size
    }


}