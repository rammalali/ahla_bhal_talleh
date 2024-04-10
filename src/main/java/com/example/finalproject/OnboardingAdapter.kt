package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(
    private val onboardingItems: List<OnboardingItem>,
    private val onNextClick: (Int) -> Unit,
    private val onGetStartedClick: () -> Unit
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val currentItem = onboardingItems[position]
        holder.bind(currentItem)

        holder.nextButton.apply {
            visibility = if (position == onboardingItems.size - 1) View.GONE else View.VISIBLE
            setOnClickListener { onNextClick(position) }
        }

        holder.getStartedButton.apply {
            visibility = if (position == onboardingItems.size - 1) View.VISIBLE else View.GONE
            setOnClickListener { onGetStartedClick() }
        }
    }

    override fun getItemCount(): Int = onboardingItems.size

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nextButton: Button = itemView.findViewById(R.id.nextButton)
        val getStartedButton: Button = itemView.findViewById(R.id.getStartedButton)

        fun bind(onboardingItem: OnboardingItem) {
            titleText.text = onboardingItem.title
            descriptionText.text = onboardingItem.description
            imageView.setImageResource(onboardingItem.image)
        }
    }
}

