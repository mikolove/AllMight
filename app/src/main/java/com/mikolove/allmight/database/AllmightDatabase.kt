package com.mikolove.allmight.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mikolove.allmight.database.entities.*
import com.mikolove.allmight.database.dao.*
import timber.log.Timber
import java.util.concurrent.Executors

@Database(entities = [WorkoutType::class, Workout::class, Exercise::class, WorkoutExercise::class, Routine::class, RoutineExercise::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class AllmightDatabase : RoomDatabase() {

    abstract fun workoutTypeDao() : WorkoutTypeDao
    abstract fun workoutDao() : WorkoutDao
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
                        "all_might_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback( object : Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Timber.i("DATABASE CALLBACK")
                                Executors.newSingleThreadExecutor().execute{
                                    getInstance(context).workoutTypeDao().insertAll(WorkoutType.populate())
                                }
                            }
                        })
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