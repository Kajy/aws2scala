package com.monsanto.arch.awsutil.identitymanagement.model

import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.identitymanagement.AwsMatcherSupport
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class GetPolicyVersionRequestSpec extends AnyFreeSpec with AwsMatcherSupport {
  "a GetPolicyVersionRequest should" - {
    "convert to the correct AWS equivalent" in {
      forAll { request: GetPolicyVersionRequest =>
        request.asAws should have (
          Symbol("PolicyArn") (request.arn.arnString),
          Symbol("VersionId") (request.versionId)
        )
      }
    }
  }
}
