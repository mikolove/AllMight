package com.mikolove.allmight.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mikolove.allmight.database.entities.BasicInfo

class SpinnerAdapter<T>(context : Context, private val layout : Int, private val dataSet : List<T>) : ArrayAdapter<T>(context , layout, dataSet) where T : BasicInfo {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(layout,parent,false)
        val text = view.findViewById<TextView>(android.R.id.text1)
        text.text = getItem(position)?.getObjectName()
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position,convertView,parent)
    }

    override fun getItem(position: Int): T? {
        return dataSet[position]
    }

    override fun getCount(): Int {
        return dataSet.size
    }


}