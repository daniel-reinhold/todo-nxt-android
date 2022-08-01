package de.todonxt.feature.select_label.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.R
import de.todonxt.core.util.launchAndRepeatWithViewLifecycle
import de.todonxt.databinding.FragmentSelectLabelsBinding
import de.todonxt.feature.select_label.domain.LabelListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectLabelFragment : Fragment(), MenuProvider {

    private var _binding: FragmentSelectLabelsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SelectLabelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectLabelsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = LabelListAdapter(
            onLabelSelected = { label ->
                viewModel.setLabelSelected(label)
            },
            onLabelUnSelected = { label ->
                viewModel.setLabelUnSelected(label)
            }
        ).also { binding.recyclerView.adapter = it }

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.labels.collectLatest {
                    adapter.update(it)
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
        menuInflater.inflate(R.menu.menu_select_labels, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.itemAddLabel -> {
                // TODO: Add label
                true
            }
            else -> false
        }
    }

}