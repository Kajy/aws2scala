package com.monsanto.arch.awsutil.identitymanagement.model

import com.amazonaws.services.identitymanagement.{model ⇒ aws}
import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.CoreGen
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AttachedPolicySpec extends AnyFreeSpec {
  "a AttachedPolicy can be round-tripped" - {
    "from its AWS equivalent" in {
      forAll(arbitrary[PolicyArn], CoreGen.iamName) { (arn, name) ⇒
        val attachedPolicy = new aws.AttachedPolicy()
          .withPolicyArn(arn.arnString)
          .withPolicyName(name)

        attachedPolicy.asScala.asAws shouldBe attachedPolicy
      }
    }

    "via its AWS equivalent" in {
      forAll { attachedPolicy: AttachedPolicy ⇒
        attachedPolicy.asAws.asScala shouldBe attachedPolicy
      }
    }
  }
}
