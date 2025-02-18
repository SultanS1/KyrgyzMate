package alatoo.edu.kg.kyrgyzmate.extensions

import alatoo.edu.kg.kyrgyzmate.R
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun NavController.navigateTo(destinationId: Int, args: Bundle? = null) {
    this.navigate(
        destinationId,
        args,
        NavOptions.Builder()
            .setEnterAnim(R.anim.enter_animation)
            .setExitAnim(R.anim.exit_animation)
            .setPopEnterAnim(R.anim.pop_enter_animation)
            .setPopExitAnim(R.anim.pop_exit_animation)
            .build()
    )
}

fun NavController.navigateToWithSafeArgs(direction: NavDirections) {
    this.navigate(
        direction,
        NavOptions.Builder()
            .setEnterAnim(R.anim.enter_animation)
            .setExitAnim(R.anim.exit_animation)
            .setPopEnterAnim(R.anim.pop_enter_animation)
            .setPopExitAnim(R.anim.pop_exit_animation)
            .build()
    )
}

fun NavController.navigateAndClearBackStack(destinationId: Int, args: Bundle? = null) {
    this.navigate(
        destinationId,
        args,
        NavOptions.Builder()
            .setPopUpTo(graph.startDestinationId, inclusive = true)
            .setEnterAnim(R.anim.enter_animation)
            .setExitAnim(R.anim.exit_animation)
            .setPopEnterAnim(R.anim.pop_enter_animation)
            .setPopExitAnim(R.anim.pop_exit_animation)
            .build()
    )
}

fun NavController.navigateWithPopUp(destinationId: Int, popUpToId: Int, inclusive: Boolean = false, args: Bundle? = null) {
    this.navigate(
        destinationId,
        args,
        NavOptions.Builder()
            .setPopUpTo(popUpToId, inclusive)
            .setEnterAnim(R.anim.enter_animation)
            .setExitAnim(R.anim.exit_animation)
            .setPopEnterAnim(R.anim.pop_enter_animation)
            .setPopExitAnim(R.anim.pop_exit_animation)
            .build()
    )
}

