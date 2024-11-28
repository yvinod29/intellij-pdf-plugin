package com.firsttimeinforever.intellij.pdf.viewer.mpi

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

actual class Moment(private val dateTime: LocalDateTime = LocalDateTime.now()) {

  actual fun format(format: String): String {
    // Translate the `moment`-style format to Java's DateTimeFormatter syntax
    val javaFormat = format.replace("YYYY", "yyyy")
      .replace("DD", "dd")
      .replace("HH", "HH")
      .replace("mm", "mm")
      .replace("ss", "ss")
    val formatter = DateTimeFormatter.ofPattern(javaFormat)
    return dateTime.format(formatter)
  }


}
