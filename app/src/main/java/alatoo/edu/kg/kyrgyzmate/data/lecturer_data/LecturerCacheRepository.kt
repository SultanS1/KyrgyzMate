package alatoo.edu.kg.kyrgyzmate.data.lecturer_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User
import kotlinx.coroutines.flow.MutableStateFlow

class LecturerCacheRepository : LecturerLocalRepository {

    private val groupsFlow: MutableStateFlow<List<GroupItem>?> = MutableStateFlow(null)

    private val profileFlow: MutableStateFlow<User?> = MutableStateFlow(null)

    override fun setGroups(groups: List<GroupItem>) {
        groupsFlow.value = groups
    }

    override fun getGroups(): List<GroupItem>? {
        return groupsFlow.value
    }

    override fun setProfile(user: User) {
        profileFlow.value = user
    }

    override fun getProfile(): User? {
        return profileFlow.value
    }
}