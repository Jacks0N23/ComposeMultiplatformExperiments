import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import arch.ImagesUiState
import arch.LopremImagesViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.LopremImagesItem

@Composable
fun App() {
    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        val greeting = remember { Greeting().greet() }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                if (showContent) {
//                    Text("Hello, motherfucker")
//                } else {
//                    Text("Click me, BITCH!")
//                }
//            }
//            AnimatedVisibility(showContent) {
//                Column(
//                    Modifier.fillMaxWidth().then(Modifier.wrapContentHeight()),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text("Compose: $greeting")
//                    KamelImage(
//                        asyncPainterResource("https://fastly.picsum.photos/id/17/2500/1667.jpg?hmac=HD-JrnNUZjFiP2UZQvWcKrgLoC_pc_ouUSWv8kHsJJY"),
//                        null
//                    )
//                }
//            }
//        }

        val viewModel = getViewModel(Unit, viewModelFactory { LopremImagesViewModel() })
        LopremImagesPage(viewModel)
    }
}


@Composable
fun LopremImagesPage(viewModel: LopremImagesViewModel) {

    val uiState: ImagesUiState by viewModel.uiState.collectAsState()

    AnimatedVisibility(uiState.images.isNotEmpty()) {
        LazyColumn (

//            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxSize().padding(5.dp),
            content = {
                items(uiState.images) {
                    LopremImageCell(it)
                }
            }
        )
    }
}

@Composable
fun LopremImageCell(item: LopremImagesItem) {
    KamelImage(
        asyncPainterResource(item.downloadUrl),
        contentDescription = "${item.id} by ${item.author}",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)
    )
}
