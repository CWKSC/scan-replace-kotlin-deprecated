import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
data class Config(
    @Required val file_key: String = "file_key.json",
    @Required val key_value: String = "key_value.json",

    val output_prefix: String = "",
    val output_suffix: String = "",

    val file_prefix: String = "",
    val file_suffix: String = "",

    val key_prefix: String = "",
    val key_suffix: String = "",

    val value_prefix: String = "",
    val value_suffix: String = "",
)
