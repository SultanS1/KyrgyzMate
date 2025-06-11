package alatoo.edu.kg.kyrgyzmate.data.lecturer_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem
import alatoo.edu.kg.kyrgyzmate.data.dto.user.User

interface LecturerLocalRepository {

    fun setGroups(groups: List<GroupItem>)

    fun getGroups(): List<GroupItem>?

    fun setProfile(user: User)

    fun getProfile(): User?
}