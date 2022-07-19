package de.todonxt.feature.dashboard.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import de.todonxt.R
import de.todonxt.core.ui.UNICODE_EMOJI_MOON
import de.todonxt.core.ui.UNICODE_EMOJI_SUN
import de.todonxt.core.ui.UNICODE_EMOJI_SUNRISE
import de.todonxt.core.ui.UNICODE_EMOJI_SUNSET
import de.todonxt.core.util.asUnicode
import de.todonxt.databinding.FragmentDashboardBinding
import de.todonxt.feature.dashboard.data.ACTION_SHOW_LABELS
import de.todonxt.feature.dashboard.data.ACTION_SHOW_PROJECTS
import de.todonxt.feature.dashboard.data.ACTION_SHOW_TASKS
import de.todonxt.feature.dashboard.domain.DashboardItemAdapter
import java.util.*

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

    override fun onResume() {
        super.onResume()

        /**
         * 5-9: Morning
         * 10-17: Day
         * 18-21: Evening
         * 21-4: Night
         */
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.textViewGreeting.text = when(currentHour) {
            in 5..9 -> getString(R.string.greeting_morning) + UNICODE_EMOJI_SUNRISE.asUnicode()
            in 10..17 -> getString(R.string.greeting_day) + UNICODE_EMOJI_SUN.asUnicode()
            in 18..21 -> getString(R.string.greeting_evening) + UNICODE_EMOJI_SUNSET.asUnicode()
            in 22..23,
            in 0..4 -> getString(R.string.greeting_night) + UNICODE_EMOJI_MOON.asUnicode()
            else -> getString(R.string.greeting_day) + UNICODE_EMOJI_SUN.asUnicode()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}