package com.firsttimeinforever.intellij.pdf.viewer.actions.view

import com.firsttimeinforever.intellij.pdf.viewer.MyBundle
import com.firsttimeinforever.intellij.pdf.viewer.actions.PdfDumbAwareAction
import com.firsttimeinforever.intellij.pdf.viewer.model.PageSpreadState
import com.firsttimeinforever.intellij.pdf.viewer.model.ScrollDirection
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnActionEvent

class PdfToggleScrollDirectionAction : PdfDumbAwareAction() {
  override fun actionPerformed(event: AnActionEvent) {
    val controller = findController(event) ?: return
    with(controller) {
      // This is needed to prevent weird behaviour
      setPageSpreadState(PageSpreadState.NONE)
      when (viewState.scrollDirection) {
        ScrollDirection.VERTICAL -> setScrollDirection(ScrollDirection.HORIZONTAL)
        ScrollDirection.HORIZONTAL -> setScrollDirection(ScrollDirection.VERTICAL)
      }
    }
  }

  override fun update(event: AnActionEvent) {
    super.update(event)
    val controller = findController(event) ?: return
    when (controller.viewState.scrollDirection) {
      ScrollDirection.VERTICAL -> {
        event.presentation.text = VERTICAL_TEXT
        event.presentation.description = VERTICAL_DESCRIPTION
        event.presentation.icon = AllIcons.Actions.SplitVertically
      }
      ScrollDirection.HORIZONTAL -> {
        event.presentation.text = HORIZONTAL_TEXT
        event.presentation.description = HORIZONTAL_DESCRIPTION
        event.presentation.icon = AllIcons.Actions.SplitHorizontally
      }
    }
  }

  private companion object {
    val VERTICAL_TEXT = MyBundle.message("pdf.viewer.actions.pdfjs.set.vertical.scrolling.name")
    val VERTICAL_DESCRIPTION = MyBundle.message("pdf.viewer.actions.pdfjs.set.vertical.scrolling.description")
    val HORIZONTAL_TEXT = MyBundle.message("pdf.viewer.actions.pdfjs.set.horizontal.scrolling.name")
    val HORIZONTAL_DESCRIPTION = MyBundle.message("pdf.viewer.actions.pdfjs.set.horizontal.scrolling.description")
  }
}
