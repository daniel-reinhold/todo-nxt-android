package de.todonxt.feature.task_list.presentation

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.core.ui.components.ChangeOrDeleteDialog
import de.todonxt.core.util.*
import de.todonxt.databinding.FragmentTaskListBinding
import de.todonxt.feature.task_list.domain.TaskDoneListAdapter
import de.todonxt.feature.task_list.domain.TaskListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val taskListAdapter = TaskListAdapter(
            onClick = { taskID ->
                findNavController().navigate(
                    TaskListFragmentDirections.actionTaskListToTaskDetails(taskID)
                )
            },
            onDateClick = { task ->
                ChangeOrDeleteDialog.show(
                    property = getString(R.string.date),
                    onOptionChangeClicked = {
                        datePicker(
                            selection = task.date,
                            onDateSet = {
                                viewModel.updateDate(
                                    task = task,
                                    date = it
                                )
                            }
                        )
                    },
                    onOptionDeleteClicked = {
                        viewModel.updateDate(
                            task = task,
                            date = null
                        )
                    },
                    fragmentManager = childFragmentManager
                )
            },
            onTimeClick = { task ->
                ChangeOrDeleteDialog.show(
                    property = getString(R.string.time),
                    onOptionChangeClicked = {
                        timePicker(
                            selection = task.time,
                            onTimeSet = {
                                viewModel.updateTime(
                                    task = task,
                                    time = it
                                )
                            }
                        )
                    },
                    onOptionDeleteClicked = {
                        viewModel.updateTime(
                            task = task,
                            time = null
                        )
                    },
                    fragmentManager = childFragmentManager
                )
            }
        ).also { binding.recyclerViewTasks.adapter = it }

        val taskDoneListAdapter = TaskDoneListAdapter().also {
            binding.recyclerViewTasksDone.adapter = it
        }

        binding.buttonCreateTask.setOnClickListener {
            findNavController().navigate(
                TaskListFragmentDirections.actionTaskListToAddTask()
            )
        }

        binding.headerDoneTasks.setOnClickListener {
            viewModel.toggleDoneTasksVisibility()
        }

        context?.let {
            binding.recyclerViewTasks.addItemDecoration(
                object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        val position = parent.getChildAdapterPosition(view)
                        val itemCount = state.itemCount

                        if (position != itemCount - 1) {
                            outRect.bottom = 8.dp().toInt()
                        }
                    }
                }
            )

            binding.recyclerViewTasksDone.addItemDecoration(
                MaterialDividerItemDecoration(it, VERTICAL).apply {
                    isLastItemDecorated = false
                }
            )
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.openTasks.collectLatest {
                    taskListAdapter.update(it)
                }
            }

            launch {
                viewModel.doneTasks.collectLatest {
                    taskDoneListAdapter.update(it)
                }
            }

            launch {
                viewModel.containerDoneTasksVisible.collectLatest {
                    binding.iconHeaderDoneTasks.setImageResource(
                        if (it) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down
                    )
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}