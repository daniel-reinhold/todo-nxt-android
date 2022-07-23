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
import de.todonxt.core.ui.components.TextDialog
import de.todonxt.core.util.*
import de.todonxt.databinding.FragmentTaskDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskDetailsViewModel by viewModels()
    private val arguments: TaskDetailFragmentArgs by navArgs()

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

    private val clickListenerUpdateDescription = View.OnClickListener {
        TextDialog.show(
            titleResource = R.string.update_description,
            predefinedText = viewModel.getDescriptionText(),
            onOkClicked = {
                viewModel.updateDescription(it)
            },
            fragmentManager = childFragmentManager
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

        binding.buttonEditDate.setOnClickListener(clickListenerUpdateDate)
        binding.buttonAddDate.setOnClickListener(clickListenerUpdateDate)

        binding.buttonEditTime.setOnClickListener(clickListenerUpdateTime)
        binding.buttonAddTime.setOnClickListener(clickListenerUpdateTime)

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.description.collectLatest { description ->
                    if (description != null) {
                        binding.containerDescription.visibility = View.VISIBLE
                        binding.containerNoDescription.visibility = View.GONE
                    } else {
                        binding.containerDescription.visibility = View.GONE
                        binding.containerNoDescription.visibility = View.VISIBLE
                    }
                }
            }

            launch {
                viewModel.date.collectLatest { date ->
                    if (date != null) {
                        binding.containerDate.visibility = View.VISIBLE
                        binding.containerNoDate.visibility = View.GONE
                        binding.textViewDate.text = date.formatDate(context)
                    } else {
                        binding.containerDate.visibility = View.GONE
                        binding.containerNoDate.visibility = View.VISIBLE
                    }
                }
            }

            launch {
                viewModel.time.collectLatest { time ->
                    if (time != null) {
                        binding.containerTime.visibility = View.VISIBLE
                        binding.containerNoTime.visibility = View.GONE
                        binding.textViewTime.text = time.formatTime(context)
                    } else {
                        binding.containerTime.visibility = View.GONE
                        binding.containerNoTime.visibility = View.VISIBLE
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