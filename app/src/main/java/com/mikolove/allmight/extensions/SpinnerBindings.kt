package com.mikolove.allmight.extensions

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.extensions.SpinnerExtensions.setSpinnerData
import com.mikolove.allmight.extensions.SpinnerExtensions.setSpinnerItemListener
import com.mikolove.allmight.extensions.SpinnerExtensions.setSpinnerValue
import timber.log.Timber

class SpinnerBindings {

    @BindingAdapter("setData")
    fun Spinner.setData( dataset : List<BasicInfo>?){
        setSpinnerData(dataset)
    }

    @BindingAdapter("setItemListener")
    fun Spinner.setItemListener(listener : SpinnerExtensions.SpinnerItemListener){
        setSpinnerItemListener(listener)
    }

    @BindingAdapter( "setValue")
    fun Spinner.setValue(value : BasicInfo?){
        Timber.i("SET VALUE SPINNER")
        setSpinnerValue(value)
    }
}