package com.monsanto.arch.awsutil.ec2.model

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._

import scala.jdk.CollectionConverters._

class FilterSpec extends AnyFreeSpec {
  "a Filter instance should" - {
    "create valid AWS filters" in {
      forAll { filter: Filter =>
        filter.toAws should have (
          Symbol("name") (filter.name),
          Symbol("values") (filter.values.asJava)
        )
      }
    }
  }

  "the Filter object should" - {
    "create a sequence of filters from a map" in {
      forAll { filters: Seq[Filter] =>
        val map = filters.map(filter => filter.name ->filter.values) .toMap
        val result = Filter.fromMap(map)
        result should contain theSameElementsAs filters
      }
    }
  }
}
