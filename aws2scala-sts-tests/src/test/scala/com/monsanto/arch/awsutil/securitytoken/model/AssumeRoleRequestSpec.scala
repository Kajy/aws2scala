package com.monsanto.arch.awsutil.securitytoken.model

import com.monsanto.arch.awsutil.securitytoken.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.StsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AssumeRoleRequestSpec extends AnyFreeSpec {
  "an AssumeRoleRequestInstance will" - {
    "correctly convert to its AWS equivalent" in {
      forAll { request: AssumeRoleRequest ⇒
        request.asAws should have (
          'roleArn (request.roleArn),
          'roleSessionName (request.roleSessionName),
          'durationSeconds (request.duration.map(d ⇒ Integer.valueOf(d.toSeconds.toInt)).orNull),
          'externalId (request.externalId.orNull),
          'policy (request.policy.orNull),
          'serialNumber (request.mfa.map(_.serialNumber).orNull),
          'tokenCode (request.mfa.map(_.tokenCode).orNull)
        )
      }
    }
  }
}
