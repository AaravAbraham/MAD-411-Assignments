package com.example.androidassignment7

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

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
        expenseAdapter = ExpenseAdapter(expenseList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = expenseAdapter

        // add expense button
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
                    expenseNameEditText.text.clear()
                    expenseAmountEditText.text.clear()
                    Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Data class for Expense
    data class Expense(val name: String, val amount: Float)
}
