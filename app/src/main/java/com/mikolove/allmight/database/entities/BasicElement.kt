package com.mikolove.allmight.database.entities

class BasicElement( val id : Int, val name : String) : BasicInfo{

    override fun getObjectId(): Int = id
    override fun getObjectName(): String? = name
}