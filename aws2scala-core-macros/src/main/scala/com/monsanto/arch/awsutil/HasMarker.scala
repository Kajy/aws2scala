package com.monsanto.arch.awsutil

import com.monsanto.arch.awsutil.impl.Macros

import scala.language.experimental.macros
import scala.annotation.implicitNotFound

/** HasMarker is a typeclass representing classes with a 'getMarker' method */
@implicitNotFound("Cannot prove that type ${R} has a getMarker method")
trait HasMarker[R] {
  def getMarker(res: R): Option[String]
}

object HasMarker {
  def apply[R: HasMarker]: HasMarker[R] = implicitly

  implicit def instance[R]: HasMarker[R] = macro Macros.hasMarkerImpl[R]

  final implicit class HMSyntax[R](val hm: R) extends AnyVal {
    def getMarker(implicit r: HasMarker[R]): Option[String] = r.getMarker(hm)
  }
}
