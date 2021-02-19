package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class PathSpec extends AnyFreeSpec {
  "a Path should" - {
    "be round-tripped via its string representation" in {
      forAll { path: Path =>
        Path.fromPathString(path.pathString) shouldBe path
      }
    }

    "allow concatenation using /" in {
      forAll(arbitrary[Path] ->"path", Gen.identifier ->"element") { (path, element) =>
        (path / element) shouldBe Path(path.elements :+ element)
      }
    }

    "should not parse invalid path strings" in {
      forAll { pathString: String =>
        whenever(!pathString.matches("^(\u002F)|(\u002F[\u0021-\u007F]+\u002F)$")) {
          an [IllegalArgumentException] shouldBe thrownBy {
            Path.fromPathString(pathString)
          }
        }
      }
    }

    "should not lazyAppendedAll a path element with a slash" in {
      val badElement =
        (
          for {
            element <- Gen.identifier.suchThat(_.length > 1)
            n <- Gen.choose(1, element.length - 1)
          } yield {
            val (prefix, suffix) = element.splitAt(n)
            s"$prefix/$suffix"
          }
        ).suchThat(_.contains("/"))
      forAll(arbitrary[Path] ->"path", badElement ->"element") { (path, element) =>
        an [IllegalArgumentException] shouldBe thrownBy {
          path / element
        }
      }
    }
  }
}
