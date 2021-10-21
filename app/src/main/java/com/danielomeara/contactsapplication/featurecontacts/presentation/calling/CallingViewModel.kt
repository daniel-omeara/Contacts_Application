package com.danielomeara.contactsapplication.featurecontacts.presentation.calling

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielomeara.contactsapplication.featurecontacts.domain.usecases.FormatPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallingViewModel @Inject constructor(
    private val formatPhoneNumber: FormatPhoneNumber
): ViewModel() {

    private val _state = MutableStateFlow(CallingState())
    val state: StateFlow<CallingState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: CallingEvent) {
        when(event) {
            is CallingEvent.ButtonPressed -> {
                _state.value = state.value.copy(
                    phoneNumber = formatPhoneNumber(state.value.phoneNumber + event.number)
                )
            }
            is CallingEvent.ButtonLongPressed -> {
                _state.value = state.value.copy(
                    phoneNumber = formatPhoneNumber(state.value.phoneNumber + event.number)
                )
            }
            is CallingEvent.BackspacePressed -> {
                _state.value = state.value.copy(
                    phoneNumber = state.value.phoneNumber.dropLast(1)
                )
            }
            is CallingEvent.MakePhoneCall -> {
                viewModelScope.launch {
                    if(state.value.phoneNumber.isBlank()) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                            message = "Cannot call a blank number"
                        ))
                    } else {
                        _eventFlow.emit(UiEvent.MakePhoneCall(state.value.phoneNumber))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        data class MakePhoneCall(val phoneNumber: String): UiEvent()
    }
}