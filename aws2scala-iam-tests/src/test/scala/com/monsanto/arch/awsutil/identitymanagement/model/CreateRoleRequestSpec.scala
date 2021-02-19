package com.monsanto.arch.awsutil.identitymanagement.model

import com.amazonaws.services.identitymanagement.{model ⇒ aws}
import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class CreateRoleRequestSpec extends AnyFreeSpec {
  "a CreateRoleRequest can be round-tripped" - {
    "from its AWS equivalent" in {
      forAll { request: CreateRoleRequest ⇒
        val awsRequest = new aws.CreateRoleRequest()
          .withRoleName(request.name)
          .withAssumeRolePolicyDocument(request.assumeRolePolicy.toJson)
        request.path.foreach(p ⇒ awsRequest.setPath(p.pathString))

        awsRequest.asScala.asAws shouldBe awsRequest
      }
    }

    "via its AWS equivalent" in {
      forAll { request: CreateRoleRequest ⇒
        request.asAws.asScala shouldBe request
      }
    }
  }
}
