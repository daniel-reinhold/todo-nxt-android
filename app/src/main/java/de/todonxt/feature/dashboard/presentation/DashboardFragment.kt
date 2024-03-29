package de.todonxt.feature.dashboard.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.core.ui.UNICODE_EMOJI_MOON
import de.todonxt.core.ui.UNICODE_EMOJI_SUN
import de.todonxt.core.ui.UNICODE_EMOJI_SUNRISE
import de.todonxt.core.ui.UNICODE_EMOJI_SUNSET
import de.todonxt.core.ui.components.ChangeOrDeleteDialog
import de.todonxt.core.util.asUnicode
import de.todonxt.core.util.dp
import de.todonxt.core.util.launchAndRepeatWithViewLifecycle
import de.todonxt.core.util.setActionBarTitle
import de.todonxt.databinding.FragmentDashboardBinding
import de.todonxt.feature.dashboard.data.ACTION_SHOW_LABELS
import de.todonxt.feature.dashboard.data.ACTION_SHOW_PROJECTS
import de.todonxt.feature.dashboard.data.ACTION_SHOW_TASKS
import de.todonxt.feature.dashboard.domain.DashboardItemAdapter
import de.todonxt.feature.dashboard.domain.TaskAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerViewOptions.adapter = DashboardItemAdapter.getInstance(
            onItemSelected = { option ->
                when (option) {
                    ACTION_SHOW_TASKS -> {
                        findNavController().navigate(
                            DashboardFragmentDirections.actionDashboardToTaskList()
                        )
                    }
                    ACTION_SHOW_LABELS -> {}
                    ACTION_SHOW_PROJECTS -> {}
                }
            }
        )

        val taskAdapter = TaskAdapter(
            onClick = { taskID ->
                findNavController().navigate(
                    DashboardFragmentDirections.actionDashboardToTaskDetails(taskID)
                )
            }
        ).also { binding.recyclerViewTasks.adapter = it }

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

        binding.buttonCreateTask.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionDashboardToAddTask()
            )
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.tasks.collectLatest {
                    taskAdapter.submitList(it)
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setActionBarTitle(R.string.title_dashboard)

        /**
         * 5-9: Morning
         * 10-17: Day
         * 18-21: Evening
         * 21-4: Night
         */
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.textViewGreeting.text = when(currentHour) {
            in 5..9 -> getString(R.string.greeting_morning) + " " + UNICODE_EMOJI_SUNRISE.asUnicode()
            in 10..17 -> getString(R.string.greeting_day) + " " + UNICODE_EMOJI_SUN.asUnicode()
            in 18..21 -> getString(R.string.greeting_evening) + " " + UNICODE_EMOJI_SUNSET.asUnicode()
            in 22..23,
            in 0..4 -> getString(R.string.greeting_night) + " " + UNICODE_EMOJI_MOON.asUnicode()
            else -> getString(R.string.greeting_day) + " " + UNICODE_EMOJI_SUN.asUnicode()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}