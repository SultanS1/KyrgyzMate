package alatoo.edu.kg.kyrgyzmate.data.lecturer_data

import alatoo.edu.kg.kyrgyzmate.data.dto.groups.GroupItem

interface LecturerLocalRepository {

    fun setGroups(groups: List<GroupItem>)

    fun getGroups(): List<GroupItem>?
}