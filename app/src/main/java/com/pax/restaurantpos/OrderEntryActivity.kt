package com.pax.restaurantpos

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback

class OrderEntryActivity : AppCompatActivity() {

    private val orderItems = mutableListOf<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_entry)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tableName = intent.getStringExtra("TABLE_NAME")
        val summaryText = getString(R.string.order_for_table, tableName)
        findViewById<TextView>(R.id.summaryTitle).text = summaryText

        val menuItems = listOf(
            MenuItem("Pizza", 10.99),
            MenuItem("Burger", 8.99),
            MenuItem("Pasta", 12.99)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.menuRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuAdapter(menuItems) { menuItem ->
            addItemToOrder(menuItem)
        }

        findViewById<Button>(R.id.proceedToPaymentButton).setOnClickListener {
            proceedToPayment()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAfterTransition()
            }
        })
    }

    private fun addItemToOrder(menuItem: MenuItem) {
        if (!orderItems.contains(menuItem)) {
            orderItems.add(menuItem)
            updateOrderSummary()
        } else {
            Toast.makeText(this, "${menuItem.name} is already in the order.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateOrderSummary() {
        val summaryLayout = findViewById<LinearLayout>(R.id.orderSummaryLayout)
        summaryLayout.removeAllViews()

        for (item in orderItems) {
            val textView = TextView(this)
            val itemText = getString(R.string.order_item, item.name, item.price)
            textView.text = itemText
            summaryLayout.addView(textView)
        }
    }

    private fun proceedToPayment() {
        if (orderItems.isEmpty()) {
            Toast.makeText(this, "No items in the order.", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, PaymentActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(intent, options.toBundle())
        }
    }

}
