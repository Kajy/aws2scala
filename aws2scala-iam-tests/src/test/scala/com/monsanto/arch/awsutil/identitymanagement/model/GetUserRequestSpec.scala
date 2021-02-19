package com.monsanto.arch.awsutil.identitymanagement.model

import com.amazonaws.services.identitymanagement.{model => aws}
import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.CoreGen
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class GetUserRequestSpec extends AnyFreeSpec {
  "the get current user request" - {
    "creates the correct AWS request" in {
      GetUserRequest.currentUser.asAws shouldBe new aws.GetUserRequest
    }

    "creates new requests" in {
      GetUserRequest.currentUser.asAws should not be theSameInstanceAs (GetUserRequest.currentUser.asAws)
    }
  }

  "a list roles with prefix" - {
    "creates the correct AWS request" in {
      forAll(CoreGen.iamName) { name =>
        val request = GetUserRequest.forUserName(name)
        val awsRequest = new aws.GetUserRequest().withUserName(request.userName.get)
        request.asAws shouldBe awsRequest
      }
    }

    "creates new requests" in {
      forAll(CoreGen.iamName) { name =>
        val request = GetUserRequest.forUserName(name)
        request.asAws should not be theSameInstanceAs (request.asAws)
      }
    }
  }
}
