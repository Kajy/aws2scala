package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.testkit.Ec2Gen
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

import scala.jdk.CollectionConverters._

class DescribeKeyPairsRequestSpec extends AnyFreeSpec {
  "a DescribeKeyPairsRequest should" - {
    "convert to an AWS request" in {
      forAll { request: DescribeKeyPairsRequest =>
        val aws = request.toAws

        aws should have (
          Symbol("keyNames") (request.keyNames.asJava),
          Symbol("filters") (request.filters.map(_.toAws).asJava)
        )
      }
    }
  }

  "the DescribeKeyPairsRequest object provides" - {
    "a handy value for describing all key pairs" in {
      val request = DescribeKeyPairsRequest.allKeyPairs

      request shouldBe DescribeKeyPairsRequest(Seq.empty, Seq.empty)
    }

    "a method for getting a particular key pair with a given name" in {
      forAll(Ec2Gen.keyName) { name =>
        DescribeKeyPairsRequest.withName(name) shouldBe DescribeKeyPairsRequest(Seq(name), Seq.empty)
      }
    }

    "a method for getting all key pairs matching filters specified" - {
      "as a sequence of Filters" in {
        forAll { filters: Seq[Filter] =>
          DescribeKeyPairsRequest.filter(filters) shouldBe DescribeKeyPairsRequest(Seq.empty, filters)
        }
      }

      "as a map" in {
        forAll { filters: Seq[Filter] =>
          val mapFilter = filters.map(f => f.name ->f.values).toMap

          val result = DescribeKeyPairsRequest.filter(mapFilter)
          result.keyNames shouldBe empty
          result.filters should contain theSameElementsAs filters
        }
      }
    }
  }
}
