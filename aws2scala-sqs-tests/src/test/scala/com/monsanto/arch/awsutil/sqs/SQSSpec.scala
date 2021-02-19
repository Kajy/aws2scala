package com.monsanto.arch.awsutil.sqs

import com.monsanto.arch.awsutil.auth.policy.action.SQSAction
import com.monsanto.arch.awsutil.converters.CoreConverters._
import com.monsanto.arch.awsutil.test_support.AwsClientProviderBehaviours
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class SQSSpec extends AnyFreeSpec with MockFactory with AwsClientProviderBehaviours {
  val actions = Table("SQSAction", SQSAction.values: _*)

  "the SNS provider should" - {
    behave like anAwsClientProvider(SQS)

    "register the SQS actions" in {
      forAll(actions) { action =>
        action.asAws.asScala shouldBe action
      }
    }
  }
}
