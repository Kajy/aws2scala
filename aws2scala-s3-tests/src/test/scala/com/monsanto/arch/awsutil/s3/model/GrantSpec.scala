package com.monsanto.arch.awsutil.s3.model

import com.monsanto.arch.awsutil.s3.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.S3ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class GrantSpec extends AnyFreeSpec {
  "a Grant can be round-tripped via its AWS equivalent" in {
    forAll { grant: Grant ⇒
      grant.asAws.asScala shouldBe grant
    }
  }
}
