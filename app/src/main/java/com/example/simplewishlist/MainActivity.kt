package com.example.simplewishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var items: MutableList<WishItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = ArrayList()
        val wishListItemsRv = findViewById<RecyclerView>(R.id.itemsRv)
        val adapter = WishItemAdapter(items)
        wishListItemsRv.adapter = adapter
        wishListItemsRv.layoutManager = LinearLayoutManager(this)

        // EditTextViews
        val itemNameView = findViewById<EditText>(R.id.itemNameInput)
        val itemPriceView = findViewById<EditText>(R.id.itemPriceInput)
        val itemLinkView = findViewById<EditText>(R.id.itemLinkInput)

        val submitBtn = findViewById<Button>(R.id.submitButton)
        submitBtn.setOnClickListener {
            items.add(WishItem(itemNameView.text.toString(), itemPriceView.text.toString().toDouble(), itemLinkView.text.toString()))
            itemNameView.setText("")
            itemPriceView.setText("")
            itemLinkView.setText("")
            adapter.notifyDataSetChanged()
        }
    }
}