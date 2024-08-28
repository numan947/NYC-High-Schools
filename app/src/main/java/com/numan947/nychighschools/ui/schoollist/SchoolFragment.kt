package com.numan947.nychighschools.ui.schoollist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.numan947.nychighschools.R
import com.numan947.nychighschools.databinding.FragmentItemListBinding
import com.numan947.nychighschools.domain.HighSchoolListItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SchoolFragment : Fragment() {
    private val viewModel: SchoolListViewModel by viewModels()
    private lateinit var binding: FragmentItemListBinding
    private var showSavedOnly: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.highSchoolListLiveData.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    // recyclerview
                    val adapter = MySchoolRecyclerViewAdapter(it) {
                        showSchoolDetails(it)
                    }
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
                    refresh.visibility = View.VISIBLE
                    savedSchools.visibility = View.VISIBLE
                    // progress bar and error text
                    errorText.visibility = View.VISIBLE
                    errorText.text = "Error loading schools"
                    progressBar.visibility = View.GONE
                }
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.schoolListToolbar.title = "NYC High Schools"

        binding.refresh.setOnClickListener {
            loadSchools(showSavedOnly, true)
        }
        binding.savedSchools.setOnClickListener {
            showSavedOnly = !showSavedOnly
            loadSchools(showSavedOnly)
            changeButtonIcon()
        }

        changeButtonIcon()
        loadSchools(showSavedOnly)
        return binding.root
    }

    private fun changeButtonIcon() {
        if (showSavedOnly) {
            binding.savedSchools.setImageResource(R.drawable.baseline_cloud_24)
        } else {
            binding.savedSchools.setImageResource(R.drawable.baseline_save_24)
        }
        binding.savedSchools.setColorFilter(ContextCompat.getColor(requireContext(), R.color.toolbarButtonTint))
    }

    private fun loadSchools(showSavedOnly: Boolean = false, forceNetwork: Boolean = false) {
        println("loadSchools: $showSavedOnly")
        binding.apply {
            // hide recyclerview
            recyclerView.visibility = View.INVISIBLE
            // hide toolbar buttons
            savedSchools.visibility = View.GONE
            refresh.visibility = View.GONE
            // show progress bar
            progressBar.visibility = View.VISIBLE
            errorText.visibility = View.GONE
        }
        if (showSavedOnly)
            viewModel.getHighSchoolsFromDb()
        else
            viewModel.getHighSchoolsFromNetwork(forceNetwork)
    }

    private fun showSchoolDetails(highSchoolListItem: HighSchoolListItem) {
        SchoolFragmentDirections.actionSchoolFragmentToSchoolDetails(highSchoolListItem) // safeargs
            .also { findNavController().navigate(it) }
    }
}