package com.monsanto.arch.awsutil.identitymanagement.model

import com.amazonaws.services.identitymanagement.{model => aws}
import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class RoleSpec extends AnyFreeSpec {
  "a Role can be round-tripped" - {
    "from its AWS equivalent" in {
      forAll { role: Role =>
        val awsRole = new aws.Role()
          .withArn(role.arn.arnString)
          .withRoleName(role.name)
          .withRoleId(role.id)
          .withPath(role.path.pathString)
          .withAssumeRolePolicyDocument(role.assumeRolePolicyDocument.toJson)
          .withCreateDate(role.created)
        awsRole.asScala.asAws shouldBe awsRole
      }
    }

    "via its AWS equivalent" in {
      forAll { role: Role =>
        role.asAws.asScala shouldBe role
      }
    }
  }
}
