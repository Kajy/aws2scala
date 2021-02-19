package com.monsanto.arch.awsutil.securitytoken.model

import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AssumedRoleArnSpec extends AnyFreeSpec {
  "an AssumedRoleArn should" - {
    "provide the correct resource" in {
      forAll { arn: AssumedRoleArn =>
        arn.resource shouldBe s"assumed-role/${arn.roleName}/${arn.sessionName}"
      }
    }

    "produce the correct ARN" in {
      forAll { arn: AssumedRoleArn =>
        val partition = arn.account.partition
        val account = arn.account.id
        val roleName = arn.roleName
        val sessionName = arn.sessionName
        arn.arnString shouldBe s"arn:$partition:sts::$account:assumed-role/$roleName/$sessionName"
      }
    }

    "can round-trip via an ARN" in {
      forAll { arn: AssumedRoleArn =>
        AssumedRoleArn.fromArnString(arn.arnString) shouldBe arn
      }
    }

    "will fail to parse an invalid ARN" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        AssumedRoleArn.fromArnString("arn:aws:iam::111222333444:root")
      }
    }
  }
}
