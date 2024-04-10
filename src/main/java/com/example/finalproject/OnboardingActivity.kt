package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.OnboardingAdapter
import com.example.finalproject.OnboardingItem
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val onboardingItems = listOf(
        OnboardingItem(
            title = "Welcome to Hayda Lebnen!",
            description = "Explore the best of Lebanese cuisine.",
            image = R.drawable.food_image
        ),
        OnboardingItem(
            title = "Discover Lebanon",
            description = "Find the best places to visit and stay.",
            image = R.drawable.location_image
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(
            onboardingItems,
            onNextClick = { position ->
                binding.viewPager.currentItem = position + 1
            },
            onGetStartedClick = {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        )
        binding.viewPager.adapter = adapter
    }
}



