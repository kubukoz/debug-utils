package com.kubukoz

def demo = DebugUtils.withDesugar(
  println(
    implicitly[ValueOf[42]].value
  )
)

def enumDemo = DebugUtils.withDesugarIgnore {
  enum Hello {
    case Foo(s: String)
  }
}
