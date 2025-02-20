package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class DisplayActivity : AppCompatActivity() {

    // Step 1: Launch TextSizeActivity when button clicked to allow selection of text size value
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Step 3: Use returned value for lyricsDisplayTextView text size
        if (result.resultCode == RESULT_OK) {
            val textSize = result.data?.getIntExtra(TEXT_SIZE_EXTRA, DEFAULT_TEXT_SIZE)
            textSize?.let {
                lyricsDisplayTextView.textSize = it.toFloat()
            }
        }
    }

    companion object {
        const val TEXT_SIZE_EXTRA = "textSize"
        const val DEFAULT_TEXT_SIZE = 20
    }

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

        textSizeSelectorButton.setOnClickListener {
            // Launch TextSizeActivity
            launcher.launch(Intent(this, TextSizeActivity::class.java))
        }
    }
}