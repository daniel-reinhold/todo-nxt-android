package de.todonxt.core.ui

import android.graphics.Color
import androidx.annotation.StringRes
import de.todonxt.R

enum class TextColor {
    WHITE, BLACK;
}

/**
 * This data class represents a material color
 *
 * @property hex - The hexadecimal representation of the color
 * @property shade - The shade of the color (50-900)
 * @property textColor - The text color of the text which is displayed on the color
 */
data class MaterialColor(
    val hex: String,
    val shade: String,
    val textColor: TextColor
)

data class MaterialPalette(
    val colors: List<MaterialColor>,
    @StringRes val stringResource: Int
)

private val COLORS_RED = listOf(
    MaterialColor("#FFEBEE", "50", TextColor.BLACK),
    MaterialColor("#FFCDD2", "100", TextColor.BLACK),
    MaterialColor("#EF9A9A", "200", TextColor.BLACK),
    MaterialColor("#E57373", "300", TextColor.BLACK),
    MaterialColor("#EF5350", "400", TextColor.BLACK),
    MaterialColor("#F44336", "500", TextColor.BLACK),
    MaterialColor("#E53935", "600", TextColor.BLACK),
    MaterialColor("#D32F2F", "700", TextColor.WHITE),
    MaterialColor("#C62828", "800", TextColor.WHITE),
    MaterialColor("#B71C1C", "900", TextColor.WHITE)
)

private val COLORS_PINK = listOf(
    MaterialColor("#FCE4EC", "50", TextColor.BLACK),
    MaterialColor("#F8BBD0", "100", TextColor.BLACK),
    MaterialColor("#F48FB1", "200", TextColor.BLACK),
    MaterialColor("#F06292", "300", TextColor.BLACK),
    MaterialColor("#EC407A", "400", TextColor.BLACK),
    MaterialColor("#E91E63", "500", TextColor.BLACK),
    MaterialColor("#D81B60", "600", TextColor.WHITE),
    MaterialColor("#C2185B", "700", TextColor.WHITE),
    MaterialColor("#AD1457", "800", TextColor.WHITE),
    MaterialColor("#880E4F", "900", TextColor.WHITE)
)

private val COLORS_PURPLE = listOf(
    MaterialColor("#F3E5F5", "50", TextColor.BLACK),
    MaterialColor("#E1BEE7", "100", TextColor.BLACK),
    MaterialColor("#CE93D8", "200", TextColor.BLACK),
    MaterialColor("#BA68C8", "300", TextColor.BLACK),
    MaterialColor("#AB47BC", "400", TextColor.WHITE),
    MaterialColor("#9C27B0", "500", TextColor.WHITE),
    MaterialColor("#8E24AA", "600", TextColor.WHITE),
    MaterialColor("#7B1FA2", "700", TextColor.WHITE),
    MaterialColor("#6A1B9A", "800", TextColor.WHITE),
    MaterialColor("#4A148C", "900", TextColor.WHITE)
)

private val COLORS_DEEP_PURPLE = listOf(
    MaterialColor("#EDE7F6", "50", TextColor.BLACK),
    MaterialColor("#D1C4E9", "100", TextColor.BLACK),
    MaterialColor("#B39DDB", "200", TextColor.BLACK),
    MaterialColor("#9575CD", "300", TextColor.BLACK),
    MaterialColor("#7E57C2", "400", TextColor.WHITE),
    MaterialColor("#673AB7", "500", TextColor.WHITE),
    MaterialColor("#5E35B1", "600", TextColor.WHITE),
    MaterialColor("#512DA8", "700", TextColor.WHITE),
    MaterialColor("#4527A0", "800", TextColor.WHITE),
    MaterialColor("#311B92", "900", TextColor.WHITE)
)

private val COLORS_INDIGO = listOf(
    MaterialColor("#E8EAF6", "50", TextColor.BLACK),
    MaterialColor("#C5CAE9", "100", TextColor.BLACK),
    MaterialColor("#9FA8DA", "200", TextColor.BLACK),
    MaterialColor("#7986CB", "300", TextColor.BLACK),
    MaterialColor("#5C6BC0", "400", TextColor.WHITE),
    MaterialColor("#3F51B5", "500", TextColor.WHITE),
    MaterialColor("#3949AB", "600", TextColor.WHITE),
    MaterialColor("#303F9F", "700", TextColor.WHITE),
    MaterialColor("#283593", "800", TextColor.WHITE),
    MaterialColor("#1A237E", "900", TextColor.WHITE)
)

