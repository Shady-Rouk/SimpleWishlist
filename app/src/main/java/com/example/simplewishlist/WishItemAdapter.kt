package com.example.simplewishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishItemAdapter (private val wishListItems: MutableList<WishItem>) : RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemNameTextView: TextView
        val itemPriceTextView: TextView
        val itemLinkTextView: TextView

        init {
            itemNameTextView = itemView.findViewById(R.id.itemName)
            itemPriceTextView = itemView.findViewById(R.id.itemPrice)
            itemLinkTextView = itemView.findViewById(R.id.itemLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishItemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wish_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: WishItemAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val wishListItem = wishListItems.get(position)
        // Set item views based on views and data model
        holder.itemNameTextView.text = wishListItem.itemName
        holder.itemPriceTextView.text = wishListItem.itemPrice.toString()
        holder.itemLinkTextView.text = wishListItem.itemLink

        // Listener to open item url in a browser window
        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wishListItem.itemLink))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + wishListItem.itemName, Toast.LENGTH_LONG).show()
            }
        }

        // Listener to delete a list item on long click
        holder.itemView.setOnLongClickListener {
            wishListItems.removeAt(holder.adapterPosition)
            this.notifyDataSetChanged()
            true
        }
    }

    override fun getItemCount(): Int {
        return wishListItems.size
    }
}