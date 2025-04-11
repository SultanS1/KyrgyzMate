package alatoo.edu.kg.kyrgyzmate.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding: ViewBinding, STATE: BaseState, ACTION: BaseAction>(layoutId: Int) : Fragment(layoutId) {

    abstract val binding: Binding

    abstract val viewModel: BaseViewModel<STATE, ACTION>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    abstract fun setupUI()

    abstract fun renderState(state: STATE)
}
