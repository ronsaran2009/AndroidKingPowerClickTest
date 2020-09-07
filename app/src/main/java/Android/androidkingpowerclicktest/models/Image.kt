package Android.androidkingpowerclicktest.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("albumId")  val albumId: Int? = null,
    @SerializedName("id")  val id: Int? = null,
    @SerializedName("title")  val title: String? = null,
    @SerializedName("url")  val url: String? = null,
    @SerializedName("thumbnailUrl")  val thumbnailUrl: String? = null

)