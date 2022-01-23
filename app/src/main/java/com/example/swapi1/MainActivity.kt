package com.example.swapi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swapi1.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter: PlanetasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val client = OkHttpClient()

        val request = Request.Builder()
        request.url("https://swapi.dev/api/planets")


        val call = client.newCall(request.build())
        call.enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)
                    val gson = Gson()

                    val planet = gson.fromJson(body, PlanetResponse::class.java)

                    println(planet)


                    CoroutineScope(Dispatchers.Main).launch {
                        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = PlanetasAdapter(planet.results)
                        binding.recyclerview.adapter = adapter

                    }
                }
            }
        })
    }

}
