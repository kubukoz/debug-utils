package com.kubukoz

import scala.quoted._

object DebugUtils:

  inline def withDesugar[A](inline expr: A): A = ${
    withDesugarImpl('expr)
  }

  /**  Can be used when the expression being printed can't be returned (e.g. it's an enum definition with class members).
    */
  inline def withDesugarIgnore(inline expr: Any): Unit = ${
    withDesugarIgnoreImpl('expr)
  }

  private def withDesugarImpl[A](expr: Expr[A])(using Quotes): Expr[A] =
    withDesugarIgnoreImpl(expr)
    expr

  private def withDesugarIgnoreImpl[A](expr: Expr[A])(using
      Quotes
  ): Expr[Unit] =
    import quotes.reflect.given
    import quotes.reflect._

    report.info(TreePrinter.show(expr.asTerm), expr)
    '{ () }
