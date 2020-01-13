package com.mikolove.allmight

import androidx.databinding.DataBindingComponent
import com.mikolove.allmight.extensions.InverseSpinnerBindings
import com.mikolove.allmight.extensions.SpinnerBindings

class BindingComponent : DataBindingComponent {

    override fun getSpinnerBindings(): SpinnerBindings {
        return SpinnerBindings()
    }

    override fun getInverseSpinnerBindings(): InverseSpinnerBindings {
        return InverseSpinnerBindings()
    }
}