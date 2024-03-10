package arch

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.LopremImagesItem

data class ImagesUiState(
    val images: List<LopremImagesItem> = emptyList()

)
class LopremImagesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ImagesUiState())
    val uiState = _uiState.asStateFlow()
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    init {
        updateImages()
    }

    fun updateImages() {
        viewModelScope.launch {
            val images = getImages()
            _uiState.update {
                it.copy(images = images)
            }
        }
    }
    private suspend fun getImages(): List<LopremImagesItem> {
        return httpClient
            .get("https://picsum.photos/v2/list")
            .body()
    }

    override fun onCleared() {
        httpClient.close()
    }
}