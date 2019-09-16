package com.mikolove.allmight.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mikolove.allmight.database.Entities.*
import com.mikolove.allmight.database.dao.*

@Database(entities = [WorkoutType::class, Workout::class, Exercise::class, WorkoutExercise::class, Routine::class, RoutineExercise::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class AllmightDatabase : RoomDatabase() {

    abstract fun workoutTypeDao() : WorkoutTypeDao
    abstract fun wourkoutDao() : WorkoutDao
    abstract fun exerciseDao() : ExerciseDao
    abstract fun workoutExerciseDao() : WorkoutExerciseDao
    abstract fun routineDao() : RoutineDao
    abstract fun routineExerciseDao() : RoutineExerciseDao


    companion object{

        const val workoutTypeTableName = "workout_type"
        const val workoutTableName = "workout"
        const val exerciseTableName = "exercise"
        const val workoutExerciseTableName = "workout_exercise"
        const val routineTableName = "routine"
        const val routineExerciseTypeTableName = "routine_exercise"

        @Volatile
        private var INSTANCE: AllmightDatabase? = null

        fun getInstance(context: Context): AllmightDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AllmightDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        fun getTestInstance(context: Context): AllmightDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.inMemoryDatabaseBuilder(context, AllmightDatabase::class.java).build()
                    INSTANCE = instance
                }
                return instance
            }
        }


    }
}