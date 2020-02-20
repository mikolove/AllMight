package com.mikolove.allmight.database.entities

import android.view.View

data class AddExercise(val id_exercise : Int,
                       val name : String,
                       val name_type : String,
                       val rep_count : Int,
                       val set_count : Int,
                       val id_type : Int,
                       var is_selected : Int) : BasicInfo{

    override fun getObjectId(): Int = id_exercise

    override fun getObjectName(): String = name

    fun getVisibility() : Int{
        return when(is_selected) {
            0 ->View.INVISIBLE
            1 -> View.VISIBLE
            else -> View.GONE
        }
    }
}