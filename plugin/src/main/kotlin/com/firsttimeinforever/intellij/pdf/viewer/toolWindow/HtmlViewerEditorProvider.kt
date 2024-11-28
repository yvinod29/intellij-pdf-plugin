package com.firsttimeinforever.intellij.pdf.viewer.toolWindow

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class HtmlViewerEditorProvider : FileEditorProvider, DumbAware {
  override fun accept(project: Project, file: VirtualFile): Boolean {
    // Accept logic here
    return file.name == "Viewer" || file.extension.isNullOrEmpty()
  }

  override fun createEditor(project: Project, file: VirtualFile): FileEditor {
    return HtmlViewerEditor(project, file)
  }

  override fun getEditorTypeId(): String = "html-viewer"

  override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}