private val COLORS_BLUE = listOf(
    MaterialColor("#E3F2FD", "50", TextColor.BLACK),
    MaterialColor("#BBDEFB", "100", TextColor.BLACK),
    MaterialColor("#90CAF9", "200", TextColor.BLACK),
    MaterialColor("#64B5F6", "300", TextColor.BLACK),
    MaterialColor("#42A5F5", "400", TextColor.BLACK),
    MaterialColor("#2196F3", "500", TextColor.BLACK),
    MaterialColor("#1E88E5", "600", TextColor.BLACK),
    MaterialColor("#1976D2", "700", TextColor.WHITE),
    MaterialColor("#1565C0", "800", TextColor.WHITE),
    MaterialColor("#0D47A1", "900", TextColor.WHITE)
)

private val COLORS_LIGHT_BLUE = listOf(
    MaterialColor("#E1F5FE", "50", TextColor.BLACK),
    MaterialColor("#B3E5FC", "100", TextColor.BLACK),
    MaterialColor("#81D4FA", "200", TextColor.BLACK),
    MaterialColor("#4FC3F7", "300", TextColor.BLACK),
    MaterialColor("#29B6F6", "400", TextColor.BLACK),
    MaterialColor("#03A9F4", "500", TextColor.BLACK),
    MaterialColor("#039BE5", "600", TextColor.BLACK),
    MaterialColor("#0288D1", "700", TextColor.BLACK),
    MaterialColor("#0277BD", "800", TextColor.WHITE),
    MaterialColor("#01579B", "900", TextColor.WHITE)
)

private val COLORS_CYAN = listOf(
    MaterialColor("#E0F7FA", "50", TextColor.BLACK),
    MaterialColor("#B2EBF2", "100", TextColor.BLACK),
    MaterialColor("#80DEEA", "200", TextColor.BLACK),
    MaterialColor("#4DD0E1", "300", TextColor.BLACK),
    MaterialColor("#26C6DA", "400", TextColor.BLACK),
    MaterialColor("#00BCD4", "500", TextColor.BLACK),
    MaterialColor("#00ACC1", "600", TextColor.BLACK),
    MaterialColor("#0097A7", "700", TextColor.BLACK),
    MaterialColor("#00838F", "800", TextColor.WHITE),
    MaterialColor("#006064", "900", TextColor.WHITE)
)

private val COLORS_TEAL = listOf(
    MaterialColor("#E0F2F1", "50", TextColor.BLACK),
    MaterialColor("#B2DFDB", "100", TextColor.BLACK),
    MaterialColor("#80CBC4", "200", TextColor.BLACK),
    MaterialColor("#4DB6AC", "300", TextColor.BLACK),
    MaterialColor("#26A69A", "400", TextColor.BLACK),
    MaterialColor("#009688", "500", TextColor.BLACK),
    MaterialColor("#00897B", "600", TextColor.BLACK),
    MaterialColor("#00796B", "700", TextColor.WHITE),
    MaterialColor("#00695C", "800", TextColor.WHITE),
    MaterialColor("#004D40", "900", TextColor.WHITE)
)

private val COLORS_GREEN = listOf(
    MaterialColor("#E8F5E9", "50", TextColor.BLACK),
    MaterialColor("#C8E6C9", "100", TextColor.BLACK),
    MaterialColor("#A5D6A7", "200", TextColor.BLACK),
    MaterialColor("#81C784", "300", TextColor.BLACK),
    MaterialColor("#66BB6A", "400", TextColor.BLACK),
    MaterialColor("#4CAF50", "500", TextColor.BLACK),
    MaterialColor("#43A047", "600", TextColor.BLACK),
    MaterialColor("#388E3C", "700", TextColor.BLACK),
    MaterialColor("#2E7D32", "800", TextColor.WHITE),
    MaterialColor("#1B5E20", "900", TextColor.WHITE)
)

