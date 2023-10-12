package com.example.bikestore

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bikestore.data.Bike
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BorrowActivity : AppCompatActivity() {
    private lateinit var daysSelector:SeekBar
    private lateinit var totalPrice:TextView
    private lateinit var btnSave:Button
    private lateinit var selectedBike:Bike
    private lateinit var thumbTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrow)
        val selectedBikePosition = intent.getIntExtra("selectedPosition",0)
        daysSelector = findViewById(R.id.progress_days)
        totalPrice = findViewById(R.id.bike_borrow_rate)
        btnSave = findViewById(R.id.btn_save)
        thumbTextView = findViewById(R.id.thumb_textview)
        selectedBike = MainActivity.bikesList[selectedBikePosition]
        totalPrice.text = "$${selectedBike.borrowRatePerDay}"

        daysSelector.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val total = (selectedBike.borrowRatePerDay * seekBar!!.progress)
                totalPrice.text = "$$total"
                thumbTextView.text = progress.toString()
                val thumb = seekBar.thumb
                val thumbBounds = thumb?.bounds
                val thumbTextX = (thumbBounds?.centerX()?.minus(50)) ?: 0

                thumbTextView.x = thumbTextX.toFloat()
                thumbTextView.text = progress.toString()



            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                val thumb = seekBar?.thumb
                val thumbBounds = thumb?.bounds
                val thumbTextX = thumbBounds?.centerX() ?: 0
                thumbTextView.text = seekBar?.progress.toString()
                thumbTextView.x = thumbTextX.toFloat()-50
                thumbTextView.visibility = View.VISIBLE
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                thumbTextView.visibility = View.INVISIBLE

            }

        })

        btnSave.setOnClickListener {
            if(daysSelector.progress == 0){
                val snackbar = Snackbar.make(btnSave, "Please select some days!", Snackbar.LENGTH_SHORT)
                snackbar.show()
            } else {
                MainActivity.bikesList[selectedBikePosition].isBorrowed = true
                MainActivity.bikesList[selectedBikePosition].borrowedDate = calculateDueDate(daysSelector.progress)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun calculateDueDate(daysToAdd: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dueDate = calendar.time
        return dateFormat.format(dueDate)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
    }
}