import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.AddTaskUseCase
import com.example.myapplication.domain.usecase.DeleteTaskUseCase
import com.example.myapplication.domain.usecase.SearchTaskUseCase
import com.example.myapplication.presentation.ui.home.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchTaskUseCase: SearchTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {
    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun searchTask() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = searchTaskUseCase()
                if (response.isNullOrEmpty()) {
                    _state.value = HomeState.Empty
                } else {
                    _state.value = HomeState.Success(response)
                }
            } catch (e: Exception) {
                _state.value = HomeState.Error
            }
        }
    }

    fun deleteTask(id: Int) {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = deleteTaskUseCase(id)
                _state.value = HomeState.Success(listOf(response))
            } catch (e: Exception) {
                _state.value = HomeState.Error
            }
        }
    }

    fun addTask(text: String) {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val response = addTaskUseCase(text)
                _state.value = HomeState.Success(listOf(response))
            } catch (e: Exception) {
                _state.value = HomeState.Error
            }
        }
    }
}
