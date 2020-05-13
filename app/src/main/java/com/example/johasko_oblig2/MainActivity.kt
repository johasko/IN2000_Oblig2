package com.example.johasko_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var liste = mutableListOf<Alpaca>()
    private lateinit var rVAdapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tag = "MainActivity"
        val link = "https://in2000-alpakkaproxy.ifi.uio.no/alpakka2000"

        val gson = Gson()

        CoroutineScope(Dispatchers.Main).launch() {

            progressBar.visibility = View.VISIBLE

            val respons = Fuel.get(link).awaitString()
            Log.d(tag, respons)
            //For aa unngaa ClassCastException:
            val mutableListAlpaca = object : TypeToken<MutableList<Alpaca>>(){}.type
            liste = gson.fromJson(respons, mutableListAlpaca)

            delay(3000)

            if (liste.size == 0) {
                Toast.makeText(this@MainActivity, "Linken inneholder ingen alpakkaer", Toast.LENGTH_SHORT).show()
            }

            initRecyclerView()
            progressBar.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            rVAdapter = ListAdapter(liste)
            adapter = rVAdapter
        }
    }


}
