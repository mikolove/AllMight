package com.mikolove.allmight.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mikolove.allmight.database.AllmightDatabase
import timber.log.Timber

@Entity(tableName = AllmightDatabase.workoutTypeTableName,
    indices = [Index("id")])
data class WorkoutType(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String? = null
)  : BasicInfo{

    override fun getObjectId(): Int?  = id
    override fun getObjectName(): String? = name

    companion object{

        fun populate() : List<WorkoutType>{
            Timber.i("GO TO POPULATE")
            return listOf(
                WorkoutType(1,"Pectoraux"),
                WorkoutType(2,"Bras"),
                WorkoutType(3,"Dos"),
                WorkoutType(4,"Epaules"),
                WorkoutType(5,"Jambes"),
                WorkoutType(6,"Abdominaux")
            )
        }
    }
}
