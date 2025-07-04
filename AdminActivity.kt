package com.buyansell

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buyansell.models.Product
import java.util.*

class AdminActivity : AppCompatActivity() {

    private val productList = mutableListOf<Product>()
    private lateinit var productAdapter: ProductAdapter

    private lateinit var listView: ListView
    private lateinit var addButton: Button

    private lateinit var nameInput: EditText
    private lateinit var barcodeInput: EditText
    private lateinit var purchasePriceInput: EditText
    private lateinit var salePriceInput: EditText
    private lateinit var quantityInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        listView = findViewById(R.id.productListView)
        addButton = findViewById(R.id.addProductButton)

        nameInput = findViewById(R.id.nameInput)
        barcodeInput = findViewById(R.id.barcodeInput)
        purchasePriceInput = findViewById(R.id.purchasePriceInput)
        salePriceInput = findViewById(R.id.salePriceInput)
        quantityInput = findViewById(R.id.quantityInput)

        productAdapter = ProductAdapter(this, productList)
        listView.adapter = productAdapter

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val barcode = barcodeInput.text.toString()
            val purchasePrice = purchasePriceInput.text.toString().toDoubleOrNull()
            val salePrice = salePriceInput.text.toString().toDoubleOrNull()
            val quantity = quantityInput.text.toString().toIntOrNull()

            if (name.isEmpty() || barcode.isEmpty() || purchasePrice == null || salePrice == null || quantity == null) {
                Toast.makeText(this, "Zəhmət olmasa bütün sahələri doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val product = Product(
                id = UUID.randomUUID().toString(),
                name = name,
                barcode = barcode,
                purchasePrice = purchasePrice,
                salePrice = salePrice,
                quantity = quantity
            )
            productList.add(product)
            productAdapter.notifyDataSetChanged()

            nameInput.text.clear()
            barcodeInput.text.clear()
            purchasePriceInput.text.clear()
            salePriceInput.text.clear()
            quantityInput.text.clear()

            Toast.makeText(this, "Məhsul əlavə olundu", Toast.LENGTH_SHORT).show()
        }
    }
}
