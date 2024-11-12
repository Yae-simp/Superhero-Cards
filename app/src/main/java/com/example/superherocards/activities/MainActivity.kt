package com.example.superherocards.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superherocards.R
import com.example.superherocards.adapters.SuperheroAdapter
import com.example.superherocards.data.Superhero
import com.example.superherocards.databinding.ActivityMainBinding
import com.example.superherocards.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //Binds individual data items directly to specific UI components. Does not create views.
    private lateinit var binding: ActivityMainBinding

    //Binds a list of data to a UI component. Creates view for each item.
    private lateinit var adapter: SuperheroAdapter
    private var superheroList: List<Superhero> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inflates layout and gets the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Sets root view of the binding object as the content view
        setContentView(binding.root) //root is the container view that holds everything else (in this case, recyclerView).

        adapter = SuperheroAdapter(superheroList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        searchHero("a")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_act, menu)

        val menuItem = menu?.findItem(R.id.search)!!
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchHero(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
        )
        return true
    }

    private fun searchHero (query: String) {
        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = service.findSuperheroesByName(query)

                CoroutineScope(Dispatchers.Main).launch {
                    if (result.response == "success") {
                        adapter.updateItems(result.results)
                    } else {
                        println("error")
                    }
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}
/* GitHub Superhero API access token: 17f1ccff525ef3de4f98aeb8c2339b9d */