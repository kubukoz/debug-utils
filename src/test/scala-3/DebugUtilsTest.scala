package com.kubukoz

def demo = DebugUtils.withDesugar(
  println(
    implicitly[ValueOf[42]].value
  )
)
