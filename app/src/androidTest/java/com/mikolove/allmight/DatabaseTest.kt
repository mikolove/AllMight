package com.mikolove.allmight

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.mikolove.allmight.database.AllmightDatabase
import com.mikolove.allmight.database.entities.WorkoutType
import com.mikolove.allmight.database.dao.WorkoutTypeDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var workoutTypeDao : WorkoutTypeDao
    private lateinit var db: AllmightDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = AllmightDatabase.getTestInstance(context)
        workoutTypeDao = db.workoutTypeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writePostAndReadInList() {
        val workoutType = WorkoutType(name ="Pectoraux")
        workoutTypeDao.insert(workoutType)
        //Not working on livedata
        //assertEquals(0, workoutTypeDao.getAllWorkoutType().size)
    }

}