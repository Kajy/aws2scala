package com.monsanto.arch.awsutil

import com.monsanto.arch.awsutil.impl.Macros

import scala.language.experimental.macros
import scala.annotation.implicitNotFound

/** TakesNextToken is a typeclass representing classes with a 'withNextToken' method */
@implicitNotFound("Cannot prove that type ${R} has a withNextToken method")
trait TakesNextToken[R] {
  def withToken(req: R, tok: String): R
}

object TakesNextToken {
  def apply[R: TakesNextToken]: TakesNextToken[R] = implicitly

  implicit def instance[R]: TakesNextToken[R] = macro Macros.takesNextTokenImpl[R]

  final implicit class TTSyntax[R](val tt: R) extends AnyVal {
    def withToken(tok: String)(implicit r: TakesNextToken[R]): R = r.withToken(tt,tok)
  }
}
