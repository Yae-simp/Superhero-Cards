package com.example.superherocards.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.superherocards.R
import com.example.superherocards.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitProvider.getRetrofit()


        CoroutineScope(Dispatchers.IO).launch {
            val result = service.findSuperheroesByName("cyborg superman")
            println(result)
        }
    }
}
/* GitHub Superhero API access token: 17f1ccff525ef3de4f98aeb8c2339b9d */