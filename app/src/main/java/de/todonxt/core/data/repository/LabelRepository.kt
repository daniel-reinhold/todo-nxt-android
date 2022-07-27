package de.todonxt.core.data.repository

import de.todonxt.core.data.source.local.dao.LabelDao
import de.todonxt.core.data.source.local.entities.LabelEntity
import javax.inject.Inject

class LabelRepository @Inject constructor(
    private val labelDao: LabelDao
) {

    fun getLabels() = labelDao.getLabels()

    suspend fun createLabel(label: LabelEntity) = labelDao.createOrUpdateLabel(label)

    suspend fun updateLabelTitle(label: LabelEntity, title: String) = label.apply {
        this.title = title
    }.let {
        labelDao.createOrUpdateLabel(it)
    }

    suspend fun updateLabelColor(label: LabelEntity, color: Int) = label.apply {
        this.color = color
    }.let {
        labelDao.createOrUpdateLabel(it)
    }

    suspend fun deleteLabel(label: LabelEntity) = labelDao.deleteLabel(label)

}