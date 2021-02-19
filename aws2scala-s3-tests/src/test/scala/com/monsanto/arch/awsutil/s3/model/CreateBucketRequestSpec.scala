package com.monsanto.arch.awsutil.s3.model

import com.monsanto.arch.awsutil.s3.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.S3Gen
import com.monsanto.arch.awsutil.testkit.S3ScalaCheckImplicits._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class CreateBucketRequestSpec extends AnyFreeSpec {
  "a CreateBucketRequest" - {
    "can be created" - {
      "with a bucket name" in {
        forAll(S3Gen.bucketName) { name =>
          CreateBucketRequest(name) shouldBe CreateBucketRequest.CreateBucketWithNoAcl(name, None)
        }
      }

      "with a bucket name and region" in {
        forAll(S3Gen.bucketName, arbitrary[Region]) { (name, region) =>
          CreateBucketRequest(name, region) shouldBe CreateBucketRequest.CreateBucketWithNoAcl(name, Some(region))
        }
      }

      "with a bucket name and canned ACL" in {
        forAll(S3Gen.bucketName, arbitrary[CannedAccessControlList]) { (name, cannedAcl) =>
          CreateBucketRequest(name, cannedAcl) shouldBe
            CreateBucketRequest.CreateBucketWithCannedAcl(name, cannedAcl, None)
        }
      }

      "with a bucket name, canned ACL, and region" in {
        forAll(S3Gen.bucketName, arbitrary[CannedAccessControlList], arbitrary[Region]) { (name, cannedAcl, region) =>
          CreateBucketRequest(name, cannedAcl, region) shouldBe
            CreateBucketRequest.CreateBucketWithCannedAcl(name, cannedAcl, Some(region))
        }
      }

      "with a bucket name and list of grants" in {
        forAll(S3Gen.bucketName, arbitrary[Seq[Grant]]) { (name, grants) =>
          CreateBucketRequest(name, grants) shouldBe
            CreateBucketRequest.CreateBucketWithGrants(name, grants, None)
        }
      }

      "with a bucket name, list of grants, and region" in {
        forAll(S3Gen.bucketName, arbitrary[Seq[Grant]], arbitrary[Region]) { (name, grants, region) =>
          CreateBucketRequest(name, grants, region) shouldBe
            CreateBucketRequest.CreateBucketWithGrants(name, grants, Some(region))
        }
      }
    }

    "converts to the correct AWS object" in {
      forAll { request: CreateBucketRequest =>
        val (maybeCannedAcl, maybeAccessControlList) = request.acl match {
          case None                  => (None, None)
          case Some(Left(cannedAcl)) => (Some(cannedAcl.toAws), None)
          case Some(Right(grants))   => (None, Some(grants.asAws))
        }
        request.asAws should have (
          Symbol("AccessControlList") (maybeAccessControlList.orNull),
          Symbol("BucketName") (request.bucketName),
          Symbol("CannedAcl") (maybeCannedAcl.orNull),
          Symbol("Region") (request.region.map(_.toString).orNull)
        )
      }
    }
  }
}
