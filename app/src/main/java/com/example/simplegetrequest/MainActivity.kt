package com.example.simplegetrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.simplegetrequest.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var queue : RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue = Volley.newRequestQueue(this)
        binding.btnUpdatePokemon.setOnClickListener{
            val listAmount = Integer.parseInt(binding.etPokemonAmount.text.toString())
            getPokemonList(5)
        }
    }

    fun getPokemonList(listAmount: Int) {
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"
        val jsonRequest = JsonObjectRequest(url,Response.Listener<JSONObject> { response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        },
        Response.ErrorListener { error ->
            Log.w("JSONRESPONSE",error.message as String)
        })

        queue.add(jsonRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}