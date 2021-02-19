package com.monsanto.arch.awsutil.kms.model

import com.monsanto.arch.awsutil.converters.KmsConverters._
import com.monsanto.arch.awsutil.testkit.KmsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class CreateKeyWithAliasRequestSpec extends AnyFreeSpec {
  "a CreateKeyWithAliasRequest should" - {
    "convert to a correct AWS CreateKeyRequest object" in {
      forAll { request: CreateKeyWithAliasRequest =>
        val awsRequest = request.asAws
        awsRequest.getBypassPolicyLockoutSafetyCheck shouldBe
          request.bypassPolicyLockoutSafetyCheck.map(java.lang.Boolean.valueOf).orNull
        request.asAws should have (
          Symbol("Description") (request.description.orNull),
          Symbol("KeyUsage") (request.keyUsage.name),
          Symbol("Policy") (request.policy.map(_.toJson).orNull)
        )
      }
    }
  }
}
