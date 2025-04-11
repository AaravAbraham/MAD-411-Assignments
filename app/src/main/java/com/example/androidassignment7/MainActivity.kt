
package com.example.androidassignment7

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment7.R


import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var expenseNameEditText: EditText
    private lateinit var expenseAmountEditText: EditText
    private lateinit var addExpenseButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var expenseList: MutableList<Expense>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        expenseNameEditText = findViewById(R.id.expense_name_edit_text)
        expenseAmountEditText = findViewById(R.id.expense_amount_edit_text)
        addExpenseButton = findViewById(R.id.add_expense_button)
        recyclerView = findViewById(R.id.expense_list_recycler_view)

        // Initialize the expense list
        expenseList = mutableListOf()

        // Initialize the RecyclerView
        expenseAdapter = ExpenseAdapter(expenseList) { expense ->
            // This block is executed when the "Show Details" button is clicked
            val intent = Intent(this, ExpenseActivity::class.java).apply {
                putExtra("expense_name", expense.name)
                putExtra("expense_amount", expense.amount)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = expenseAdapter

        // Add expense button click listener
        addExpenseButton.setOnClickListener {
            val expenseName = expenseNameEditText.text.toString()
            val expenseAmount = expenseAmountEditText.text.toString()

            if (expenseName.isEmpty() || expenseAmount.isEmpty()) {
                Toast.makeText(this, "Expense Name and Amount are required!", Toast.LENGTH_SHORT).show()
            } else {
                val amount = expenseAmount.toFloatOrNull()
                if (amount == null) {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                } else {
                    val newExpense = Expense(expenseName, amount)
                    expenseList.add(newExpense)
                    expenseAdapter.notifyItemInserted(expenseList.size - 1)

                    // Clear the input fields
                    expenseNameEditText.text.clear()
                    expenseAmountEditText.text.clear()

                    // Show success toast
                    Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    data class Expense(val name: String, val amount: Float)
}
