package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.ec2.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class KeyPairInfoSpec extends AnyFreeSpec {
  "a KeyPairInfo should" - {
    "be constructed from an AWS instance" in {
      forAll { keyPairInfo: KeyPairInfo =>
        KeyPairInfo.fromAws(keyPairInfo.toAws) shouldBe keyPairInfo
      }
    }
  }
}
