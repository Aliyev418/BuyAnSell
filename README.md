# BuyAnSell
“Product management app”
package com.buyansell

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
package com.buyansell

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == "admin" && password == "admin123") {
                startActivity(Intent(this, AdminActivity::class.java))
                finish()
            } else if (username == "user1" && password == "user123") {
                startActivity(Intent(this, UserActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "İstifadəçi adı və ya şifrə yalnışdır", Toast.LENGTH_SHORT).show()
            }
        }
    }
} 
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
package com.buyansell.models

data class Product(
    val id: String,
    val name: String,
    val barcode: String,
    val purchasePrice: Double,
    val salePrice: Double,
    var quantity: Int,
    val imageUrl: String? = null,
    val minStock: Int = 5
)
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp">

    <EditText
        android:id="@+id/usernameInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="İstifadəçi adı" />

    <EditText
        android:id="@+id/passwordInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Şifrə"
        android:inputType="textPassword"
        android:layout_marginTop="16dp"/>

    <Button
        android:id="@+id/loginButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Daxil ol"
        android:layout_marginTop="24dp"/>
</LinearLayout>
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <ListView
        android:id="@+id/productListView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>

    <EditText
        android:id="@+id/nameInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Məhsul adı" />

    <EditText
        android:id="@+id/barcodeInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Barkod" />

    <EditText
        android:id="@+id/purchasePriceInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Alış qiyməti"
        android:inputType="numberDecimal" />

    <EditText
        android:id="@+id/salePriceInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Satış qiyməti"
        android:inputType="numberDecimal" />

    <EditText
        android:id="@+id/quantityInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Say"
        android:inputType="number" />

    <Button
        android:id="@+id/addProductButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Məhsul əlavə et"
        android:layout_marginTop="12dp"/>
</LinearLayout>
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <ListView
        android:id="@+id/productListView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>

    <Button
        android:id="@+id/requestButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Sifariş göndər"
        android:layout_marginTop="12dp"/>
</LinearLayout>
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="12dp">

    <TextView
        android:id="@+id/productName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Məhsul adı"
        android:textSize="18sp"
        android:textStyle="bold"/>

    <TextView
        android:id="@+id/productQuantity"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/productName"
        android:text="Say: 0"
        android:layout_marginTop="4dp"/>

    <TextView
        android:id="@+id/productPrice"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentEnd="true"
        android:text="Qiymət: 0 AZN"
        android:textSize="16sp"
        android:textStyle="bold"/>

    <CheckBox
        android:id="@+id/productCheckbox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_alignParentEnd="true"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"/>
</RelativeLayout>
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="primary">#6200EE</color>
    <color name="primary_dark">#3700B3</color>
    <color name="accent">#03DAC5</color>
    <color name="background">#FFFFFF</color>
    <color name="text_primary">#000000</color>
</resources>
<resources>
    <string name="app_name">BuyAnSell</string>
    <string name="login">Daxil ol</string>
    <string name="username_hint">İstifadəçi adı</string>
    <string name="password_hint">Şifrə</string>
    <string name="add_product">Məhsul əlavə et</string>
    <string name="request_order">Sifariş göndər</string>
</resources>
<resources>

    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorPrimaryDark">@color/primary_dark</item>
        <item name="colorAccent">@color/accent</item>
        <item name="android:windowBackground">@color/background</item>
    </style>

</resources>


