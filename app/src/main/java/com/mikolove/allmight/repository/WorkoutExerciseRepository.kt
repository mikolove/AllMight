package com.mikolove.allmight.repository

import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutExercise

class WorkoutExerciseRepository(val dataSource: AllmightDatabase) {

    fun add(id_workout : Int, id_exercise : Int){
        dataSource.workoutExerciseDao().insert(WorkoutExercise(id_workout,id_exercise))
    }

   fun delete(id_workout : Int, id_exercise : Int){
        dataSource.workoutExerciseDao().delete(WorkoutExercise(id_workout,id_exercise))
    }
}