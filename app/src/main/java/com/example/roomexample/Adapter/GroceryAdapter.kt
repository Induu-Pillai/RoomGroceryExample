package com.example.roomexample.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.GroceryItems
import com.example.roomexample.R
import com.example.roomexample.UI.GroceryViewModel

class GroceryAdapter(var list : List<GroceryItems> , val viewModel: GroceryViewModel) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {
    class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val txtItemName = itemView.findViewById<TextView>(R.id.txtItemName)
      val txtItemPrice = itemView.findViewById<TextView>(R.id.txtItemPrice)
      val txtItemQuantity = itemView.findViewById<TextView>(R.id.txtItemQuantity)
      val txtItemTotalCost = itemView.findViewById<TextView>(R.id.txtItemTotalCost)
      val txtTotalCostTitle = itemView.findViewById<TextView>(R.id.txtTotalCostTitle)
      val ibDelete = itemView.findViewById<ImageButton>(R.id.ibDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.groceryadapter, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        var currentPosition = list[position]
        holder.txtItemName.text = currentPosition.itemName
        holder.txtItemPrice.text = "${currentPosition.itemPrice}"
        holder.txtItemQuantity.text = "${currentPosition.itemQuantity}"
        holder.ibDelete.setOnClickListener {
            viewModel.delete(currentPosition)
        }

        // To get total cost
        if (position == list.size - 1) {
            var totalCost = 0
            for (i in 0 until list.size) {
                totalCost = list[i].itemPrice.toInt()
            }
            holder.txtItemTotalCost.visibility = View.VISIBLE
            holder.txtTotalCostTitle.visibility = View.VISIBLE
            holder.txtItemTotalCost.text = "$totalCost"
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}