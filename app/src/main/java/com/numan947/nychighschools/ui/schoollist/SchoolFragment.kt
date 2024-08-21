package com.numan947.nychighschools.ui.schoollist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.numan947.nychighschools.R
import com.numan947.nychighschools.databinding.FragmentItemListBinding
import com.numan947.nychighschools.databinding.FragmentItemListBindingImpl
import com.numan947.nychighschools.placeholder.PlaceholderContent
import dagger.hilt.android.AndroidEntryPoint


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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = MySchoolRecyclerViewAdapter(PlaceholderContent.ITEMS)

        binding.schoolListToolbar.title = "NYC High Schools"
        return binding.root
    }
}