package de.todonxt.feature.task_add.presentation

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.core.util.*
import de.todonxt.databinding.FragmentCreateTaskBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class CreateTaskFragment : Fragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.buttonAddDate.setOnClickListener {
            val timePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(viewModel.getDate()?.timeInMillis ?: System.currentTimeMillis())
                .build()

            timePicker.addOnPositiveButtonClickListener {
                viewModel.setDate(
                    Calendar.getInstance().apply {
                        timeInMillis = it
                    }
                )
            }

            timePicker.show(childFragmentManager, "DatePickerDialog")
        }

        binding.buttonAddTime.setOnClickListener {
            val now = Calendar.getInstance()
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(if (DateFormat.is24HourFormat(context)) CLOCK_24H else CLOCK_12H)
                .setHour((viewModel.getTime() ?: now).get(Calendar.HOUR_OF_DAY))
                .setMinute((viewModel.getTime() ?: now).get(Calendar.MINUTE))
                .build()

            timePicker.addOnPositiveButtonClickListener {
                viewModel.setTime(
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, timePicker.hour)
                        set(Calendar.MINUTE, timePicker.minute)
                    }
                )
            }

            timePicker.show(childFragmentManager, "TimePickerDialog")
        }

        binding.chipDate.setOnClickListener {
            viewModel.clearDate()
        }

        binding.chipTime.setOnClickListener {
            viewModel.clearTime()
        }

        binding.buttonAddLabel.setOnClickListener {
            findNavController().navigate(
                CreateTaskFragmentDirections.actionAddTaskToSelectLabels()
            )
        }

        binding.buttonSave.setOnClickListener {
            viewModel.saveTask()
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.formValid.collectLatest {
                    binding.buttonSave.isEnabled = it
                }
            }

            launch {
                viewModel.date.collectLatest { date ->
                    date?.let {
                        binding.chipDate.text = it.formatDate(context)
                    }
                }
            }

            launch {
                viewModel.time.collectLatest { time ->
                    time?.let {
                        binding.chipTime.text = it.formatTime(context)
                    }
                }
            }

            launch {
                viewModel.taskCreated.collectLatest {
                    if (it) {
                        findNavController().navigateUp()
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textFieldTitle.showKeyboard()
    }

    override fun onResume() {
        super.onResume()
        setActionBarTitle(R.string.title_create_task)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createLabelChip(title: String): Chip {
        return (layoutInflater.inflate(
            R.layout.component_label_chip,
            null,
            false
        ) as Chip).apply {
            text = title
        }
    }

}