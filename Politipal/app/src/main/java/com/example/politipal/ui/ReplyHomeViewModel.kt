

package com.example.politipal.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.politipal.data.Email
import com.example.politipal.data.EmailsRepository
import com.example.politipal.data.EmailsRepositoryImpl
import com.example.politipal.ui.utils.PolitipalContentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ReplyHomeViewModel(private val emailsRepository: EmailsRepository = EmailsRepositoryImpl()) :
    ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(homeUIState(loading = true))
    val uiState: StateFlow<homeUIState> = _uiState

    init {
        observeEmails()
    }

    private fun observeEmails() {
        viewModelScope.launch {
            emailsRepository.getAllEmails()
                .catch { ex ->
                    _uiState.value = homeUIState(error = ex.message)
                }
                .collect { emails ->
                    /**
                     * We set first email selected by default for first App launch in large-screens
                     */
                    _uiState.value = homeUIState(
                        emails = emails,
                        openedEmail = emails.first()
                    )
                }
        }
    }

    fun setOpenedEmail(emailId: Long, contentType: PolitipalContentType) {
        /**
         * We only set isDetailOnlyOpen to true when it's only single pane layout
         */
        val email = uiState.value.emails.find { it.id == emailId }
        _uiState.value = _uiState.value.copy(
            openedEmail = email,
            isDetailOnlyOpen = contentType == PolitipalContentType.SINGLE_PANE
        )
    }

    fun toggleSelectedEmail(emailId: Long) {
        val currentSelection = uiState.value.selectedEmails
        _uiState.value = _uiState.value.copy(
            selectedEmails = if (currentSelection.contains(emailId))
                currentSelection.minus(emailId) else currentSelection.plus(emailId)
        )
    }

    fun closeDetailScreen() {
        _uiState.value = _uiState
            .value.copy(
                isDetailOnlyOpen = false,
                openedEmail = _uiState.value.emails.first()
            )
    }
}

data class homeUIState(
    val emails: List<Email> = emptyList(),
    val selectedEmails: Set<Long> = emptySet(),
    val openedEmail: Email? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)

@Preview
@Composable
fun HomeView() {
    ReplyHomeViewModel()
}