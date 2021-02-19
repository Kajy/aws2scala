package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.ec2.model.AwsConverters._
import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class InstanceStateSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "an InstanceState should" - {
    "be constructible from its AWS equivalent" in {
      forAll { state: InstanceState =>
        InstanceState.fromAws(state.toAws) shouldBe state
      }
    }
  }

  "the InstanceState.Name enumeration" - {
    behave like anAwsEnumeration(InstanceState.Name)
  }
}
