package com.pax.restaurantpos

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.OnBackPressedCallback


class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.payByCardButton).setOnClickListener {
            processPayment("Card")
        }

        findViewById<Button>(R.id.payByCashButton).setOnClickListener {
            processPayment("Cash")
        }

        findViewById<Button>(R.id.printReceiptButton).setOnClickListener {
            printReceipt()
        }

        // Handle back button with custom transition
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAfterTransition()  // Ends the activity with a transition
            }
        })
    }

    private fun processPayment(method: String) {
        Toast.makeText(this, "Payment processed with $method", Toast.LENGTH_SHORT).show()
    }

    private fun printReceipt() {
        Toast.makeText(this, "Receipt printed", Toast.LENGTH_SHORT).show()
    }
}
