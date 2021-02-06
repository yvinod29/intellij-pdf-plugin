package com.firsttimeinforever.intellij.pdf.viewer.ui.editor.panel.jcef.events.objects

import kotlinx.serialization.Serializable

/**
 * @property page The page of the pdf file.
 * @property x The x coordinate in the pdf file.
 * @property y The y coordinate in the pdf file.
 */
@Serializable
data class SynctexInfoDataObject(
    val page: Int,
    val x: Int,
    val y: Int
)