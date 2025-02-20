package edu.temple.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TextSizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Create array of integers that are multiples of 5
        val textSizes = Array(20) { (it + 1) * 5 }


        with(findViewById<RecyclerView>(R.id.textSizeSelectorRecyclerView)) {
            // Step 2: Pass selected value back to activity that launched TextSizeActivity
            adapter = TextSizeAdapter(textSizes) { selectedTextSize ->
                val resultIntent = Intent().apply {
                    putExtra(DisplayActivity.TEXT_SIZE_EXTRA, selectedTextSize)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            layoutManager = LinearLayoutManager(this@TextSizeActivity)
        }
    }
}

class TextSizeAdapter(
    private val textSizes: Array<Int>,
    private val callback: (Int) -> Unit
) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        init {
            textView.setOnClickListener { callback(textSizes[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply {
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()
        }
    }

    override fun getItemCount(): Int {
        return textSizes.size
    }
}





