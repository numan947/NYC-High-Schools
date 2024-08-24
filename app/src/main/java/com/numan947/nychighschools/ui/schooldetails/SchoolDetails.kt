package com.numan947.nychighschools.ui.schooldetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.numan947.nychighschools.R
import com.numan947.nychighschools.databinding.FragmentSchoolDetailsBinding
import com.numan947.nychighschools.domain.HighSchoolListItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SchoolDetails : Fragment() {
    private lateinit var binding: FragmentSchoolDetailsBinding
    private val viewModel: SchoolDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        arguments?.let {
            val args: SchoolDetailsArgs by navArgs()
            viewModel.setSchool(args.schoolData)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.apply {
                tvSchoolName.text = it.name
                tvSchoolAddress.text = buildString {
                    "Address: ${it.address}\n"
                }
                tvSchoolEmail.text = buildString {
                    append("Email: ")
                    append(it.email)
                }
                tvSchoolWebsite.text = buildString {
                    append("Website: ")
                    append(it.website)
                }
                tvSchoolPhone.text = buildString {
                    append("Phone: ")
                    append(it.phone)
                }
                tvSchoolOverview.text = buildString {
                    append("Overview:\n")
                    append(it.overview)
                }
            }
        }

        binding.toolbar.setTitle("School Details")

        viewModel.getHighSchoolScores()

        return binding.root
    }
}