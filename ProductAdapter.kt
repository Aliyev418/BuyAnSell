package com.buyansell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.buyansell.models.Product

class ProductAdapter(private val context: Context, private val products: List<Product>) : BaseAdapter() {

    override fun getCount(): Int = products.size

    override fun getItem(position: Int): Any = products[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)

        val product = products[position]

        val nameText: TextView = view.findViewById(R.id.productName)
        val quantityText: TextView = view.findViewById(R.id.productQuantity)
        val priceText: TextView = view.findViewById(R.id.productPrice)
        val checkbox: CheckBox = view.findViewById(R.id.productCheckbox)

        nameText.text = product.name
        quantityText.text = "Say: ${product.quantity}"
        priceText.text = "Qiymət: ${product.salePrice} AZN"

        // Checkbox işarələmə funksiyası əlavə et

        return view
    }
}
