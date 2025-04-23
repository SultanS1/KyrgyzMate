package alatoo.edu.kg.kyrgyzmate.data.lessons

import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem

interface LessonsRestRepository {

    suspend fun getLevels(): List<DriveItem>

    suspend fun getDocsText(id: String): String

    suspend fun getItems(id: String): List<DriveItem>
}