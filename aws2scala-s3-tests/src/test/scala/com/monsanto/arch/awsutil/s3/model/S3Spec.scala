package com.monsanto.arch.awsutil.s3.model

import java.util.{Date, UUID}

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import com.amazonaws.services.s3.{model ⇒ aws}
import com.monsanto.arch.awsutil.s3.{AsyncS3Client, StreamingS3Client}
import com.monsanto.arch.awsutil.test_support.AdaptableScalaFutures._
import com.monsanto.arch.awsutil.test_support.Materialised
import org.scalamock.scalatest.MockFactory
import org.scalatest.FreeSpec
import org.scalatest.Matchers._

import scala.concurrent.Future

class S3Spec extends FreeSpec with MockFactory with Materialised {
  private val who = System.getProperty("user.name")

  "the S3 object" - {
    "can list buckets" in {
      val awsBuckets = makeAwsBuckets()
      val buckets = awsBuckets.map(S3.Implicits.fromAws)
      implicit val client = mock[AsyncS3Client]
      (client.listBuckets()(_: Materializer)).expects(materialiser).returning(Future.successful(awsBuckets))
      val result = S3.list().futureValue
      result shouldBe buckets
    }

    "can create a bucket" in {
      implicit val client = mock[AsyncS3Client]
      val name = "some-name"
      val now = new Date
      val awsBucket = {
        val b = new aws.Bucket(name)
        b.setOwner(new aws.Owner(who, who))
        b.setCreationDate(now)
        b
      }
      (client.createBucket(_: String)(_: Materializer)).expects(name, materialiser).returning(Future.successful(awsBucket))
      val result = S3.create(name).futureValue
      result shouldBe Bucket(name, Owner(who, who), now)
    }

    "can check if a bucket exists" in {
      implicit val client = mock[AsyncS3Client]
      val name = "some-name"

      (client.doesBucketExist(_: String)(_: Materializer)).expects(name, materialiser).returning(Future.successful(true))
      S3.exists(name).futureValue shouldBe true
    }

    "can look for a bucket" - {
      "and find it" in {
        val awsBuckets = makeAwsBuckets()
        val buckets = awsBuckets.map(S3.Implicits.fromAws)
        val theBucket = buckets(12)

        implicit val client = mock[StreamingS3Client]
        (client.bucketLister _).expects().returning(Source(awsBuckets.toList))

        S3.find(theBucket.name).futureValue shouldBe Some(theBucket)
      }

      "and indicate it does not exist" in {
        val awsBuckets = makeAwsBuckets()
        implicit val client = mock[StreamingS3Client]
        (client.bucketLister _).expects().returning(Source(awsBuckets.toList))
        S3.find("not-found").futureValue shouldBe None
      }
    }
  }

  private def makeAwsBuckets() =
    Seq.fill(20) {
      val awsBucket = new aws.Bucket(s"test-bucket-${UUID.randomUUID()}")
      awsBucket.setOwner(new aws.Owner(who, who))
      awsBucket.setCreationDate(new Date())
      awsBucket
    }
}