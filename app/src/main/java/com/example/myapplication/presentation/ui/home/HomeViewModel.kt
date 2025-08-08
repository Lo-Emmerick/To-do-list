import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Task
import com.example.myapplication.presentation.ui.home.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    private val task = mutableListOf(
        Task(id = 1, isChecked = false, text = "Comprar pão"),
        Task(id = 2, isChecked = true, text = "Estudar Kotlin"),
        Task(id = 3, isChecked = false, text = "Fazer exercícios")
    )

    fun searchTask() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            delay(1000)

            if (task.isEmpty()) {
                _state.value = HomeState.Empty
            } else {
                _state.value = HomeState.Success(task.toList())
            }
        }
    }

    fun deleteTask(id: Int) {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            delay(1000)

            if (task.isEmpty()) {
                _state.value = HomeState.Empty
            } else {
                _state.value = HomeState.Success(task.toList())
            }
        }
    }

    fun addTask(text: String) {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            delay(1000)

            if (task.isEmpty()) {
                _state.value = HomeState.Empty
            } else {
                _state.value = HomeState.Success(task.toList())
            }
        }
    }
}
