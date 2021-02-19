package com.monsanto.arch.awsutil.sns.model

import com.monsanto.arch.awsutil.sns.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.SnsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class ProtocolSpec extends AnyFreeSpec {
  "the Protocol object should" - {
    "round-trip protocol strings" in {
      forAll { protocol: Protocol ⇒
        protocol.asAws.asScala shouldBe protocol
      }
    }

    "fail to recognise bad protocols" in {
      an [IllegalArgumentException] shouldBe thrownBy("foo".asScala)
    }
  }

  "individual protocols should" - {
    "generate SubscriptionEndpoints" in {
      forAll { subscriptionEndpoint: SubscriptionEndpoint ⇒
        val SubscriptionEndpoint(protocol, endpoint) =  subscriptionEndpoint
        val result = protocol(endpoint)
        result shouldBe subscriptionEndpoint
      }
    }
  }
}
