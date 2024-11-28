package com.firsttimeinforever.intellij.pdf.viewer.toolWindow
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import javax.swing.JButton
import com.intellij.openapi.application.ApplicationManager
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.SwingUtilities


import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.testFramework.LightVirtualFile
import com.intellij.openapi.fileEditor.OpenFileDescriptor

class MyToolWindowFactory : ToolWindowFactory {
  private val logger = Logger.getInstance(MyToolWindowFactory::class.java)


  override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
    val myToolWindow = MyToolWindow(project)
    val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), "", false)
    toolWindow.contentManager.addContent(content)
    logger.info("Tool window content added")
  }

  override fun shouldBeAvailable(project: Project): Boolean {
    logger.info("Checking if tool window should be available")
    return true
  }

  class MyToolWindow(private val project: Project) {
    private val logger = Logger.getInstance(MyToolWindow::class.java)
    private val inputLabel = JLabel("Awaiting input...") // Label to display received input


    init {
      project.messageBus.connect().subscribe(ToolWindowCommunication.TOOL_WINDOW_TOPIC, object : ToolWindowCommunication {
        override fun submitInput(input: String) {
          SwingUtilities.invokeLater {
            inputLabel.text = "Received: $input"
          }
        }
      })
    }
    fun getContent(): JPanel = JBPanel<JBPanel<*>>().apply {
      layout = BorderLayout()
      add(JBLabel("Input Display:"), BorderLayout.NORTH)
      add(inputLabel, BorderLayout.CENTER)
      val label = JBLabel("Ready to open web view")
      add(label, BorderLayout.NORTH)
      add(JButton("Open Web View").apply {
        addActionListener {
          logger.info("Open Web View button clicked")
          openWebView(project)
        }
      }, BorderLayout.NORTH)
    }

    private fun openWebView(project: Project) {
      logger.info("Attempting to open web view")
      ApplicationManager.getApplication().invokeLater {
        val browser = JBCefBrowser()


        // Create a new light virtual file to display the content
        val virtualFile = LightVirtualFile("HTMLViewer", browser.component.toString())

        // Open the virtual file in the editor
        val fileEditorManager = FileEditorManager.getInstance(project)
        ApplicationManager.getApplication().invokeLater {
          fileEditorManager.openFile(virtualFile, true)
        }
      }
    }
  }
}
