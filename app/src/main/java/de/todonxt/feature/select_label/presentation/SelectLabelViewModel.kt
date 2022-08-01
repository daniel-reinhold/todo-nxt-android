package de.todonxt.feature.select_label.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.todonxt.core.data.repository.LabelRepository
import de.todonxt.core.data.source.local.entities.LabelEntity
import de.todonxt.feature.select_label.data.LabelDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLabelViewModel @Inject constructor(
    labelRepository: LabelRepository
) : ViewModel() {

    private val rawLabels = labelRepository.getLabels()
    val labels = MutableStateFlow<List<LabelDto>>(listOf())
    val anyLabels = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            launch {
                rawLabels.collectLatest {
                    labels.value = it.map { label ->
                        LabelDto(
                            label = label,
                            isSelected = false
                        )
                    }
                }
            }

            launch {
                labels.collectLatest {
                    anyLabels.value = it.isNotEmpty()
                }
            }
        }
    }

    fun setLabelSelected(label: LabelEntity) {
        labels.value = labels.value.map {
            if (it.label.id == label.id) {
                it.apply {
                    isSelected = true
                }
            } else {
                it
            }
        }
    }

    fun setLabelUnSelected(label: LabelEntity) {
        labels.value = labels.value.map {
            if (it.label.id == label.id) {
                it.apply {
                    isSelected = false
                }
            } else {
                it
            }
        }
    }

}