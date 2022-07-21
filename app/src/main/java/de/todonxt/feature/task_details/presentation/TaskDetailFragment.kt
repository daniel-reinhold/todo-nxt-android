package de.todonxt.feature.task_details.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.databinding.FragmentTaskDetailsBinding

@AndroidEntryPoint
class TaskDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)

        (activity as? MenuHost)?.addMenuProvider(
            this,
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

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