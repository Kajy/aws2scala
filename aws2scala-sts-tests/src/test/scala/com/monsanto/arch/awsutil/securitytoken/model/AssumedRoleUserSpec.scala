package com.monsanto.arch.awsutil.securitytoken.model

import com.monsanto.arch.awsutil.securitytoken.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.StsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AssumedRoleUserSpec extends AnyFreeSpec {
  "a AssumedRoleUser can be round-tripped" - {
    "via its AWS equivalent" in {
      forAll { assumedRoleUser: AssumedRoleUser ⇒
        assumedRoleUser.asAws.asScala shouldBe assumedRoleUser
      }
    }
  }
}
