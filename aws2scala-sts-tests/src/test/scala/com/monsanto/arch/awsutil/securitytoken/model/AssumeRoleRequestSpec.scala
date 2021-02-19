package com.monsanto.arch.awsutil.securitytoken.model

import com.monsanto.arch.awsutil.securitytoken.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.StsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AssumeRoleRequestSpec extends AnyFreeSpec {
  "an AssumeRoleRequestInstance will" - {
    "correctly convert to its AWS equivalent" in {
      forAll { request: AssumeRoleRequest =>
        request.asAws should have (
          Symbol("roleArn") (request.roleArn),
          Symbol("roleSessionName") (request.roleSessionName),
          Symbol("durationSeconds") (request.duration.map(d => Integer.valueOf(d.toSeconds.toInt)).orNull),
          Symbol("externalId") (request.externalId.orNull),
          Symbol("policy") (request.policy.orNull),
          Symbol("serialNumber") (request.mfa.map(_.serialNumber).orNull),
          Symbol("tokenCode") (request.mfa.map(_.tokenCode).orNull)
        )
      }
    }
  }
}
