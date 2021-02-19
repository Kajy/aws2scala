package com.monsanto.arch.awsutil.sns.model

import akka.Done
import com.monsanto.arch.awsutil.sns.StreamingSNSClient
import com.monsanto.arch.awsutil.test_support.AdaptableScalaFutures._
import com.monsanto.arch.awsutil.test_support.{FlowMockUtils, Materialised}
import com.monsanto.arch.awsutil.testkit.SnsScalaCheckImplicits._
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._
import spray.json._

class SubscriptionSpec extends AnyFreeSpec with Materialised with MockFactory with FlowMockUtils {
  private implicit val generatorDrivenConfig = PropertyCheckConfiguration(minSuccessful = 50, sizeRange = 50)

  "the Subscription companion object" - {
    "can build a subscription using" - {
      "a set of attributes" in {
        forAll { attributes: SubscriptionAttributes =>
          val subscription = Subscription(attributes.asMap)

          subscription should have (
            Symbol("arn") (attributes.arn.arnString),
            Symbol("confirmationWasAuthenticated") (attributes.confirmationWasAuthenticated),
            Symbol("deliveryPolicy") (attributes.deliveryPolicy.map(_.toJson.compactPrint)),
            Symbol("effectiveDeliveryPolicy") (attributes.effectiveDeliveryPolicy.map(_.toJson.compactPrint)),
            Symbol("endpoint") (attributes.endpoint),
            Symbol("owner") (attributes.arn.account.id),
            Symbol("topicArn") (attributes.arn.arnString.replaceAll(":[^:]+$", ""))
          )
        }
      }

      "a subscription ARN" in {
        forAll { subscription: Subscription =>
          implicit val sns = mock[StreamingSNSClient]("sns")

          (() => sns.subscriptionAttributesGetter)
            .expects()
            .returningFlow(subscription.arn, subscription.attributes)

          val result = Subscription(subscription.arn).futureValue()
          result shouldBe subscription
        }
      }
    }
  }

  "a Subscription instance should" - {
    "refresh" in {
      forAll { (subscription: Subscription, newRawMessageDelivery: Boolean, newDeliveryPolicy: Option[SubscriptionDeliveryPolicy]) =>
        implicit val sns = mock[StreamingSNSClient]("sns")

        val newAttributes = newDeliveryPolicy match {
          case None =>
            subscription.attributes +
              ("RawMessageDelivery" ->newRawMessageDelivery.toString) -
              "DeliveryPolicy" -
              "EffectiveDeliveryPolicy"
          case Some(p) =>
            val json = p.toJson.compactPrint
            subscription.attributes +
              ("RawMessageDelivery" ->newRawMessageDelivery.toString) +
              ("DeliveryPolicy" ->json) +
              ("EffectiveDeliveryPolicy" ->json)
        }
        val newSubscription = Subscription(newAttributes)

        (() => sns.subscriptionAttributesGetter)
          .expects()
          .returningFlow(subscription.arn, newAttributes)

        val result = subscription.refresh().futureValue()
        result shouldBe newSubscription
      }
    }

    "have a toString that is mainly the ARN" in {
      forAll { subscription: Subscription =>
        subscription.toString shouldBe s"Subscription(${subscription.arn})"
      }
    }

    "allow setting the raw message delivery attribute" in {
      forAll { (subscription: Subscription, rawMessageDelivery: Boolean) =>
        implicit val sns = mock[StreamingSNSClient]("sns")

        (() => sns.subscriptionAttributeSetter)
          .expects()
          .returningFlow(
            SetSubscriptionAttributesRequest(subscription.arn, "RawMessageDelivery", rawMessageDelivery.toString),
            subscription.arn)

        val result = subscription.setRawMessageDelivery(rawMessageDelivery).futureValue()
        result shouldBe Done
      }
    }

    "allow setting the delivery policy attribute" in {
      forAll { (subscription: Subscription, deliveryPolicy: Option[SubscriptionDeliveryPolicy]) =>
        implicit val sns = mock[StreamingSNSClient]("sns")
        val maybeDeliveryPolicy = deliveryPolicy.map(_.toJson.compactPrint)

        (() => sns.subscriptionAttributeSetter)
          .expects()
          .returningFlow(
            SetSubscriptionAttributesRequest(subscription.arn, "DeliveryPolicy", maybeDeliveryPolicy),
            subscription.arn)

        val result = subscription.setDeliveryPolicy(maybeDeliveryPolicy).futureValue()
        result shouldBe Done
      }
    }

    "delete itself" in {
      forAll { subscription: Subscription =>
        implicit val sns = mock[StreamingSNSClient]("sns")

        (() => sns.unsubscriber)
          .expects()
          .returningFlow(subscription.arn, subscription.arn)

        val result = subscription.unsubscribe().futureValue()
        result shouldBe Done
      }
    }
  }
}
