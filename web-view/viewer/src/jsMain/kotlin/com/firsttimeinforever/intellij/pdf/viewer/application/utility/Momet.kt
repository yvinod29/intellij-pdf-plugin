package com.firsttimeinforever.intellij.pdf.viewer.application.utility

@JsModule("moment")
@JsNonModule
external fun moment(): Moment

@JsModule("moment")
@JsNonModule
external fun moment(date: String): Moment

@JsModule("moment")
@JsNonModule
external fun moment(date: Long): Moment

external interface Moment {
  fun format(format: String): String
  fun add(amount: Long, unit: String): Moment
  fun subtract(amount: Long, unit: String): Moment
  fun isBefore(date: Moment): Boolean
}
