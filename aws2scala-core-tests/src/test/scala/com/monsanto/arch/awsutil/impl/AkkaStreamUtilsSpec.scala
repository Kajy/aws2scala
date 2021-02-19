package com.monsanto.arch.awsutil.impl

import akka.stream.scaladsl.{Sink, Source}
import com.monsanto.arch.awsutil.impl.AkkaStreamUtils.Implicits._
import com.monsanto.arch.awsutil.test_support.AdaptableScalaFutures._
import com.monsanto.arch.awsutil.test_support.Materialised
import org.scalacheck.Gen
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class AkkaStreamUtilsSpec extends AnyFreeSpec with Materialised {
  "an EnhancesSinkObject" - {
    "provides a count sink" in {
      forAll(Gen.listOf(Gen.choose(0, 100))) { nums =>
        val count = Source(nums).runWith(Sink.count).futureValue()
        count shouldBe nums.size
      }
    }
  }
}
