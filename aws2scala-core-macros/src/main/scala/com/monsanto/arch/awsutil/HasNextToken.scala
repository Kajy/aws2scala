package com.monsanto.arch.awsutil

import com.monsanto.arch.awsutil.impl.Macros

import scala.language.experimental.macros
import scala.annotation.implicitNotFound

/** HasNextToken is a typeclass representing classes with a 'getNextToken' method */
@implicitNotFound("Cannot prove that type ${R} has a getNextToken method")
trait HasNextToken[R] {
  def getToken(res: R): Option[String]
}

object HasNextToken {
  def apply[R: HasNextToken]: HasNextToken[R] = implicitly

  implicit def instance[R]: HasNextToken[R] = macro Macros.hasNextTokenImpl[R]

  final implicit class HTSyntax[R](val ht: R) extends AnyVal {
    def getToken(implicit r: HasNextToken[R]): Option[String] = r.getToken(ht)
  }
}
