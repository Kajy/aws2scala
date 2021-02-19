package com.monsanto.arch.awsutil.s3.model

import com.monsanto.arch.awsutil.s3.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.S3ScalaCheckImplicits._
import com.monsanto.arch.awsutil.testkit.{S3Gen, UtilGen}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class GranteeSpec extends AnyFreeSpec {
  "the Grantee object provides" - {
    "a method for creating" - {
      "canonical grantees" in {
        forAll(S3Gen.canonicalIdentifier) { id =>
          val grantee = Grantee.canonical(id)
          grantee should have (
            Symbol("identifier") (id),
            Symbol("typeIdentifier") ("id")
          )
        }
      }

      "e-mail address grantees" in {
        forAll(UtilGen.emailAddress) { emailAddress =>
          val grantee = Grantee.emailAddress(emailAddress)
          grantee should have (
            Symbol("identifier") (emailAddress),
            Symbol("typeIdentifier") ("emailAddress")
          )
        }
      }
    }

    "a value for" - {
      "all users" in {
        Grantee.allUsers should have (
          Symbol("identifier") ("http://acs.amazonaws.com/groups/global/AllUsers"),
          Symbol("typeIdentifier") ("uri")
        )
      }

      "any authenticated user" in {
        Grantee.authenticatedUsers should have (
          Symbol("identifier") ("http://acs.amazonaws.com/groups/global/AuthenticatedUsers"),
          Symbol("typeIdentifier") ("uri")
        )
      }

      "log delivery" in {
        Grantee.logDelivery should have (
          Symbol("identifier") ("http://acs.amazonaws.com/groups/s3/LogDelivery"),
          Symbol("typeIdentifier") ("uri")
        )
      }
    }
  }

  "a Grantee can be round-tripped via its AWS equivalent" in {
    forAll { grantee: Grantee =>
      grantee.asAws.asScala shouldBe grantee
    }
  }
}
