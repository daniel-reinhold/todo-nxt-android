package de.todonxt.feature.pick_color.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.todonxt.databinding.FragmentPickColorBinding
import de.todonxt.feature.pick_color.domain.MaterialPaletteAdapter

class PickColorFragment : Fragment() {

    private var _binding: FragmentPickColorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickColorBinding.inflate(inflater, container, false)
        binding.root.adapter = MaterialPaletteAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}