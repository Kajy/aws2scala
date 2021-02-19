package com.monsanto.arch.awsutil

import com.monsanto.arch.awsutil.impl.Macros

import scala.language.experimental.macros
import scala.annotation.implicitNotFound

/**
  * TakesMarker is a typeclass representing classes with a 'withMarker' method
  */
@implicitNotFound("Cannot prove that type ${R} has a withMarker method")
trait TakesMarker[R] {
  def withMarker(req: R, marker: String): R
}

object TakesMarker {
  def apply[R: TakesMarker]: TakesMarker[R] = implicitly

  implicit def instance[R]: TakesMarker[R] = macro Macros.takesMarkerImpl[R]

  final implicit class TMSyntax[R](val tm: R) extends AnyVal {
    def withMarker(marker: String)(implicit r: TakesMarker[R]): R = r.withMarker(tm,marker)
  }
}
