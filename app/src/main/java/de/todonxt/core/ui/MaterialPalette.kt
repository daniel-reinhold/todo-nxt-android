package de.todonxt.core.ui

import android.graphics.Color
import androidx.annotation.StringRes
import de.todonxt.R

private val RED = listOf(
    Color.parseColor("#FFEBEE"),
    Color.parseColor("#FFCDD2"),
    Color.parseColor("#EF9A9A"),
    Color.parseColor("#E57373"),
    Color.parseColor("#EF5350"),
    Color.parseColor("#F44336"),
    Color.parseColor("#E53935"),
    Color.parseColor("#D32F2F"),
    Color.parseColor("#C62828"),
    Color.parseColor("#B71C1C")
)

private val PINK = listOf(
    Color.parseColor("#FCE4EC"),
    Color.parseColor("#F8BBD0"),
    Color.parseColor("#F48FB1"),
    Color.parseColor("#F06292"),
    Color.parseColor("#EC407A"),
    Color.parseColor("#E91E63"),
    Color.parseColor("#D81B60"),
    Color.parseColor("#C2185B"),
    Color.parseColor("#AD1457"),
    Color.parseColor("#880E4F")
)

private val PURPLE = listOf(
    Color.parseColor("#F3E5F5"),
    Color.parseColor("#E1BEE7"),
    Color.parseColor("#CE93D8"),
    Color.parseColor("#BA68C8"),
    Color.parseColor("#AB47BC"),
    Color.parseColor("#9C27B0"),
    Color.parseColor("#8E24AA"),
    Color.parseColor("#7B1FA2"),
    Color.parseColor("#6A1B9A"),
    Color.parseColor("#4A148C")
)

private val DEEP_PURPLE = listOf(
    Color.parseColor("#EDE7F6"),
    Color.parseColor("#D1C4E9"),
    Color.parseColor("#B39DDB"),
    Color.parseColor("#9575CD"),
    Color.parseColor("#7E57C2"),
    Color.parseColor("#673AB7"),
    Color.parseColor("#5E35B1"),
    Color.parseColor("#512DA8"),
    Color.parseColor("#4527A0"),
    Color.parseColor("#311B92")
)

private val INDIGO = listOf(
    Color.parseColor("#E8EAF6"),
    Color.parseColor("#C5CAE9"),
    Color.parseColor("#9FA8DA"),
    Color.parseColor("#7986CB"),
    Color.parseColor("#5C6BC0"),
    Color.parseColor("#3F51B5"),
    Color.parseColor("#3949AB"),
    Color.parseColor("#303F9F"),
    Color.parseColor("#283593"),
    Color.parseColor("#1A237E")
)

private val BLUE = listOf(
    Color.parseColor("#E3F2FD"),
    Color.parseColor("#BBDEFB"),
    Color.parseColor("#90CAF9"),
    Color.parseColor("#64B5F6"),
    Color.parseColor("#42A5F5"),
    Color.parseColor("#2196F3"),
    Color.parseColor("#1E88E5"),
    Color.parseColor("#1976D2"),
    Color.parseColor("#1565C0"),
    Color.parseColor("#0D47A1")
)

private val LIGHT_BLUE = listOf(
    Color.parseColor("#E1F5FE"),
    Color.parseColor("#B3E5FC"),
    Color.parseColor("#81D4FA"),
    Color.parseColor("#4FC3F7"),
    Color.parseColor("#29B6F6"),
    Color.parseColor("#03A9F4"),
    Color.parseColor("#039BE5"),
    Color.parseColor("#0288D1"),
    Color.parseColor("#0277BD"),
    Color.parseColor("#01579B")
)

private val CYAN = listOf(
    Color.parseColor("#E0F7FA"),
    Color.parseColor("#B2EBF2"),
    Color.parseColor("#80DEEA"),
    Color.parseColor("#4DD0E1"),
    Color.parseColor("#26C6DA"),
    Color.parseColor("#00BCD4"),
    Color.parseColor("#00ACC1"),
    Color.parseColor("#0097A7"),
    Color.parseColor("#00838F"),
    Color.parseColor("#006064")
)

private val TEAL = listOf(
    Color.parseColor("#E0F2F1"),
    Color.parseColor("#B2DFDB"),
    Color.parseColor("#80CBC4"),
    Color.parseColor("#4DB6AC"),
    Color.parseColor("#26A69A"),
    Color.parseColor("#009688"),
    Color.parseColor("#00897B"),
    Color.parseColor("#00796B"),
    Color.parseColor("#00695C"),
    Color.parseColor("#004D40")
)

