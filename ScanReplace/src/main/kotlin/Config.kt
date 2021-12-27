import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
data class Config(
    @Required val file_key: String = "file_key.json",
    @Required val key_value: String = "key_value.json",
    val output: String = ""
)
