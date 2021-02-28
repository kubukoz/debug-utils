package com.kubukoz

import scala.quoted._

object DebugUtils:

  inline def withDesugar[A](inline expr: A): A = ${ withDesugarImpl('expr) }

  private def withDesugarImpl[A](expr: Expr[A])(using Quotes): Expr[A] =
    import quotes.reflect.given
    import quotes.reflect._

    report.warning(TreePrinter.show(expr.asTerm), expr)
    expr