private val COLORS_LIGHT_GREEN = listOf(
    MaterialColor("#F1F8E9", "50", TextColor.BLACK),
    MaterialColor("#DCEDC8", "100", TextColor.BLACK),
    MaterialColor("#C5E1A5", "200", TextColor.BLACK),
    MaterialColor("#AED581", "300", TextColor.BLACK),
    MaterialColor("#9CCC65", "400", TextColor.BLACK),
    MaterialColor("#8BC34A", "500", TextColor.BLACK),
    MaterialColor("#7CB342", "600", TextColor.BLACK),
    MaterialColor("#689F38", "700", TextColor.BLACK),
    MaterialColor("#558B2F", "800", TextColor.BLACK),
    MaterialColor("#33691E", "900", TextColor.WHITE)
)

private val COLORS_LIME = listOf(
    MaterialColor("#F9FBE7", "50", TextColor.BLACK),
    MaterialColor("#F0F4C3", "100", TextColor.BLACK),
    MaterialColor("#E6EE9C", "200", TextColor.BLACK),
    MaterialColor("#DCE775", "300", TextColor.BLACK),
    MaterialColor("#D4E157", "400", TextColor.BLACK),
    MaterialColor("#CDDC39", "500", TextColor.BLACK),
    MaterialColor("#C0CA33", "600", TextColor.BLACK),
    MaterialColor("#AFB42B", "700", TextColor.BLACK),
    MaterialColor("#9E9D24", "800", TextColor.BLACK),
    MaterialColor("#827717", "900", TextColor.WHITE)
)

private val COLORS_YELLOW = listOf(
    MaterialColor("#FFFDE7", "50", TextColor.BLACK),
    MaterialColor("#FFF9C4", "100", TextColor.BLACK),
    MaterialColor("#FFF59D", "200", TextColor.BLACK),
    MaterialColor("#FFF176", "300", TextColor.BLACK),
    MaterialColor("#FFEE58", "400", TextColor.BLACK),
    MaterialColor("#FFEB3B", "500", TextColor.BLACK),
    MaterialColor("#FDD835", "600", TextColor.BLACK),
    MaterialColor("#FBC02D", "700", TextColor.BLACK),
    MaterialColor("#F9A825", "800", TextColor.BLACK),
    MaterialColor("#F57F17", "900", TextColor.BLACK)
)

private val COLORS_AMBER = listOf(
    MaterialColor("#FFF8E1", "50", TextColor.BLACK),
    MaterialColor("#FFECB3", "100", TextColor.BLACK),
    MaterialColor("#FFE082", "200", TextColor.BLACK),
    MaterialColor("#FFD54F", "300", TextColor.BLACK),
    MaterialColor("#FFCA28", "400", TextColor.BLACK),
    MaterialColor("#FFC107", "500", TextColor.BLACK),
    MaterialColor("#FFB300", "600", TextColor.BLACK),
    MaterialColor("#FFA000", "700", TextColor.BLACK),
    MaterialColor("#FF8F00", "800", TextColor.BLACK),
    MaterialColor("#FF6F00", "900", TextColor.BLACK)
)

private val COLORS_ORANGE = listOf(
    MaterialColor("#FFF3E0", "50", TextColor.BLACK),
    MaterialColor("#FFE0B2", "100", TextColor.BLACK),
    MaterialColor("#FFCC80", "200", TextColor.BLACK),
    MaterialColor("#FFB74D", "300", TextColor.BLACK),
    MaterialColor("#FFA726", "400", TextColor.BLACK),
    MaterialColor("#FF9800", "500", TextColor.BLACK),
    MaterialColor("#FB8C00", "600", TextColor.BLACK),
    MaterialColor("#F57C00", "700", TextColor.BLACK),
    MaterialColor("#EF6C00", "800", TextColor.BLACK),
    MaterialColor("#E65100", "900", TextColor.BLACK)
)

private val COLORS_DEEP_ORANGE = listOf(
    MaterialColor("#FBE9E7", "50", TextColor.BLACK),
    MaterialColor("#FFCCBC", "100", TextColor.BLACK),
    MaterialColor("#FFAB91", "200", TextColor.BLACK),
    MaterialColor("#FF8A65", "300", TextColor.BLACK),
    MaterialColor("#FF7043", "400", TextColor.BLACK),
    MaterialColor("#FF5722", "500", TextColor.BLACK),
    MaterialColor("#F4511E", "600", TextColor.BLACK),
    MaterialColor("#E64A19", "700", TextColor.BLACK),
    MaterialColor("#D84315", "800", TextColor.BLACK),
    MaterialColor("#BF360C", "900", TextColor.WHITE)
)

