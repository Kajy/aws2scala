package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class ManagedPolicyVersionSpec extends AnyFreeSpec {
  "a ManagedPolicyVersion should" - {
    "round-trip via its AWS equivalent" in {
      forAll { policyVersion: ManagedPolicyVersion ⇒
        policyVersion.asAws.asScala shouldBe policyVersion
      }
    }
  }
}
