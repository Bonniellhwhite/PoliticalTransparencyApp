

package com.example.politipal.ui

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.politipal.data.Email
import com.example.politipal.data.EmailsRepository
import com.example.politipal.data.EmailsRepositoryImpl
import com.example.politipal.data.Rep
import com.example.politipal.data.firebaseData.FirebaseDataRetriever
import com.example.politipal.ui.utils.PolitipalContentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


// Handles view page changes

// Possibly Helpful Post: https://www.reddit.com/r/Kotlin/comments/l36co7/displayinghiding_elements_in_jetpack_compose_for/
// https://www.youtube.com/watch?v=NhoV78E6yWo&pp=ygUoamV0cGFjayBjb21wb3NlIGJ1dHRvbiBjbGljayBldmVudCBvcGVuIA%3D%3D
class ReplyHomeViewModel(private val emailsRepository: EmailsRepository = EmailsRepositoryImpl()) :
    ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(homeUIState(loading = true))
    val uiState: StateFlow<homeUIState> = _uiState
    private val firebaseDataRetriever = FirebaseDataRetriever()


    init {
        observeEmails()
        updateReps()
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

    fun toggleSelectedRep(repId: String) {
        val currentSelection = uiState.value.selectedReps
        _uiState.value = _uiState.value.copy(
            selectedReps = if (currentSelection.contains(repId))
                currentSelection.minus(repId) else currentSelection.plus(repId)
        )
    }

    private fun updateReps() {
        viewModelScope.launch {
            val reps = firebaseDataRetriever.getReps()
            _uiState.value = _uiState.value.copy(reps = reps, loading = false)
            Log.d(ContentValues.TAG, "Size of fullReplist: ${_uiState.value.reps.size}")
        }
    }


}

data class homeUIState(


    val emails: List<Email> = emptyList(),

    val selectedEmails: Set<Long> = emptySet(),
    val selectedReps: Set<String> = emptySet(),
    val openedEmail: Email? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null,
    val reps: List<Rep> = emptyList()

)


@Preview
@Composable
fun HomeView() {
    ReplyHomeViewModel()
}