package de.todonxt.feature.task_details.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.core.util.*
import de.todonxt.databinding.FragmentTaskDetailsBinding
import de.todonxt.feature.task_details.data.DateState
import de.todonxt.feature.task_details.data.DescriptionState
import de.todonxt.feature.task_details.data.TimeState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskDetailsViewModel by viewModels()
    private val arguments: TaskDetailFragmentArgs by navArgs()

    private val clickListenerUpdateDescription = View.OnClickListener {
        viewModel.startDescriptionEditMode()
        binding.textFieldDescription.showKeyboard()
    }

    private val clickListenerUpdateDate = View.OnClickListener {
        datePicker(
            selection = viewModel.getDate(),
            onDateSet = {
                viewModel.updateDate(it)
            }
        )
    }

    private val clickListenerUpdateTime = View.OnClickListener {
        timePicker(
            selection = viewModel.getDate(),
            onTimeSet = {
                viewModel.updateTime(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.setTaskID(arguments.taskID)

        (activity as? MenuHost)?.addMenuProvider(
            this,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        binding.buttonEditDescription.setOnClickListener(clickListenerUpdateDescription)
        binding.buttonAddDescription.setOnClickListener(clickListenerUpdateDescription)

        binding.buttonSaveDescription.setOnClickListener {
            viewModel.updateDescription(binding.textFieldDescription.text.toString())
            binding.textFieldDescription.hideKeyboard()
        }

        binding.buttonEditDate.setOnClickListener(clickListenerUpdateDate)
        binding.buttonAddDate.setOnClickListener(clickListenerUpdateDate)

        binding.buttonEditTime.setOnClickListener(clickListenerUpdateTime)
        binding.buttonAddTime.setOnClickListener(clickListenerUpdateTime)

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.descriptionState.collectLatest { state ->
                    when (state) {
                        is DescriptionState.Empty -> {
                            binding.containerDescription.visibility = View.GONE
                            binding.containerEditDescription.visibility = View.GONE
                            binding.containerNoDescription.visibility = View.VISIBLE
                        }
                        is DescriptionState.Set -> {
                            binding.containerDescription.visibility = View.VISIBLE
                            binding.containerEditDescription.visibility = View.GONE
                            binding.containerNoDescription.visibility = View.GONE

                            binding.textViewDescription.text = state.text
                        }
                        is DescriptionState.Edit -> {
                            binding.containerDescription.visibility = View.GONE
                            binding.containerEditDescription.visibility = View.VISIBLE
                            binding.containerNoDescription.visibility = View.GONE
                        }
                        else -> {}
                    }
                }
            }

            launch {
                viewModel.dateState.collectLatest { state ->
                    when (state) {
                        is DateState.Empty -> {
                            binding.containerDate.visibility = View.GONE
                            binding.containerNoDate.visibility = View.VISIBLE
                        }
                        is DateState.Set -> {
                            binding.containerDate.visibility = View.VISIBLE
                            binding.containerNoDate.visibility = View.GONE

                            binding.textViewDate.text = state.date.formatDate(context)
                        }
                        else -> {}
                    }
                }
            }

            launch {
                viewModel.timeState.collectLatest { state ->
                    when (state) {
                        is TimeState.Empty -> {
                            binding.containerTime.visibility = View.GONE
                            binding.containerNoTime.visibility = View.VISIBLE
                        }
                        is TimeState.Set -> {
                            binding.containerTime.visibility = View.VISIBLE
                            binding.containerNoTime.visibility = View.GONE

                            binding.textViewTime.text = state.time.formatTime(context)
                        }
                        else -> {}
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_task_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.itemDeleteTask -> {
                true
            }
            else -> false
        }
    }

}