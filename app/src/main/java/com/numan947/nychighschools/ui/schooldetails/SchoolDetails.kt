package com.numan947.nychighschools.ui.schooldetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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
    private var highSchoolListItem: HighSchoolListItem? = null

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
            highSchoolListItem = args.schoolData
            viewModel.setSchool(highSchoolListItem!!)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.apply {
                tvSchoolName.text = it.name
                tvSchoolAddress.text = buildString {
                    append("Address: ")
                    append(it.address?:"Not Available")
                }
                tvSchoolEmail.text = buildString {
                    append("Email: ")
                    append(it.email?:"Not Available")
                }
                tvSchoolWebsite.text = buildString {
                    append("Website: ")
                    append(it.website?:"Not Available")
                }
                tvSchoolPhone.text = buildString {
                    append("Phone: ")
                    append(it.phone?:"Not Available")
                }
                tvSchoolOverview.text = buildString {
                    append("Overview:\n")
                    append(it.overview?:"Not Available")
                }
                tvOppurtunities.text = buildString {
                    append("Opportunities:\n")
                    if (it.opportunities1!=null) {
                        append(it.opportunities1)
                    }
                    if (it.opportunities2!=null) {
                        append("\n")
                        append(it.opportunities2)
                    }
                    if (it.opportunities1==null && it.opportunities2==null) {
                        append("Not Available")
                    }
                }
                tvTotalStudents.text = buildString {
                    append("Total Students: ")
                    append(it.totalStudents?:"Not Available")
                }
                tvGraduationRate.text = buildString {
                    append("Graduation Rate: ")
                    if (it.graduationRate == null) {
                        append("Not Available")
                    } else
                        append(String.format("%.2f%%", it.graduationRate?.times(100)))
                }
                attendanceRate.text = buildString {
                    append("Attendance Rate: ")
                    if (it.attendanceRate == null) {
                        append("Not Available")
                    } else
                        append(String.format("%.2f%%", it.attendanceRate?.times(100)))

                }
                collegeCareerRate.text = buildString {
                    append("College Career Rate: ")
                    if (it.collegeCareerRate == null) {
                        append("Not Available")
                    } else
                        append(String.format("%.2f%%", it.collegeCareerRate?.times(100)))
                }
                tvSATTestTakers.text = buildString {
                    append("Total SAT Test Takers: ")
                    if (it.satTestTakers == -1) {
                        append("Not Available")
                    } else
                    append(it.satTestTakers?:"Not Available")
                }
                tvReadingAvg.text = buildString {
                    append("Reading Avg: ")
                    if (it.satCriticalReadingAvgScore == -1.0) {
                        append("Not Available")
                    } else
                    append(it.satCriticalReadingAvgScore?:"Not Available")
                }
                tvMathAvg.text = buildString {
                    append("Math Avg: ")
                    if (it.satMathAvgScore == -1.0) {
                        append("Not Available")
                    } else
                    append(it.satMathAvgScore?:"Not Available")
                }
                tvWritingAvg.text = buildString {
                    append("Writing Avg: ")
                    if (it.satWritingAvgScore == -1.0) {
                        append("Not Available")
                    } else
                    append(it.satWritingAvgScore?:"Not Available")
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.detailsScroll.visibility = View.INVISIBLE
                binding.saveOrDeleteSchool.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.detailsScroll.visibility = View.VISIBLE
                binding.saveOrDeleteSchool.visibility = View.VISIBLE
            }
        }

        viewModel.saveStateData.observe(viewLifecycleOwner) { isSaved->
            if (isSaved) {
                binding.saveOrDeleteSchool.setImageResource(R.drawable.baseline_bookmark_remove_24)
                binding.saveOrDeleteSchool.setColorFilter(ContextCompat.getColor(requireContext(), R.color.toolbarButtonTint))
            } else {
                binding.saveOrDeleteSchool.setImageResource(R.drawable.baseline_download_for_offline_24)
                binding.saveOrDeleteSchool.setColorFilter(ContextCompat.getColor(requireContext(), R.color.toolbarButtonTint))
            }
        }

        binding.toolbar.setTitle("School Details")
        binding.saveOrDeleteSchool.setOnClickListener{
            viewModel.saveOrDeleteSchool()
        }

        viewModel.getHighSchoolScores()

        return binding.root
    }
}