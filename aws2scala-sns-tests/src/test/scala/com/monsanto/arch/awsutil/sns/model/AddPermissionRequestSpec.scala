package com.monsanto.arch.awsutil.sns.model

import com.monsanto.arch.awsutil.sns.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.SnsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

import scala.jdk.CollectionConverters._

class AddPermissionRequestSpec extends AnyFreeSpec {
  "a AddPermissionRequest should" - {
    "be convertible to its AWS equivalent" in {
      forAll { request: AddPermissionRequest =>
        request.asAws should have (
          Symbol("TopicArn")(request.topicArn),
          Symbol("Label") (request.label),
          Symbol("AWSAccountIds") (request.accounts.asJava),
          Symbol("ActionNames") (request.actions.map(_.toString).asJava)
        )
      }
    }
  }
}
