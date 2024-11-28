package com.firsttimeinforever.intellij.pdf.viewer.mpi


external fun encodeURIComponent(data: String): String

external fun decodeURIComponent(data: String): String

actual object MessageEncoder {
  actual fun encode(data: String): String {
    return encodeURIComponent(data)
  }
  actual fun returnTime(): String {
    val moment = moment()
    return moment.format("YYYY-MM-DD HH:mm:ss")
  }

  actual fun decode(data: String): String {
    return decodeURIComponent(data)
  }
}
