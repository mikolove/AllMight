package com.mikolove.allmight.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.InverseBindingListener
import com.mikolove.allmight.R
import com.mikolove.allmight.adapters.SpinnerAdapter
import com.mikolove.allmight.database.entities.BasicInfo

object SpinnerExtensions {

    fun Spinner.setSpinnerData(data : List<BasicInfo>?){
        if (data != null) {
            val arrayAdapter = SpinnerAdapter(
                context,
                R.layout.support_simple_spinner_dropdown_item,
                data
            )
            adapter = arrayAdapter
        }
    }

    fun Spinner.setSpinnerItemListener(listener : SpinnerItemListener){
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onItemSelected(parent.getItemAtPosition(position) as BasicInfo)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {
        if (listener == null) {
            onItemSelectedListener = null
        } else {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    fun Spinner.setSpinnerValue(item : BasicInfo?){
        if (adapter != null ) {
            val position = (adapter as ArrayAdapter<BasicInfo>).getPosition(item)
            setSelection(position, false)
            tag = position
        }
    }

    fun Spinner.getSpinnerValue(): BasicInfo {
        return selectedItem as BasicInfo
    }

    interface SpinnerItemListener {
        fun onItemSelected(item: BasicInfo)
    }
}