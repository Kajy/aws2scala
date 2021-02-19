package com.monsanto.arch.awsutil.ec2.model

import com.amazonaws.services.ec2.{model ⇒ aws}
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class GroupIdentifierSpec extends AnyFreeSpec {
  "a GroupIdentifier should" - {
    "be constructible from its AWS equivalent" in {
      forAll { groupId: GroupIdentifier ⇒
        val awsId = new aws.GroupIdentifier()
          .withGroupId(groupId.id)
          .withGroupName(groupId.name)
        GroupIdentifier.fromAws(awsId) shouldBe groupId
      }
    }
  }
}
