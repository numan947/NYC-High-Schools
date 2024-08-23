package com.numan947.nychighschools.ui.schoollist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.numan947.nychighschools.R
import com.numan947.nychighschools.databinding.FragmentItemListBinding
import com.numan947.nychighschools.databinding.FragmentItemListBindingImpl
import com.numan947.nychighschools.placeholder.PlaceholderContent
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class SchoolFragment : Fragment() {
    private val viewModel: SchoolListViewModel by viewModels()
    private lateinit var binding:FragmentItemListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.data.observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isSuccessful) {
                    // recyclerview
                    val adapter = MySchoolRecyclerViewAdapter(it.body()!!)
                    recyclerView.adapter = adapter
                    recyclerView.visibility = View.VISIBLE
                    // toolbar buttons
                    refresh.visibility = View.VISIBLE
                    savedSchools.visibility = View.VISIBLE
                    // progress bar and error text
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.GONE

                } else {
                    // recyclerview
                    recyclerView.visibility = View.INVISIBLE
                    // toolbar buttons
                    binding.refresh.visibility = View.VISIBLE
                    binding.savedSchools.visibility = View.VISIBLE
                    // progress bar and error text
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = "Error: ${it.errorBody().toString()}"
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.schoolListToolbar.title = "NYC High Schools"

        binding.refresh.setOnClickListener {
            loadSchools()
        }
        binding.savedSchools.setOnClickListener {
            Toast.makeText(context, "Saved Schools", Toast.LENGTH_SHORT).show()
        }

        loadSchools()
        return binding.root
    }

    private fun loadSchools(){
        // hide recyclerview
        binding.recyclerView.visibility = View.INVISIBLE
        // hide toolbar buttons
        binding.savedSchools.visibility = View.GONE
        binding.refresh.visibility = View.GONE
        // show progress bar
        binding.progressBar.visibility = View.VISIBLE
        binding.errorText.visibility = View.GONE

        viewModel.getHighSchools()
    }
}