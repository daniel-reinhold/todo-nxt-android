package de.todonxt.core.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import de.todonxt.R
import de.todonxt.databinding.ComponentConfirmationDialogBinding
import de.todonxt.databinding.ComponentTextDialogBinding

/**
 * Dialog which is used to set a text
 */
class ConfirmationDialog(
    @StringRes private val titleResource: Int,
    private val text: String,
    private val onConfirm: () -> Unit
) : DialogFragment() {

    private var _binding: ComponentConfirmationDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComponentConfirmationDialogBinding.inflate(inflater, container, false)

        binding.textViewTitle.text = getString(titleResource)
        binding.textViewText.text = text

        binding.buttonYes.setOnClickListener {
            onConfirm.invoke()
            dismiss()
        }

        binding.buttonNo.setOnClickListener {
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
            @StringRes titleResource: Int,
            text: String,
            onConfirm: () -> Unit,
            fragmentManager: FragmentManager
        ) {
            ConfirmationDialog(
                titleResource = titleResource,
                text = text,
                onConfirm = onConfirm
            ).show(fragmentManager, "TextDialog")
        }
    }

}