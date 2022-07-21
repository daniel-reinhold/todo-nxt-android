package de.todonxt.feature.task_list.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.core.util.dp
import de.todonxt.core.util.launchAndRepeatWithViewLifecycle
import de.todonxt.databinding.FragmentTaskListBinding
import de.todonxt.feature.task_list.domain.TaskListAdapter
import kotlinx.coroutines.flow.collectLatest
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

        val itemDecoration = object : RecyclerView.ItemDecoration() {
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

        val adapter = TaskListAdapter(
            onClick = { taskID ->
                findNavController().navigate(
                    TaskListFragmentDirections.actionTaskListToTaskDetails(taskID)
                )
            }
        ).also { binding.recyclerView.adapter = it }

        binding.recyclerView.addItemDecoration(itemDecoration)

        binding.buttonCreateTask.setOnClickListener {
            findNavController().navigate(
                TaskListFragmentDirections.actionTaskListToAddTask()
            )
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.tasks.collectLatest {
                adapter.submitList(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}