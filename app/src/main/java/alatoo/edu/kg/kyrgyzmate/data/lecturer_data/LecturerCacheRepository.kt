package alatoo.edu.kg.kyrgyzmate.data.lecturer_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import kotlinx.coroutines.flow.MutableStateFlow

class LecturerCacheRepository : LecturerLocalRepository {

    private val groupsFlow: MutableStateFlow<List<GroupItem>?> = MutableStateFlow(null)

    override fun setGroups(groups: List<GroupItem>) {
        groupsFlow.value = groups
    }

    override fun getGroups(): List<GroupItem>? {
        return groupsFlow.value
    }
}