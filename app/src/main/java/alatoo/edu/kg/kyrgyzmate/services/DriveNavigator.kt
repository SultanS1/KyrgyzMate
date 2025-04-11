package alatoo.edu.kg.kyrgyzmate.services

object DriveNavigator {
    fun getLevelFolders(rootId: String): List<DriveItem> {
        return DriveApi.listChildren(rootId).filter { it.isFolder }
    }

    fun getLevelDescriptionText(levelFolderId: String): DriveItem? {
        val descriptionFolder = DriveApi.findFolderByName(levelFolderId, "Description") ?: return null
        return DriveApi.listChildren(descriptionFolder.id).firstOrNull { !it.isFolder }
    }

    fun getThemeFolders(levelFolderId: String): List<DriveItem> {
        val contentFolder = DriveApi.findFolderByName(levelFolderId, "Main Content") ?: return emptyList()
        return DriveApi.listChildren(contentFolder.id).filter { it.isFolder }
    }

    fun getTopicFolders(themeFolderId: String): List<DriveItem> {
        return DriveApi.listChildren(themeFolderId).filter { it.isFolder }
    }

    fun getFilesInTopic(topicFolderId: String): List<DriveItem> {
        return DriveApi.listChildren(topicFolderId).filter { !it.isFolder }
    }

    fun resolveFolderPath(rootId: String, path: List<String>): DriveItem? {
        var currentId = rootId
        var currentFolder: DriveItem? = null

        for (folderName in path) {
            currentFolder = DriveApi.findFolderByName(currentId, folderName)
            if (currentFolder == null) return null
            currentId = currentFolder.id
        }
        return currentFolder
    }
}