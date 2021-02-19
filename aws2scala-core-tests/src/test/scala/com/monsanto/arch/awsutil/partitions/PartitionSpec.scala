package com.monsanto.arch.awsutil.partitions

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class PartitionSpec extends AnyFreeSpec {
  val partitions = Table("partition", Partition.values: _*)

  "a Partition" - {
    "has a toString that matches the ID" in {
      forAll(partitions) { p ⇒
        p.toString shouldBe p.id
      }
    }

    "can be round-tripped via its string representation" in {
      forAll(partitions) { p ⇒
        Partition.unapply(p.id) shouldBe Some(p)
      }
    }
  }
}
