package com.buyansell

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buyansell.models.Product

class UserActivity : AppCompatActivity() {

    private val productList = mutableListOf<Product>()
    private lateinit var productAdapter: ProductAdapter

    private lateinit var listView: ListView
    private lateinit var requestButton: Button

    private val selectedProducts = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        listView = findViewById(R.id.productListView)
        requestButton = findViewById(R.id.requestButton)

        productAdapter = ProductAdapter(this, productList)
        listView.adapter = productAdapter

        requestButton.setOnClickListener {
            if (selectedProducts.isEmpty()) {
                Toast.makeText(this, "Heç bir məhsul seçilməyib", Toast.LENGTH_SHORT).show()
            } else {
                // Sifariş göndərmə əməliyyatı - adminə göndərmə
                Toast.makeText(this, "Sifariş göndərildi", Toast.LENGTH_SHORT).show()
                selectedProducts.clear()
            }
        }
    }
}
