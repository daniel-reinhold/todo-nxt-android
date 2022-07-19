package de.todonxt.core.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import de.todonxt.databinding.ComponentLoadingScreenBinding

/**
 * This class Represents a LoadingScreen, used to display a loading state
 *
 * @author Daniel Reinhold
 * @since 1.0
 *
 * @see [de.todonxt.R.layout.component_loading_screen]
 */
class LoadingScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        ComponentLoadingScreenBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

}