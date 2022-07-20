package de.todonxt.feature.add_task.presentation

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
class CreateTaskFragment : Fragment(), MenuProvider {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateTaskViewModel by viewModels()

    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        (activity as? MenuHost)?.addMenuProvider(
            this,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

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

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.formValid.collectLatest {
                    menu?.findItem(R.id.saveTask)?.isEnabled = it
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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_add_task, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveTask -> {
                binding.textFieldTitle.hideKeyboard()
                true
            }
            else -> false
        }
    }

}