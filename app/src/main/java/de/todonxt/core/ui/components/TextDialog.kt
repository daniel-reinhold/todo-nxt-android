package de.todonxt.core.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import de.todonxt.R
import de.todonxt.databinding.ComponentTextDialogBinding

/**
 * Dialog which is used to set a text
 */
class TextDialog(
    @StringRes private val titleResource: Int,
    private val onOkClicked: (result: String?) -> Unit,
    private val predefinedText: String? = null
) : DialogFragment() {

    private var _binding: ComponentTextDialogBinding? = null
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
        _binding = ComponentTextDialogBinding.inflate(inflater, container, false)

        binding.textViewTitle.text = getString(titleResource)

        predefinedText?.let {
            binding.textField.setText(it)
        }

        binding.buttonOk.setOnClickListener {
            val text = binding.textField.text.toString()
            onOkClicked.invoke(
                if (text.isNotBlank()) text else null
            )
            dismiss()
        }

        binding.buttonCancel.setOnClickListener {
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
            onOkClicked: (result: String?) -> Unit,
            predefinedText: String?,
            fragmentManager: FragmentManager
        ) {
            TextDialog(
                titleResource = titleResource,
                onOkClicked = onOkClicked,
                predefinedText = predefinedText
            ).show(fragmentManager, "TextDialog")
        }
    }

}