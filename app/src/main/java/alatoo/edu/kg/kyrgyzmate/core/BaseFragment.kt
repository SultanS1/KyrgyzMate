package alatoo.edu.kg.kyrgyzmate.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding: ViewBinding>(layoutId: Int) : Fragment(layoutId) {

    abstract val binding: Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    open fun setupUI() {}

    open fun setupObservers() {}
}
