package com.firsttimeinforever.intellij.pdf.viewer.toolWindow


import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.jcef.*
import javax.swing.JComponent
import javax.swing.JLabel
import java.beans.PropertyChangeListener
import org.jetbrains.annotations.NotNull
import org.slf4j.LoggerFactory
import javax.swing.SwingUtilities
import com.intellij.openapi.application.ApplicationManager
import javax.swing.JOptionPane;


class HtmlViewerEditor(private val project: Project, private val file: VirtualFile) : FileEditor {
  private var browser: JBCefBrowser? = null
  private val myComponent: JComponent
  private var jsQuery: JBCefJSQuery? = null
  private var receivedInput: String = ""  // Kotlin variable to store the input
  private val logger = LoggerFactory.getLogger(HtmlViewerEditor::class.java)

  init {
    if (JBCefApp.isSupported()) {
      val client = JBCefApp.getInstance().createClient()
      browser = JBCefBrowserBuilder().setClient(client).build()
      setupBrowser()
      myComponent = browser?.component ?: JLabel("Browser component is not available.")
    } else {
      myComponent = JLabel("JCEF is not supported on this platform.")
    }
  }

  private fun setupBrowser() {
    val query = JBCefJSQuery.create(browser as JBCefBrowserBase)
    query.addHandler { input ->
      SwingUtilities.invokeLater {

        receivedInput = input  // Save the received input to the Kotlin variable
        println("Received input: $receivedInput")
        ApplicationManager.getApplication().invokeLater {
          ApplicationManager.getApplication().messageBus
            .syncPublisher(ToolWindowCommunication.TOOL_WINDOW_TOPIC)
            .submitInput(receivedInput)
          JOptionPane.showMessageDialog(null, "Input received: $receivedInput", "Alert", JOptionPane.INFORMATION_MESSAGE)
        }
      }

      null
    }


    browser?.let { b ->
      val htmlContent = """
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; padding: 20px; }
                        input, button { padding: 10px; margin: 5px; }
                        #output { color: green; margin-top: 20px; }
                    </style>
                </head>
                <body>
                    <input type="text" id="inputField" placeholder="Enter something...">
                    <button onclick="sendInput()">Submit</button>
                    <div id="output"></div>
                    <script>
                        function sendInput() {
                            var input = document.getElementById('inputField').value;
                            ${query.inject("input")}  // Using JBCefJSQuery to send input to Kotlin
                            document.getElementById('output').innerText = 'Input sent: ' + input;
                        }
                    </script>
                </body>
                </html>
            """.trimIndent()
      b.loadHTML(htmlContent)
    }
  }

  override fun getComponent(): JComponent = myComponent
  override fun getPreferredFocusedComponent(): JComponent? = myComponent
  override fun getName(): String = "Untitled"
  override fun getFile(): @NotNull VirtualFile = file
  override fun setState(state: FileEditorState) {}
  override fun isModified(): Boolean = false
  override fun isValid(): Boolean = true
  override fun addPropertyChangeListener(listener: PropertyChangeListener) {}
  override fun removePropertyChangeListener(listener: PropertyChangeListener) {}
  override fun dispose() {
    jsQuery?.dispose()  // Ensure to dispose of the JSQuery
    browser?.dispose()  // Ensure to dispose of the browser and the client
  }

  override fun <T : Any?> getUserData(key: Key<T>): T? = null
  override fun <T : Any?> putUserData(key: Key<T>, value: T?) {}
}
