package de.todonxt.feature.task_details.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.core.ui.components.ChangeOrDeleteDialog
import de.todonxt.core.ui.components.ConfirmationDialog
import de.todonxt.core.ui.components.TextDialog
import de.todonxt.core.util.*
import de.todonxt.databinding.FragmentTaskDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer

@AndroidEntryPoint
class TaskDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskDetailsViewModel by viewModels()
    private val arguments: TaskDetailFragmentArgs by navArgs()

    private val updateDescription = {
        TextDialog.show(
            titleResource = R.string.dialog_title_update_description,
            predefinedText = viewModel.getDescriptionText(),
            onOkClicked = {
                viewModel.updateDescription(it)
            },
            fragmentManager = childFragmentManager
        )
    }

    private val updateDate = {
        datePicker(
            selection = viewModel.getDate(),
            onDateSet = {
                viewModel.updateDate(it)
            }
        )
    }

    private val updateTime = {
        timePicker(
            selection = viewModel.getTime(),
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

        binding.buttonEditDescription.setOnClickListener {
            ChangeOrDeleteDialog.show(
                property = getString(R.string.description),
                onOptionChangeClicked = updateDescription,
                onOptionDeleteClicked = {
                    viewModel.updateDescription(null)
                },
                fragmentManager = childFragmentManager
            )
        }
        binding.buttonAddDescription.setOnClickListener(updateDescription)

        binding.buttonEditDate.setOnClickListener {
            ChangeOrDeleteDialog.show(
                property = getString(R.string.date),
                onOptionChangeClicked = updateDate,
                onOptionDeleteClicked = {
                    viewModel.updateDate(null)
                },
                fragmentManager = childFragmentManager
            )
        }
        binding.buttonAddDate.setOnClickListener(updateDate)

        binding.buttonEditTime.setOnClickListener {
            ChangeOrDeleteDialog.show(
                property = getString(R.string.time),
                onOptionChangeClicked = updateTime,
                onOptionDeleteClicked = {
                    viewModel.updateTime(null)
                },
                fragmentManager = childFragmentManager
            )
        }
        binding.buttonAddTime.setOnClickListener(updateTime)

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

            launch {
                viewModel.taskDeleted.collectLatest {
                    if (it) {
                        findNavController().navigateUp()
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
            R.id.itemChangeTitle -> {
                TextDialog.show(
                    titleResource = R.string.dialog_title_update_title,
                    onOkClicked = { title ->
                        title?.let {
                            viewModel.updateTitle(it)
                        }
                    },
                    predefinedText = viewModel.getTitleText(),
                    fragmentManager = childFragmentManager
                )

                true
            }

            R.id.itemDeleteTask -> {
                ConfirmationDialog.show(
                    titleResource = R.string.dialog_title_delete_task,
                    text = getString(R.string.dialog_text_delete_task, viewModel.getTitleText()),
                    onConfirm = {
                        viewModel.delete()
                    },
                    fragmentManager = childFragmentManager
                )

                true
            }
            else -> false
        }
    }

}