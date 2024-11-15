package com.example.superherocards.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superherocards.R
import com.example.superherocards.data.Superhero
import com.example.superherocards.databinding.ActivityDetailBinding
import com.example.superherocards.utils.RetrofitProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUPERHERO_ID = "SUPERHERO_ID"
    }

    private lateinit var binding: ActivityDetailBinding

    private lateinit var superhero: Superhero
    private lateinit var navigationBar: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra(EXTRA_SUPERHERO_ID)!!

        getSuperhero(id)

        navigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bio -> {
                    getSuperhero("biography")
                }
                R.id.menu_power -> {
                    getSuperhero("powerstats")
                }
                R.id.menu_work -> {
                    getSuperhero("work")
                }
            }
            return@setOnItemSelectedListener true
        }
        navigationBar.selectedItemId = R.id.menu_bio
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadData() {
        supportActionBar?.title = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.heroImageView)
    }

    private fun getSuperhero(id: String) {
        val service = RetrofitProvider.getRetrofit()


        binding.progressIndicator.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            try {
                superhero = service.findSuperheroById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()

                    binding.progressIndicator.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}