private val GREEN = listOf(
    Color.parseColor("#E8F5E9"),
    Color.parseColor("#C8E6C9"),
    Color.parseColor("#A5D6A7"),
    Color.parseColor("#81C784"),
    Color.parseColor("#66BB6A"),
    Color.parseColor("#4CAF50"),
    Color.parseColor("#43A047"),
    Color.parseColor("#388E3C"),
    Color.parseColor("#2E7D32"),
    Color.parseColor("#1B5E20")
)

private val LIGHT_GREEN = listOf(
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#"),
    Color.parseColor("#")
)

private val LIME = listOf(
    Color.parseColor("#F9FBE7"),
    Color.parseColor("#F0F4C3"),
    Color.parseColor("#E6EE9C"),
    Color.parseColor("#DCE775"),
    Color.parseColor("#D4E157"),
    Color.parseColor("#CDDC39"),
    Color.parseColor("#C0CA33"),
    Color.parseColor("#AFB42B"),
    Color.parseColor("#9E9D24"),
    Color.parseColor("#827717")
)

private val YELLOW = listOf(
    Color.parseColor("#FFFDE7"),
    Color.parseColor("#FFF9C4"),
    Color.parseColor("#FFF59D"),
    Color.parseColor("#FFF176"),
    Color.parseColor("#FFEE58"),
    Color.parseColor("#FFEB3B"),
    Color.parseColor("#FDD835"),
    Color.parseColor("#FBC02D"),
    Color.parseColor("#F9A825"),
    Color.parseColor("#F57F17")
)

private val AMBER = listOf(
    Color.parseColor("#FFF8E1"),
    Color.parseColor("#FFECB3"),
    Color.parseColor("#FFE082"),
    Color.parseColor("#FFD54F"),
    Color.parseColor("#FFCA28"),
    Color.parseColor("#FFC107"),
    Color.parseColor("#FFB300"),
    Color.parseColor("#FFA000"),
    Color.parseColor("#FF8F00"),
    Color.parseColor("#FF6F00")
)

private val ORANGE = listOf(
    Color.parseColor("#FFF3E0"),
    Color.parseColor("#FFE0B2"),
    Color.parseColor("#FFCC80"),
    Color.parseColor("#FFB74D"),
    Color.parseColor("#FFA726"),
    Color.parseColor("#FF9800"),
    Color.parseColor("#FB8C00"),
    Color.parseColor("#F57C00"),
    Color.parseColor("#EF6C00"),
    Color.parseColor("#E65100")
)

private val DEEP_ORANGE = listOf(
    Color.parseColor("#FBE9E7"),
    Color.parseColor("#FFCCBC"),
    Color.parseColor("#FFAB91"),
    Color.parseColor("#FF8A65"),
    Color.parseColor("#FF7043"),
    Color.parseColor("#FF5722"),
    Color.parseColor("#F4511E"),
    Color.parseColor("#E64A19"),
    Color.parseColor("#D84315"),
    Color.parseColor("#BF360C")
)

private val BROWN = listOf(
    Color.parseColor("#EFEBE9"),
    Color.parseColor("#D7CCC8"),
    Color.parseColor("#BCAAA4"),
    Color.parseColor("#A1887F"),
    Color.parseColor("#8D6E63"),
    Color.parseColor("#795548"),
    Color.parseColor("#6D4C41"),
    Color.parseColor("#5D4037"),
    Color.parseColor("#4E342E"),
    Color.parseColor("#3E2723")
)

private val GRAY = listOf(
    Color.parseColor("#FAFAFA"),
    Color.parseColor("#F5F5F5"),
    Color.parseColor("#EEEEEE"),
    Color.parseColor("#E0E0E0"),
    Color.parseColor("#BDBDBD"),
    Color.parseColor("#9E9E9E"),
    Color.parseColor("#757575"),
    Color.parseColor("#616161"),
    Color.parseColor("#424242"),
    Color.parseColor("#212121")
)

private val BLUE_GRAY = listOf(
    Color.parseColor("#ECEFF1"),
    Color.parseColor("#CFD8DC"),
    Color.parseColor("#B0BEC5"),
    Color.parseColor("#90A4AE"),
    Color.parseColor("#78909C"),
    Color.parseColor("#607D8B"),
    Color.parseColor("#546E7A"),
    Color.parseColor("#455A64"),
    Color.parseColor("#37474F"),
    Color.parseColor("#263238")
)