private val COLORS_BROWN = listOf(
    MaterialColor("#EFEBE9", "50", TextColor.BLACK),
    MaterialColor("#D7CCC8", "100", TextColor.BLACK),
    MaterialColor("#BCAAA4", "200", TextColor.BLACK),
    MaterialColor("#A1887F", "300", TextColor.BLACK),
    MaterialColor("#8D6E63", "400", TextColor.WHITE),
    MaterialColor("#795548", "500", TextColor.WHITE),
    MaterialColor("#6D4C41", "600", TextColor.WHITE),
    MaterialColor("#5D4037", "700", TextColor.WHITE),
    MaterialColor("#4E342E", "800", TextColor.WHITE),
    MaterialColor("#3E2723", "900", TextColor.WHITE)
)

private val COLORS_GRAY = listOf(
    MaterialColor("#FAFAFA", "50", TextColor.BLACK),
    MaterialColor("#F5F5F5", "100", TextColor.BLACK),
    MaterialColor("#EEEEEE", "200", TextColor.BLACK),
    MaterialColor("#E0E0E0", "300", TextColor.BLACK),
    MaterialColor("#BDBDBD", "400", TextColor.BLACK),
    MaterialColor("#9E9E9E", "500", TextColor.BLACK),
    MaterialColor("#757575", "600", TextColor.WHITE),
    MaterialColor("#616161", "700", TextColor.WHITE),
    MaterialColor("#424242", "800", TextColor.WHITE),
    MaterialColor("#212121", "900", TextColor.WHITE)
)

private val COLORS_BLUE_GRAY = listOf(
    MaterialColor("#ECEFF1", "50", TextColor.BLACK),
    MaterialColor("#CFD8DC", "100", TextColor.BLACK),
    MaterialColor("#B0BEC5", "200", TextColor.BLACK),
    MaterialColor("#90A4AE", "300", TextColor.BLACK),
    MaterialColor("#78909C", "400", TextColor.BLACK),
    MaterialColor("#607D8B", "500", TextColor.BLACK),
    MaterialColor("#546E7A", "600", TextColor.WHITE),
    MaterialColor("#455A64", "700", TextColor.WHITE),
    MaterialColor("#37474F", "800", TextColor.WHITE),
    MaterialColor("#263238", "900", TextColor.WHITE)
)

val MATERIAL_COLORS = listOf(
    MaterialPalette(COLORS_RED, R.string.color_title_red),
    MaterialPalette(COLORS_PINK, R.string.color_title_pink),
    MaterialPalette(COLORS_PURPLE, R.string.color_title_purple),
    MaterialPalette(COLORS_DEEP_PURPLE, R.string.color_title_deep_purple),
    MaterialPalette(COLORS_INDIGO, R.string.color_title_indigo),
    MaterialPalette(COLORS_BLUE, R.string.color_title_blue),
    MaterialPalette(COLORS_LIGHT_BLUE, R.string.color_title_light_blue),
    MaterialPalette(COLORS_CYAN, R.string.color_title_cyan),
    MaterialPalette(COLORS_TEAL, R.string.color_title_teal),
    MaterialPalette(COLORS_GREEN, R.string.color_title_green),
    MaterialPalette(COLORS_LIGHT_GREEN, R.string.color_title_light_green),
    MaterialPalette(COLORS_LIME, R.string.color_title_lime),
    MaterialPalette(COLORS_YELLOW, R.string.color_title_yellow),
    MaterialPalette(COLORS_AMBER, R.string.color_title_amber),
    MaterialPalette(COLORS_ORANGE, R.string.color_title_orange),
    MaterialPalette(COLORS_DEEP_ORANGE, R.string.color_title_deep_orange),
    MaterialPalette(COLORS_BROWN, R.string.color_title_brown),
    MaterialPalette(COLORS_GRAY, R.string.color_title_gray),
    MaterialPalette(COLORS_BLUE_GRAY, R.string.color_title_blue_gray)
)