package com.monsanto.arch.awsutil

import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AccountArnSpec extends AnyFreeSpec {
  "an account ARN should" - {
    "have the correct resource" in {
      forAll { arn: AccountArn ⇒
        arn.resource shouldBe "root"
      }
    }

    "produce the correct ARN string" in {
      forAll { arn: AccountArn ⇒
        arn.arnString shouldBe s"arn:${arn.account.partition}:iam::${arn.account.id}:root"
      }
    }

    "round-trip via an ARN string" in {
      forAll { arn: AccountArn ⇒
        AccountArn.fromArnString(arn.arnString) shouldBe arn
      }
    }

    "will fail to parse an invalid ARN" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        AccountArn.fromArnString("arn:aws:iam::111122223333:user/foo")
      }
    }
  }
}
