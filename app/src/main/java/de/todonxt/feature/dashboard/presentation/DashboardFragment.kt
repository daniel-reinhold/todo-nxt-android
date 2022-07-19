package de.todonxt.feature.dashboard.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import de.todonxt.R
import de.todonxt.databinding.FragmentDashboardBinding
import de.todonxt.feature.dashboard.data.ACTION_SHOW_LABELS
import de.todonxt.feature.dashboard.data.ACTION_SHOW_PROJECTS
import de.todonxt.feature.dashboard.data.ACTION_SHOW_TASKS
import de.todonxt.feature.dashboard.domain.DashboardItemAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.recyclerViewOptions.adapter = DashboardItemAdapter.getInstance(
            onItemSelected = { option ->
                when (option) {
                    ACTION_SHOW_TASKS -> {}
                    ACTION_SHOW_LABELS -> {}
                    ACTION_SHOW_PROJECTS -> {}
                }
            }
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}