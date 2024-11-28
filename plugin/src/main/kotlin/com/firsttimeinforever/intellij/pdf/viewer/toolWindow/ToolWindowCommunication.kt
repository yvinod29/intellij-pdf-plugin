// ToolWindowCommunication.kt
package com.firsttimeinforever.intellij.pdf.viewer.toolWindow

import com.intellij.util.messages.Topic

interface ToolWindowCommunication {
  fun submitInput(input: String)

  companion object {
    @JvmStatic
    val TOOL_WINDOW_TOPIC: Topic<ToolWindowCommunication> = Topic.create(
      "TOOL_WINDOW_COMMUNICATION",
      ToolWindowCommunication::class.java
    )
  }
}
