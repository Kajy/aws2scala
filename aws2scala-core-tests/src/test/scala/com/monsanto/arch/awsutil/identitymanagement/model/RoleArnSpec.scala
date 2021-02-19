package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class RoleArnSpec extends AnyFreeSpec {
  "a RoleArn should" - {
    "provide the correct resource" in {
      forAll { arn: RoleArn =>
        val path = arn.path.pathString
        val roleName = arn.name
        arn.resource shouldBe s"role$path$roleName"
      }
    }

    "produce the correct ARN" in {
      forAll { arn: RoleArn =>
        val partition = arn.account.partition.id
        val account = arn.account.id
        val path = arn.path.pathString
        val roleName = arn.name

        arn.arnString shouldBe s"arn:$partition:iam::$account:role$path$roleName"
      }
    }

    "can round-trip via an ARN" in {
      forAll { arn: RoleArn =>
        RoleArn.fromArnString(arn.arnString) shouldBe arn
      }
    }

    "will fail to parse an invalid ARN" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        RoleArn.fromArnString("arn:aws:iam::111222333444:root")
      }
    }
  }
}
