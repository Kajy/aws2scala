package com.monsanto.arch.awsutil

import com.monsanto.arch.awsutil.impl.Macros

import scala.language.experimental.macros
import scala.annotation.implicitNotFound

/** HasNextMarker is a typeclass representing classes with a 'getNextMarker' method */
@implicitNotFound("Cannot prove that type ${R} has a getNextMarker method")
trait HasNextMarker[R] {
  def getNextMarker(res: R): Option[String]
}

object HasNextMarker {
  def apply[R: HasNextMarker]: HasNextMarker[R] = implicitly

  implicit def instance[R]: HasNextMarker[R] = macro Macros.hasNextMarkerImpl[R]

  final implicit class HNMSyntax[R](val hnm: R) extends AnyVal {
    def getNextMarker(implicit r: HasNextMarker[R]): Option[String] = r.getNextMarker(hnm)
  }
}
