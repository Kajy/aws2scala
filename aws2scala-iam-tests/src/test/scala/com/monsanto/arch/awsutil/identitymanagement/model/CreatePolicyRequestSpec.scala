package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class CreatePolicyRequestSpec extends AnyFreeSpec {
  "a CreatePolicyRequest should" - {
    "transform to the correct AWS request" in {
      forAll { request: CreatePolicyRequest =>
        request.asAws should have (
          Symbol("Description") (request.description.orNull),
          Symbol("Path") (request.path.pathString),
          Symbol("PolicyDocument") (request.document.toJson),
          Symbol("PolicyName") (request.name)
        )
      }
    }

    "round-trip via an AWS request" in {
      forAll { request: CreatePolicyRequest =>
        request.asAws.asScala shouldBe request
      }
    }
  }
}
