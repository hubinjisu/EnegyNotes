package com.bin.presentation

import androidx.annotation.VisibleForTesting
import com.bin.presentation.model.NavigationState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String, popUpInclusive: Boolean = false)

    val navigationState: StateFlow<NavigationState>
}

class AppRouteNavigator @Inject constructor() : RouteNavigator {

    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() = navigate(NavigationState.NavigateUp())

    override fun navigateToRoute(route: String, popUpInclusive: Boolean) =
        navigate(NavigationState.NavigateToRoute(route = route, popUpInclusive = popUpInclusive))

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}
