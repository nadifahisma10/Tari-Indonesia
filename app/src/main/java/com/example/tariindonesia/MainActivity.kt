package com.example.tariindonesia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rv_list: RecyclerView
    private val list = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list = findViewById(R.id.rv_list)
        rv_list.setHasFixedSize(true)

        list.addAll(getListDance())
        showRecyclerList()
    }

    // Menampilkan opsi menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Menangani klik pada menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rv_list.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rv_list.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.action_search -> {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

    // Mendapatkan data dari resources
    private fun getListDance(): ArrayList<Person> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDanceAdapter = ArrayList<Person>()
        for (i in dataName.indices) {
            val dance = Person(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listDanceAdapter.add(dance)
        }
        return listDanceAdapter
    }

    // Menampilkan recycler view dengan data list tari
    private fun showRecyclerList() {
        rv_list.layoutManager = LinearLayoutManager(this)
        val listDanceAdapter = ListDanceAdapter(list)
        rv_list.adapter = listDanceAdapter

        listDanceAdapter.setOnItemClickCallback(object : ListDanceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Person) {
                showSelectedDance(data)
            }
        })
    }

    // Menampilkan detail dari tari yang dipilih
    private fun showSelectedDance(dance: Person) {
        Toast.makeText(this, "Kamu memilih " + dance.name, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("dance_name", dance.name)
            putExtra("dance_description", dance.description)
            putExtra("dance_photo", dance.photo)
        }

        Log.d("MainActivity", "Navigating to DetailActivity")
        startActivity(intent)
    }
}
