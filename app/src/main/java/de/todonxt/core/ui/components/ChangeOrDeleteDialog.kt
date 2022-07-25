package de.todonxt.core.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import de.todonxt.R
import de.todonxt.databinding.DialogChangeOrDeleteBinding

class ChangeOrDeleteDialog(
    private val property: String,
    private val onOptionChangeClicked: () -> Unit,
    private val onOptionDeleteClicked: () -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: DialogChangeOrDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogChangeOrDeleteBinding.inflate(inflater, container, false)

        binding.textViewEdit.text = getString(R.string.dialog_change_or_delete_title_change, property)
        binding.textViewDelete.text = getString(R.string.dialog_change_or_delete_title_delete, property)

        binding.containerEdit.setOnClickListener {
            onOptionChangeClicked.invoke()
            dismiss()
        }

        binding.containerDelete.setOnClickListener {
            onOptionDeleteClicked.invoke()
            dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun show(
            property: String,
            onOptionChangeClicked: () -> Unit,
            onOptionDeleteClicked: () -> Unit,
            fragmentManager: FragmentManager,
        ) {
            ChangeOrDeleteDialog(
                property = property,
                onOptionChangeClicked = onOptionChangeClicked,
                onOptionDeleteClicked = onOptionDeleteClicked
            ).show(fragmentManager, "ChangeOrDeleteDialog")
        }
    }

}