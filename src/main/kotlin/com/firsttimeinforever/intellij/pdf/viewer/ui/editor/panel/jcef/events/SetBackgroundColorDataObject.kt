package com.firsttimeinforever.intellij.pdf.viewer.ui.editor.panel.jcef.events

import com.firsttimeinforever.intellij.pdf.viewer.ui.editor.panel.jcef.transformColor
import kotlinx.serialization.Serializable
import java.awt.Color

@Serializable
class SetBackgroundColorDataObject(val color: String) {
    companion object {
        fun from(color: Color): SetBackgroundColorDataObject {
            return SetBackgroundColorDataObject(transformColor(color))
        }
    }
}
