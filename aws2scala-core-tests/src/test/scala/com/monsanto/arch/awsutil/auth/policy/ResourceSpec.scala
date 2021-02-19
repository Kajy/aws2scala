package com.monsanto.arch.awsutil.auth.policy

import com.monsanto.arch.awsutil.converters.CoreConverters._
import com.monsanto.arch.awsutil.testkit.CoreScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class ResourceSpec extends AnyFreeSpec {
  "a Resource" - {
    "can be round-tripped via its AWS equivalent" in {
      forAll { resource: Resource ⇒
        resource.asAws.asScala shouldBe resource
      }
    }

    "* will always be AllResources" in {
      Resource("*").asAws.asScala shouldBe theSameInstanceAs (Resource.AllResources)
    }
  }
}
