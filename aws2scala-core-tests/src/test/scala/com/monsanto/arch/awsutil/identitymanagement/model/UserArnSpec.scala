package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class UserArnSpec extends AnyFreeSpec {
  "a UserArn should" - {
    "provide the correct resource" in {
      forAll { arn: UserArn ⇒
        arn.resource shouldBe s"user${arn.path.pathString}${arn.name}"
      }
    }

    "produce the correct ARN" in {
      forAll { arn: UserArn ⇒
        val partition = arn.account.partition.id
        val path = arn.path.pathString
        val userName = arn.name
        val account = arn.account.id
        arn.arnString shouldBe s"arn:$partition:iam::$account:user$path$userName"
      }
    }

    "can round-trip via an ARN" in {
      forAll { arn: UserArn ⇒
        UserArn.fromArnString(arn.arnString) shouldBe arn
      }
    }

    "will fail to parse an invalid ARN" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        UserArn.fromArnString("arn:aws:iam::111222333444:root")
      }
    }
  }
}
