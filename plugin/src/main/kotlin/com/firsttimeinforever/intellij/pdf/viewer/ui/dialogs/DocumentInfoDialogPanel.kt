package com.firsttimeinforever.intellij.pdf.viewer.ui.dialogs

import com.firsttimeinforever.intellij.pdf.viewer.MyBundle
import com.firsttimeinforever.intellij.pdf.viewer.model.DocumentInfo
import java.awt.BorderLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

internal class DocumentInfoDialogPanel(info: DocumentInfo) : JPanel() {
  init {
    layout = BoxLayout(this, BoxLayout.Y_AXIS)
    with(info) {
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.filename"), fileName))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.file.size"), fileSize))
      add(JSeparator(SwingConstants.HORIZONTAL))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.title"), title))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.subject"), subject))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.author"), author))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.creator"), creator))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.creation.date"), creationDate))
      add(
        EntryPanel(
          MyBundle.message("pdf.viewer.document.info.modification.date"),
          modificationDate
        )
      )
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.producer"), producer))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.version"), version))
      add(JSeparator(SwingConstants.HORIZONTAL))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.page.size"), pageSize))
      add(EntryPanel(MyBundle.message("pdf.viewer.document.info.linearized"), linearized))
    }
  }

  private class EntryPanel(label: String, value: String) : JPanel(BorderLayout()) {
    init {
      border = EmptyBorder(5, 10, 5, 10)
      add(JLabel("$label:  "), BorderLayout.WEST)
      add(
        when {
          value == "-" || value.isEmpty() -> JLabel("unspecified")
          else -> JLabel(value)
        }, BorderLayout.EAST
      )
    }
  }
}
