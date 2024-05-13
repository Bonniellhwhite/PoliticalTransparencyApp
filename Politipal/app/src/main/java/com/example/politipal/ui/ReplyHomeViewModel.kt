

package com.example.politipal.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.math.log


// Handles view page changes

// Possibly Helpful Post: https://www.reddit.com/r/Kotlin/comments/l36co7/displayinghiding_elements_in_jetpack_compose_for/
// https://www.youtube.com/watch?v=NhoV78E6yWo&pp=ygUoamV0cGFjayBjb21wb3NlIGJ1dHRvbiBjbGljayBldmVudCBvcGVuIA%3D%3D
// Tutorial: https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#6

class ReplyHomeViewModel(private val emailsRepository: EmailsRepository = EmailsRepositoryImpl()) :
    ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(homeUIState(loading = true))
    val uiState: StateFlow<homeUIState> = _uiState
    private val firebaseDataRetriever = FirebaseDataRetriever()
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _reps = MutableStateFlow(listOf<Rep>())


    // Search query state
    var searchQuery = mutableStateOf("")

    // Filter options state
   // var selectedFilters = mutableStateOf(SetOf<FilterOptions>())


    init {
        observeEmails()
        fetchFirebaseReps()
    }

    fun fetchFirebaseReps(){
        viewModelScope.launch {
            firebaseDataRetriever.fetchFirebaseReps()
                .catch { ex ->
                    _uiState.value = homeUIState(error = ex.message)
                }
                .collect { reps ->
                    Log.d(TAG,reps.size.toString())

                    _uiState.value = homeUIState(
                        reps = reps,
                    )
                }
        }
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

    }

    fun toggleSelectedRep(repId: String) {
        val currentSelection = uiState.value.selectedReps
        _uiState.value = _uiState.value.copy(
            selectedReps = if (currentSelection.contains(repId))
                currentSelection.minus(repId) else currentSelection.plus(repId)
        )
    }

    private fun filterAndSearchReps(reps: List<Rep>): List<Rep> {
        // Apply filters and search
        return reps.filter {
            Log.d(TAG,"Searching..")
            // Match search query against first name or full name
            (it.firstName.contains(searchQuery.value, ignoreCase = true) ||
                    it.fullName.contains(searchQuery.value, ignoreCase = true))
                    //&&
                    // Apply any selected filters
                   // selectedFilters.value.all { filter -> filter.matches(it) }
        }

    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
        // Optionally trigger a re-filtering of the reps
        _uiState.value = _uiState.value.copy(
            reps = filterAndSearchReps(_uiState.value.reps)
        )
        Log.d(TAG,"search done")
    }

    /*
    fun toggleFilter(filter: FilterOptions) {
        if (selectedFilters.value.contains(filter)) {
            selectedFilters.value = selectedFilters.value.minus(filter)
        } else {
            selectedFilters.value = selectedFilters.value.plus(filter)
        }
        // Re-filter the reps list whenever filters change
        _uiState.value = _uiState.value.copy(
            reps = filterAndSearchReps(_uiState.value.reps)
        )
    }*/




}

enum class FilterOptions {
    MALE, FEMALE, DEMOCRAT, REPUBLICAN,OTHER, SENATE, HOUSE;
    fun matches(rep: Rep): Boolean {
        return when (this) {
            MALE -> rep.gender == "M"
            FEMALE -> rep.gender == "F"
            DEMOCRAT -> rep.party == "Democrat"
            REPUBLICAN -> rep.party == "Republican"
            SENATE -> rep.type == "Senate"
            HOUSE -> rep.type == "House"
            OTHER -> TODO()
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