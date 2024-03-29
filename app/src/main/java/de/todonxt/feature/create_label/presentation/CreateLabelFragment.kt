package de.todonxt.feature.create_label.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import de.todonxt.databinding.FragmentCreateLabelBinding
import de.todonxt.databinding.FragmentCreateTaskBinding

@AndroidEntryPoint
class CreateLabelFragment : Fragment() {

    private var _binding: FragmentCreateLabelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateLabelBinding.inflate(inflater, container, false)

        binding.buttonOpenColorSelector.setOnClickListener {
            findNavController().navigate(
                CreateLabelFragmentDirections.actionCreateLabelToPickColor()
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}