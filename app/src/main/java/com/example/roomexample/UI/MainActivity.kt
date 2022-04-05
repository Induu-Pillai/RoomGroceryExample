package com.example.roomexample.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.Adapter.GroceryAdapter
import com.example.roomexample.Database.GroceryDatabase
import com.example.roomexample.Database.GroceryRepository
import com.example.roomexample.DialogListener
import com.example.roomexample.GroceryItems
import com.example.roomexample.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : GroceryViewModel
    lateinit var list : List<GroceryItems>
    lateinit var rvList: RecyclerView
    lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rvList)
        btnAdd = findViewById(R.id.btnAdd)

        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)

        //Initialized View Model
        viewModel = ViewModelProvider(this , factory).get(GroceryViewModel::class.java)
        val groceryAdapter = GroceryAdapter(listOf() , viewModel)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = groceryAdapter

        //to display all items in recyclerview
        viewModel.allGroceryItems().observe(this , Observer {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
        })

        //btn click
        btnAdd.setOnClickListener {
            GroceryItemDialog(this , object : DialogListener {
                override fun onAddButtonClicked(item: GroceryItems) {
                    viewModel.insert(item)
                }

            }).show()
        }

    }
}