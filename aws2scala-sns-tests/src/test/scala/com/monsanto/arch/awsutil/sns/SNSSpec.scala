package com.monsanto.arch.awsutil.sns

import com.monsanto.arch.awsutil.auth.policy.action.SNSAction
import com.monsanto.arch.awsutil.converters.CoreConverters._
import com.monsanto.arch.awsutil.test_support.AwsClientProviderBehaviours
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class SNSSpec extends AnyFreeSpec with MockFactory with AwsClientProviderBehaviours {
  val actions = Table("SNSAction", SNSAction.values: _*)

  "the SNS provider should" - {
    behave like anAwsClientProvider(SNS)

    "register the SNS actions" in {
      forAll(actions) { action =>
        action.asAws.asScala shouldBe action
      }
    }
  }
}
