package alatoo.edu.kg.kyrgyzmate.services.models

import alatoo.edu.kg.kyrgyzmate.core.adapter.DelegateAdapterItem

data class DriveItem(
    val id: String,
    val name: String,
    val isFolder: Boolean
) : DelegateAdapterItem {
    override fun id(): Any {
        return id
    }
    override fun content(): Any {
        return name
    }
}
