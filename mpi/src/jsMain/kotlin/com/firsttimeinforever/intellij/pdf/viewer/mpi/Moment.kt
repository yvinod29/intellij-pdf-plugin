package com.firsttimeinforever.intellij.pdf.viewer.mpi

@JsModule("moment")
@JsNonModule


external fun moment(): Moment

actual external class Moment {
  actual fun format(format: String): String

}
