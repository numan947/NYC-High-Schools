package com.numan947.nychighschools.ui.schoollist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.numan947.nychighschools.placeholder.PlaceholderContent.PlaceholderItem
import com.numan947.nychighschools.databinding.FragmentSchoolBinding
import com.numan947.nychighschools.domain.HighSchoolListItem


class MySchoolRecyclerViewAdapter(
    private val values: ArrayList<HighSchoolListItem>
) : RecyclerView.Adapter<MySchoolRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentSchoolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentSchoolBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.schoolName
        val location: TextView = binding.location
        val phone: TextView = binding.phoneNumber
        val website: TextView = binding.website
        val email: TextView = binding.email

        fun bind(item: HighSchoolListItem) {
            name.text = item.school_name
            location.text = buildString {
                append("Location: ")
                append(item.primary_address_line_1)
                append(", ")
                append(item.city)
                append(", ")
                append(item.state_code)
                append(", ")
                append(item.zip)
            }
            phone.text = buildString {
                append("Phone: ")
                append(item.phone_number)
            }
            website.text = buildString {
                append("Website: ")
                append(item.website)
            }
            email.text = buildString {
                append("Email: ")
                append(item.school_email)
            }
        }
    }

}