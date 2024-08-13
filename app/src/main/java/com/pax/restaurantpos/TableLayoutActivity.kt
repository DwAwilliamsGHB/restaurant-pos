package com.pax.restaurantpos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TableLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_table_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up table selection logic
        findViewById<CardView>(R.id.table1).setOnClickListener {
            navigateToOrderEntry("Table 1")
        }

        findViewById<CardView>(R.id.table2).setOnClickListener {
            navigateToOrderEntry("Table 2")
        }


        // Add listeners for other tables here
    }

    private fun navigateToOrderEntry(tableName: String) {
        val intent = Intent(this, OrderEntryActivity::class.java)
        intent.putExtra("TABLE_NAME", tableName)
        startActivity(intent)
    }
}
