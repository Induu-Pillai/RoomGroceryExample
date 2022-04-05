package com.example.roomexample.UI

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.example.roomexample.DialogListener
import com.example.roomexample.GroceryItems
import com.example.roomexample.R

class GroceryItemDialog(context: Context , val dialogListener: DialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.grocerydialog)

        val tvSave = findViewById<TextView>(R.id.tvSave)
        val tvCancel = findViewById<TextView>(R.id.tvCancel)
        val etItemName = findViewById<EditText>(R.id.etItemName)
        val etItemQuantity = findViewById<EditText>(R.id.etItemQuantity)
        val etItemPrice = findViewById<EditText>(R.id.etItemPrice)

        // Click listener on Save button
        // to save all data.
        tvSave?.setOnClickListener {

            // Take all three inputs in different variables from user
            // and add it in Grocery Items database
            val name = etItemName?.text.toString()
            val quantity = etItemQuantity?.text.toString()
            val price = etItemPrice?.text.toString()

            // Toast to display enter items in edit text
            if (name.isEmpty()) {
                Toast.makeText(context, "Please Enter Item Name", Toast.LENGTH_SHORT).show()
            }

            val item = GroceryItems(name, quantity, price)
            dialogListener.onAddButtonClicked(item)
            dismiss()
        }

        // On click listener on cancel text to close dialog box
        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}