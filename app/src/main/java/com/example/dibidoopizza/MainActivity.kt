package com.example.dibidoopizza
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var textViewDisplayName: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating instance of the objects
        editTextName = findViewById(R.id.editText_name)
        textViewDisplayName = findViewById(R.id.textView_displayName)
        val buttonShowName: Button = findViewById(R.id.button_showName)

        //button event listener
        buttonShowName.setOnClickListener {
            val name = editTextName.text.toString().trim()
            //condition to display the text and name
            if (name.isNotEmpty()) {
                textViewDisplayName.text = "Hello, $name!"
            } else {
                Toast.makeText(this, "Please enter a name.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
