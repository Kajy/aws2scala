package com.monsanto.arch.awsutil.identitymanagement.model

import com.amazonaws.services.identitymanagement.{model ⇒ aws}
import com.monsanto.arch.awsutil.converters.IamConverters._
import com.monsanto.arch.awsutil.testkit.IamScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class UserSpec extends AnyFreeSpec {
  "a User" - {
    "can be round-tripped" - {
      "from its AWS equivalent" in {
        forAll { user: User ⇒
          val awsUser =
            new aws.User(user.path.pathString, user.name, user.id, user.arn.arnString, user.created)
          user.passwordLastUsed.foreach(d ⇒ awsUser.setPasswordLastUsed(d))

          awsUser.asScala.asAws shouldBe awsUser
        }
      }

      "via its AWS equivalent" in {
        forAll { user: User ⇒
          user.asAws.asScala shouldBe user
        }
      }
    }

    "exposes the account from the ARN" in {
      forAll { user: User ⇒
        user.account shouldBe user.arn.account
      }
    }
  }
}
