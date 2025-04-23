package alatoo.edu.kg.kyrgyzmate.data.lessons

import alatoo.edu.kg.kyrgyzmate.core.BaseUrls
import alatoo.edu.kg.kyrgyzmate.services.DocsReader
import alatoo.edu.kg.kyrgyzmate.services.DriveApi
import alatoo.edu.kg.kyrgyzmate.services.models.DriveItem

class LessonsRemoteRepository : LessonsRestRepository {

    override suspend fun getLevels(): List<DriveItem> {
        return DriveApi.listChildren(BaseUrls.rootId).filter { it.isFolder }
            .sortedBy {
                Regex("""^\d+""").find(it.name)?.value?.toIntOrNull() ?: Int.MAX_VALUE
            }
    }

    override suspend fun getDocsText(id: String): String {
        return DocsReader.readTextFile(id)
    }

    override suspend fun getItems(id: String): List<DriveItem> {
        return DriveApi.listChildren(id)
    }
}