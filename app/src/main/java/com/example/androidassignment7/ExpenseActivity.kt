package com.example.androidassignment7
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense) // Name of your layout XML

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Get the data from Intent
        val expenseName = intent.getStringExtra("expense_name") ?: "No name"
        val expenseAmount = intent.getFloatExtra("expense_amount", 0f)

        // Set the values to TextViews
        findViewById<TextView>(R.id.expense_name_details).text = expenseName
        findViewById<TextView>(R.id.expense_amount_details).text = "$$expenseAmount"
    }

}

