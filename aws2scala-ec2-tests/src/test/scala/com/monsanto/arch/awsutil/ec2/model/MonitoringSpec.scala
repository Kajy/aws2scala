package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.ec2.model.AwsConverters._
import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class MonitoringSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "a Monitoring should" - {
    "be constructible from its AWS equivalent" in {
      forAll { monitoring: Monitoring =>
        Monitoring.fromAws(monitoring.toAws) shouldBe monitoring
      }
    }
  }

  "the Monitoring.State enumeration" - {
    behave like anAwsEnumeration(Monitoring.State)
  }
}
