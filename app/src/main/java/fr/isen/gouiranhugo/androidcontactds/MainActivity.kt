package fr.isen.gouiranhugo.androidcontactds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.gouiranhugo.androidcontactds.databinding.ActivityMainBinding
import com.android.volley.Request
import com.google.gson.Gson
import fr.isen.gouiranhugo.androidcontactds.model.Model
import fr.isen.gouiranhugo.androidcontactds.model.Results
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(applicationContext)
        binding.contactsList.layoutManager = layoutManager
        binding.contactsList.adapter = ContactAdapter(arrayListOf()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Detail", it)
            startActivity(intent)
        }

        loadContactsFromAPI()
    }

    private fun loadContactsFromAPI() {
        Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonObject = JSONObject()
        jsonObject.put("", "")
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, jsonObject, {
            Log.w("MainActivity", "response : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("MainActivity", "erreur: $it")
        }
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        val contactResult = Gson().fromJson(data, Model::class.java)
        val contactFiltered = contactResult.data//.firstOrNull{ it.nameFr == category}
        val adapter = binding.contactsList.adapter as ContactAdapter
        adapter.refreshList(contactFiltered as ArrayList<Results>)
    }

}


