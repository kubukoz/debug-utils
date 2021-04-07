# debug-utils

[![License](http://img.shields.io/:license-Apache%202-green.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

Just some utilities for debugging daily development issues in Scala.

```scala
"com.kubukoz" %% "debug-utils" % "1.1.0"
```

Published for Scala 2.12, 2.13 and 3.0.0-RC2.

## Usage

> Note: There's another library that does a better job at this - see [macrolizer](https://github.com/sirthias/macrolizer)

### `withDesugar`

Wrapping an expression in `withDesugar` will print its desugared (scala 3: fully elaborated) form at compile time.

In Scala 2.x, the code will be printed at INFO level.

Due to the changes in Scala 3's metaprogramming, the desugared code is printed as a warning.

```scala
import com.kubukoz.DebugUtils

def demo = DebugUtils.withDesugar(
  println(
    implicitly[ValueOf[42]].value
  )
)

// The above shows (in Scala 3.x):
// scala.Predef.println(scala.Predef.implicitly[scala.ValueOf[42]](new scala.ValueOf[42](42)).value)
```

