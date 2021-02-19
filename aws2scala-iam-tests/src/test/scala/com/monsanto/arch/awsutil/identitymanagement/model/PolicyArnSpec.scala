package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.identitymanagement.IdentityManagement
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class PolicyArnSpec extends AnyFreeSpec {
  IdentityManagement.init()

  "a PolicyArn should" - {
    "provide the correct resource" in {
      forAll { arn: PolicyArn =>
        arn.resource shouldBe s"policy${arn.path.pathString}${arn.name}"
      }
    }

    "produce the correct ARN" in {
      forAll { arn: PolicyArn =>
        val partition = arn.account.partition.id
        val accountId = arn.account.id
        val path = arn.path.pathString
        val policyName = arn.name

        arn.arnString shouldBe s"arn:$partition:iam::$accountId:policy$path$policyName"
      }
    }

    "can round-trip via an ARN" in {
      forAll { arn: PolicyArn =>
        PolicyArn.fromArnString(arn.arnString) shouldBe arn
      }
    }

    "will fail to parse an invalid ARN" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        PolicyArn.fromArnString("arn:aws:iam::111222333444:root")
      }
    }
  }
}
