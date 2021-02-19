package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.identitymanagement.AwsMatcherSupport
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class CreatePolicyVersionRequestSpec extends AnyFreeSpec with AwsMatcherSupport {
  "a CreatePolicyVersionRequest should" - {
    "convert to the correct AWS equivalent" in {
      forAll { request: CreatePolicyVersionRequest ⇒
        request.asAws should have (
          'PolicyArn (request.arn.arnString),
          'PolicyDocument (request.document.toJson),
          setAsDefault (request.setAsDefault)
        )
      }
    }
  }
}
