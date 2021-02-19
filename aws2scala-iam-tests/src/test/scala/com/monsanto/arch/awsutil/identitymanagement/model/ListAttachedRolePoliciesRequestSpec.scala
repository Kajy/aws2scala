package com.monsanto.arch.awsutil.identitymanagement.model

import com.amazonaws.services.identitymanagement.{model ⇒ aws}
import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.CoreGen
import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class ListAttachedRolePoliciesRequestSpec extends AnyFreeSpec {
  "the list all roles request" - {
    "creates the correct AWS request" in {
      forAll(CoreGen.iamName) { roleName ⇒
        val request = ListAttachedRolePoliciesRequest(roleName)
        val awsRequest = new aws.ListAttachedRolePoliciesRequest().withRoleName(roleName)

        request.asAws shouldBe awsRequest
      }
    }

    "creates new requests" in {
      forAll(CoreGen.iamName) { roleName ⇒
        val request = ListAttachedRolePoliciesRequest(roleName)

        request.asAws should not be theSameInstanceAs (request.asAws)
      }
    }
  }

  "a list roles with prefix" - {
    "creates the correct AWS request" in {
      forAll(CoreGen.iamName, arbitrary[Path]) { (roleName, path) ⇒
        val request = ListAttachedRolePoliciesRequest(roleName, path)
        val awsRequest = new aws.ListAttachedRolePoliciesRequest()
          .withRoleName(roleName)
          .withPathPrefix(request.prefix.get.pathString)
        request.asAws shouldBe awsRequest
      }
    }

    "creates new requests" in {
      forAll(CoreGen.iamName, arbitrary[Path]) { (roleName, path) ⇒
        val request = ListAttachedRolePoliciesRequest(roleName, path)
        request.asAws should not be theSameInstanceAs (request.asAws)
      }
    }
  }
}
