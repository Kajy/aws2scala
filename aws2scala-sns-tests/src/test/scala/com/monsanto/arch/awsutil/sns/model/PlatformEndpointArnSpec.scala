package com.monsanto.arch.awsutil.sns.model

import com.monsanto.arch.awsutil.testkit.SnsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class PlatformEndpointArnSpec extends AnyFreeSpec {
  "a PlatformEndpointArn should" - {
    "provide the correct resource" in {
      forAll { arn: PlatformEndpointArn ⇒
        val platform = arn.platform.name
        val applicationName = arn.applicationName
        val endpointId = arn.endpointId

        arn.resource shouldBe s"endpoint/$platform/$applicationName/$endpointId"
      }
    }

    "produce the correct ARN" in {
      forAll { arn: PlatformEndpointArn ⇒
        val partition = arn.account.partition
        val region = arn.region.name
        val account = arn.account.id
        val platform = arn.platform.name
        val applicationArn = arn.applicationName
        val endpointId = arn.endpointId

        arn.arnString shouldBe s"arn:$partition:sns:$region:$account:endpoint/$platform/$applicationArn/$endpointId"
      }
    }

    "can round-trip via an ARN" in {
      forAll { arn: PlatformEndpointArn ⇒
        PlatformEndpointArn.fromArnString(arn.arnString) shouldBe arn
      }
    }

    "will fail to parse an invalid ARN" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        PlatformEndpointArn.fromArnString("arn:aws:iam::111222333444:root")
      }
    }
  }
}
