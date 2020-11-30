package com.laboremus.santecoffee

import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.laboremus.santecoffee.db.FarmersDao
import org.junit.runner.RunWith
import javax.inject.Inject
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.db.FarmersDB
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DaoTest {

    @Inject
    lateinit var farmersDao: FarmersDao
    lateinit var db: FarmersDB

    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, FarmersDB::class.java).build()
        farmersDao = db.getFarmersDao()

        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()

//        lifeCycleTestOwner.onDestroy()
    }
    
    @Test
    @Throws(IOException::class)
    fun readAndWriteTests() {
        val farmer = Farmer(
            "Test Farmer",
            "Male",
            "URL",
            "NIN",
            "0789123455",
            0L,
            "Ivan")

        val farmer2 = Farmer(
            "Test Farmer 2",
            "Male",
            "URL",
            "NIN",
            "0789123455",
            0L,
            "Ivan")

        val farmer3 = Farmer(
            "Test Farmer 3",
            "Male",
            "URL",
            "NIN",
            "0789123455",
            0L,
            "Ivan")


        // insert
        val insertedFarmer = runBlocking {
            farmersDao.insertFarmer(farmer)
        }
        assertNotNull(insertedFarmer)

        // insert Farmers
        val insertedFarmers = runBlocking {
            farmersDao.insertFarmersList(listOf(
                farmer,farmer2,farmer3
            ))
        }
        assertNotNull(insertedFarmers)

        // return all Farmers
        runBlocking {
                withContext(Dispatchers.Main){
                    val results = farmersDao.getAllFarmersSortedByDate()
                    results.observe(lifeCycleTestOwner, Observer {farmer_list ->
                        Log.d("RESULTS_FARMER", farmer_list.toString())
                        assertNotNull(farmer_list)
                    })
                }
        }

        //update Farmer
        runBlocking {
            farmer.birthCertificateUrl = "New URL"
            farmer.phoneNumber = "12345678"
            farmer.Id = 1
            val updated = farmersDao.updateFarmer(farmer)
            assertEquals(1,updated)
        }

        // delete
        runBlocking {
            val rows_deleted = farmersDao.deleteAll()
            assertEquals(4,rows_deleted)
        }
    }
}
class LifeCycleTestOwner : LifecycleOwner {

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return registry
    }

    fun onCreate() {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun onResume() {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    fun onDestroy() {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}