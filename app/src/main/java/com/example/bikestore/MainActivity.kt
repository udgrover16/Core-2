package com.example.bikestore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.bikestore.data.Bike
import com.example.bikestore.data.getBikesList
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var bikeImage:ImageView
    private lateinit var bikeName:TextView
    private lateinit var bikeDescription:TextView
    private lateinit var bikeRating:RatingBar
    private lateinit var bikeRatePerDay:TextView
    private lateinit var btnNext:Button
    private lateinit var btnBorrow:Button
    private lateinit var isCityBike:TextView
    private lateinit var containsBasket:TextView
    private lateinit var containsPanniers:TextView
    companion object {
        val bikesList = getBikesList()
    }
    private var currentBikePosition:Int = 0
    private lateinit var startBorrowActivity: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bikeImage = findViewById(R.id.bike_image)
        bikeName = findViewById(R.id.bike_name)
        bikeDescription = findViewById(R.id.bike_description)
        bikeRating = findViewById(R.id.bike_rating)
        bikeRatePerDay = findViewById(R.id.bike_rate_per_day)
        btnNext = findViewById(R.id.btn_next)
        btnBorrow = findViewById(R.id.btn_borrow)
        isCityBike = findViewById(R.id.is_city_bike)
        containsBasket = findViewById(R.id.contains_basket)
        containsPanniers = findViewById(R.id.contains_panniers)

        btnNext.setOnClickListener {
            currentBikePosition++
            if(currentBikePosition>=bikesList.size)
                currentBikePosition = 0
            val bike = bikesList[currentBikePosition]
            setData(bike)
        }

        startBorrowActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Item booked!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Booking cancelled!", Toast.LENGTH_SHORT).show()
            }
        }

        btnBorrow.setOnClickListener {
            val intent = Intent(this@MainActivity,BorrowActivity::class.java)
            intent.putExtra("selectedPosition",currentBikePosition)
            startBorrowActivity.launch(intent)
        }


    }

    private fun setData(bike:Bike){
            bikeImage.setImageResource(bike.bikeImage)
            bikeName.text = bike.bikeName
            bikeDescription.text = bike.bikeDescription
            bikeRating.rating = bike.bikeStars
            val panniersBackground =
                if(bike.features.containsPanniers){
                    R.drawable.selected_feature_background
                } else{
                    R.drawable.unselected_feature_background
                }
            containsPanniers.setBackgroundResource(panniersBackground)

        val basketBackground =
            if(bike.features.containsBasket){
                R.drawable.selected_feature_background
            } else{
                R.drawable.unselected_feature_background
            }
        containsBasket.setBackgroundResource(basketBackground)

        val isCityBackground =
            if(bike.features.isCityBike){
                R.drawable.selected_feature_background
            } else{
                R.drawable.unselected_feature_background
            }
        isCityBike.setBackgroundResource(isCityBackground)
        bikeRatePerDay.text = "$${bike.borrowRatePerDay}"
        if(bike.isBorrowed){
            btnBorrow.isEnabled = false
            btnBorrow.text = "Due back ${bike.borrowedDate}"
            btnBorrow.setTextColor(ContextCompat.getColor(this,R.color.disabled_button_color))
        } else{
            btnBorrow.isEnabled = true
            btnBorrow.text = "Borrow"
            btnBorrow.setTextColor(ContextCompat.getColor(this,R.color.white))
        }
    }
    override fun onResume() {
        super.onResume()
        setData(bikesList[currentBikePosition])
    }
}