enum class MaterialColor {
    RED,
    PINK,
    PURPLE,
    DEEP_PURPLE,
    INDIGO,
    BLUE,
    LIGHT_BLUE,
    CYAN,
    TEAL,
    GREEN,
    LIGHT_GREEN,
    LIME,
    YELLOW,
    AMBER,
    ORANGE,
    DEEP_ORANGE,
    BROWN,
    GRAY,
    BLUE_GRAY;
}

class MaterialPalette private constructor(color: MaterialColor) {
    private val palette: List<Int> = when (color) {
        MaterialColor.RED -> RED
        MaterialColor.PINK -> PINK
        MaterialColor.PURPLE -> PURPLE
        MaterialColor.DEEP_PURPLE -> DEEP_PURPLE
        MaterialColor.INDIGO -> INDIGO
        MaterialColor.BLUE -> BLUE
        MaterialColor.LIGHT_BLUE -> LIGHT_BLUE
        MaterialColor.CYAN -> CYAN
        MaterialColor.TEAL -> TEAL
        MaterialColor.GREEN -> GREEN
        MaterialColor.LIGHT_GREEN -> LIGHT_GREEN
        MaterialColor.LIME -> LIME
        MaterialColor.YELLOW -> YELLOW
        MaterialColor.AMBER -> AMBER
        MaterialColor.ORANGE -> ORANGE
        MaterialColor.DEEP_ORANGE -> DEEP_ORANGE
        MaterialColor.BROWN -> BROWN
        MaterialColor.GRAY -> GRAY
        MaterialColor.BLUE_GRAY -> BLUE_GRAY
    }

    @StringRes
    val stringResource: Int = when (color) {
        MaterialColor.RED -> R.string.color_title_red
        MaterialColor.PINK -> R.string.color_title_pink
        MaterialColor.PURPLE -> R.string.color_title_purple
        MaterialColor.DEEP_PURPLE -> R.string.color_title_deep_purple
        MaterialColor.INDIGO -> R.string.color_title_indigo
        MaterialColor.BLUE -> R.string.color_title_blue
        MaterialColor.LIGHT_BLUE -> R.string.color_title_light_blue
        MaterialColor.CYAN -> R.string.color_title_cyan
        MaterialColor.TEAL -> R.string.color_title_teal
        MaterialColor.GREEN -> R.string.color_title_green
        MaterialColor.LIGHT_GREEN -> R.string.color_title_light_green
        MaterialColor.LIME -> R.string.color_title_lime
        MaterialColor.YELLOW -> R.string.color_title_yellow
        MaterialColor.AMBER -> R.string.color_title_amber
        MaterialColor.ORANGE -> R.string.color_title_orange
        MaterialColor.DEEP_ORANGE -> R.string.color_title_deep_orange
        MaterialColor.BROWN -> R.string.color_title_brown
        MaterialColor.GRAY -> R.string.color_title_gray
        MaterialColor.BLUE_GRAY -> R.string.color_title_blue_gray
    }

    fun shade50(): Int = palette[0]
    fun shade100(): Int = palette[1]
    fun shade200(): Int = palette[2]
    fun shade300(): Int = palette[3]
    fun shade400(): Int = palette[4]
    fun shade500(): Int = palette[5]
    fun shade600(): Int = palette[6]
    fun shade700(): Int = palette[7]
    fun shade800(): Int = palette[8]
    fun shade900(): Int = palette[9]

    companion object {
        fun getInstance(color: MaterialColor): MaterialPalette {
            return MaterialPalette(color)
        }

        fun all(): List<MaterialPalette> = listOf(
            MaterialPalette(MaterialColor.RED),
            MaterialPalette(MaterialColor.PINK),
            MaterialPalette(MaterialColor.PURPLE),
            MaterialPalette(MaterialColor.DEEP_PURPLE),
            MaterialPalette(MaterialColor.INDIGO),
            MaterialPalette(MaterialColor.BLUE),
            MaterialPalette(MaterialColor.LIGHT_BLUE),
            MaterialPalette(MaterialColor.CYAN),
            MaterialPalette(MaterialColor.TEAL),
            MaterialPalette(MaterialColor.GREEN),
            MaterialPalette(MaterialColor.LIGHT_GREEN),
            MaterialPalette(MaterialColor.LIME),
            MaterialPalette(MaterialColor.YELLOW),
            MaterialPalette(MaterialColor.AMBER),
            MaterialPalette(MaterialColor.ORANGE),
            MaterialPalette(MaterialColor.DEEP_ORANGE),
            MaterialPalette(MaterialColor.BROWN),
            MaterialPalette(MaterialColor.GRAY),
            MaterialPalette(MaterialColor.BLUE_GRAY)
        )
    }
}