package com.danielomeara.contactsapplication.featurecontacts.presentation.addeditcontact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.model.InvalidContactException
import com.danielomeara.contactsapplication.featurecontacts.domain.usecases.AddContact
import com.danielomeara.contactsapplication.featurecontacts.domain.usecases.FormatPhoneNumber
import com.danielomeara.contactsapplication.featurecontacts.domain.usecases.GetContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditContactViewModel @Inject constructor(
    private val getContact: GetContact,
    private val formatPhoneNumber: FormatPhoneNumber,
    private val addContact: AddContact,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _contactName = mutableStateOf(ContactTextFieldState(
        hint = "Name"
    ))
    val contactName: State<ContactTextFieldState> = _contactName

    private val _contactPhoneNumber = mutableStateOf(ContactTextFieldState(
        hint = "Phone number"
    ))
    val contactPhoneNumber: State<ContactTextFieldState> = _contactPhoneNumber

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentContactId: Int? = null

    init {
        savedStateHandle.get<Int>("contactId")?.let { contactId ->
            if(contactId != -1) {
                viewModelScope.launch {
                    getContact(contactId)?.also { contact ->
                        currentContactId = contact.id
                        _contactName.value = contactName.value.copy(
                            text = contact.name,
                            isHintVisible = false
                        )
                        _contactPhoneNumber.value = contactPhoneNumber.value.copy(
                            text = formatPhoneNumber(contact.phoneNumber),
                            isHintVisible = false
                        )
                    }
                }
            }
        }
        savedStateHandle.get<String>("contactNumber")?.let { contactNumber ->
            if(contactNumber.isNotBlank()) {
                _contactPhoneNumber.value = contactPhoneNumber.value.copy(text = contactNumber)
            }
        }
    }

    fun onEvent(event: AddEditContactEvent) {
        when(event) {
            is AddEditContactEvent.EnteredName -> {
                _contactName.value = contactName.value.copy(
                    text = event.value
                )
            }
            is AddEditContactEvent.ChangeNameFocus -> {
                _contactName.value = contactName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            contactName.value.text.isBlank()
                )
            }
            is AddEditContactEvent.EnteredPhoneNumber -> {
                _contactPhoneNumber.value = contactPhoneNumber.value.copy(
                    text = formatPhoneNumber(event.value)
                )
            }
            is AddEditContactEvent.ChangePhoneNumberFocus -> {
                _contactPhoneNumber.value = contactPhoneNumber.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            contactPhoneNumber.value.text.isBlank()
                )
            }
            is AddEditContactEvent.SaveContact -> {
                viewModelScope.launch {
                    try {
                        addContact(
                            Contact(
                                name = contactName.value.text,
                                phoneNumber = contactPhoneNumber.value.text,
                                id = currentContactId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveContact)
                    } catch(e: InvalidContactException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save contact."
                            )
                        )
                    }
                }
            }
            is AddEditContactEvent.MakePhoneCall -> {
                viewModelScope.launch {
                    if(contactPhoneNumber.value.text.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(
                            message = "Cannot call a blank number"
                        ))
                    } else {
                        _eventFlow.emit(UiEvent.MakePhoneCall(contactPhoneNumber.value.text))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        data class MakePhoneCall(val phoneNumber: String): UiEvent()
        object SaveContact: UiEvent()
    }
}