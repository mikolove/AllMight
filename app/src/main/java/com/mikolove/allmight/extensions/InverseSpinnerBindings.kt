package com.mikolove.allmight.extensions

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.mikolove.allmight.database.entities.BasicInfo
import com.mikolove.allmight.extensions.SpinnerExtensions.getSpinnerValue
import com.mikolove.allmight.extensions.SpinnerExtensions.setSpinnerInverseBindingListener
import com.mikolove.allmight.extensions.SpinnerExtensions.setSpinnerValue
import timber.log.Timber

class InverseSpinnerBindings {

    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    @BindingAdapter("setSelectedData")
    fun Spinner.setSelectedData(selectedValue: BasicInfo?) {
        Timber.i("INVERSE SPINNER SET SELECTEDDATA %d %s", selectedValue?.getObjectId(),selectedValue?.getObjectName())
        setSpinnerValue(selectedValue)
    }

    companion object {
        @JvmStatic
        @InverseBindingAdapter(attribute = "setSelectedData", event = "selectedValueAttrChanged")
        fun Spinner.getSelectedData(): BasicInfo {
            return getSpinnerValue()
        }
    }

}
