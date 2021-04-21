package com.kubukoz

import scala.reflect.macros.blackbox

object DebugUtils {

  def withDesugar[A](expr: A): A = macro withDesugarDefaultImpl[A]

  def withDesugarDefaultImpl[A](
      c: blackbox.Context
  )(expr: c.Expr[A]): c.Expr[A] = {
    import c.universe._
    withDesugarImpl(c)(expr, c.Expr(c.typecheck(q"false")))
  }

  def withDesugar[A](expr: A, showType: Boolean): A = macro withDesugarImpl[A]

  def withDesugarImpl[A](
      c: blackbox.Context
  )(expr: c.Expr[A], showType: c.Expr[Boolean]): c.Expr[A] = {
    import c.universe._

    val tru = c.Expr(c.typecheck(q"true")).actualType
    val fols = c.Expr(c.typecheck(q"false")).actualType

    val singletonBool: Option[Boolean] = showType.actualType match {
      case t if t <:< tru  => Some(true)
      case t if t <:< fols => Some(false)
      case _               => None
    }

    val literalBool: Option[Boolean] = showType.tree match {
      case Literal(Constant(b: Boolean)) => Some(b)
      case _                             => None
    }

    val shouldShowType = singletonBool.orElse(literalBool).getOrElse {
      c.abort(
        c.enclosingPosition,
        s"${showCode(showType.tree)} must be a compile-time constant or have a literal singleton type."
      )
    }

    c.info(
      c.enclosingPosition,
      showCode(
        expr.tree,
        printTypes = shouldShowType,
        printIds = true,
        printOwners = true
        // printRootPkg = true
      ),
      false
    )

    expr

  }
}
