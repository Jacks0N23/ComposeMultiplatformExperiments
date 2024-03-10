package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LopremImagesItem(
    @SerialName("author")
    val author: String,
    @SerialName("download_url")
    val downloadUrl: String,
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)