package com.monsanto.arch.awsutil.sns

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import com.monsanto.arch.awsutil.auth.policy.action.SNSAction
import com.monsanto.arch.awsutil.sns.model._

private[awsutil] final class DefaultAsyncSNSClient(streaming: StreamingSNSClient) extends AsyncSNSClient {
  override def createTopic(name: String)(implicit as: ActorSystem) =
    Source.single(name)
      .via(streaming.topicCreator)
      .runWith(Sink.head)

  override def deleteTopic(arn: String)(implicit as: ActorSystem) =
    Source.single(arn)
      .via(streaming.topicDeleter)
      .runWith(Sink.ignore)

  override def listTopics()(implicit as: ActorSystem) = streaming.topicLister.runWith(Sink.seq)

  override def getTopicAttribute(topicArn: String, name: String)(implicit as: ActorSystem) =
    getTopicAttributes(topicArn).map(_.get(name))(as.dispatcher)

  override def getTopicAttributes(topicArn: String)(implicit as: ActorSystem) =
    Source.single(topicArn)
      .via(streaming.topicAttributesGetter)
      .runWith(Sink.head)

  override def setTopicAttribute(topicArn: String, name: String, value: String)(implicit as: ActorSystem) =
    Source.single(SetTopicAttributesRequest(topicArn, name, value))
      .via(streaming.topicAttributeSetter)
      .runWith(Sink.ignore)

  override def setTopicAttribute(topicArn: String, name: String, value: Option[String])(implicit as: ActorSystem) =
    Source.single(SetTopicAttributesRequest(topicArn, name, value))
      .via(streaming.topicAttributeSetter)
      .runWith(Sink.ignore)

  override def addPermission(topicArn: String, label: String, awsAccountIds: Seq[String], actionNames: Seq[SNSAction])
                            (implicit as: ActorSystem) =
    Source.single(AddPermissionRequest(topicArn, label, awsAccountIds, actionNames))
      .via(streaming.permissionAdder)
      .runWith(Sink.ignore)

  override def removePermission(topicArn: String, label: String)(implicit as: ActorSystem) =
    Source.single(RemovePermissionRequest(topicArn, label))
      .via(streaming.permissionRemover)
      .runWith(Sink.ignore)

  override def listSubscriptions()(implicit as: ActorSystem) =
    Source.single(ListSubscriptionsRequest.allSubscriptions)
      .via(streaming.subscriptionLister)
      .runWith(Sink.seq)

  override def listSubscriptions(topicArn: String)(implicit as: ActorSystem) =
    Source.single(ListSubscriptionsRequest.forTopic(topicArn))
      .via(streaming.subscriptionLister)
      .runWith(Sink.seq)

  override def subscribe(topicArn: String, protocol: String, endpoint: String)(implicit as: ActorSystem) =
    Source.single(SubscribeRequest(topicArn, protocol, endpoint))
      .via(streaming.subscriber)
      .runWith(Sink.head)

  override def confirmSubscription(topicArn: String, token: String)(implicit as: ActorSystem) =
    Source.single(ConfirmSubscriptionRequest(topicArn, token))
      .via(streaming.subscriptionConfirmer)
      .runWith(Sink.head)

  override def confirmSubscription(topicArn: String, token: String, authenticateOnUnsubscribe: Boolean)(implicit as: ActorSystem) =
    Source.single(ConfirmSubscriptionRequest(topicArn, token, Some(authenticateOnUnsubscribe)))
      .via(streaming.subscriptionConfirmer)
      .runWith(Sink.head)

  override def getSubscriptionAttribute(subscriptionArn: String, name: String)(implicit as: ActorSystem) =
    Source.single(subscriptionArn)
      .via(streaming.subscriptionAttributesGetter)
      .map(_.get(name))
      .runWith(Sink.head)

  override def getSubscriptionAttributes(subscriptionArn: String)(implicit as: ActorSystem) =
    Source.single(subscriptionArn)
      .via(streaming.subscriptionAttributesGetter)
      .runWith(Sink.head)

  override def setSubscriptionAttribute(subscriptionArn: String, attributeName: String, attributeValue: String)
                                       (implicit as: ActorSystem) =
    Source.single(SetSubscriptionAttributesRequest(subscriptionArn, attributeName, attributeValue))
      .via(streaming.subscriptionAttributeSetter)
      .runWith(Sink.ignore)

  override def setSubscriptionAttribute(subscriptionArn: String, attributeName: String, attributeValue: Option[String])
                                       (implicit as: ActorSystem) =
    Source.single(SetSubscriptionAttributesRequest(subscriptionArn, attributeName, attributeValue))
      .via(streaming.subscriptionAttributeSetter)
      .runWith(Sink.ignore)

  override def unsubscribe(subscriptionArn: String)(implicit as: ActorSystem) =
    Source.single(subscriptionArn)
      .via(streaming.unsubscriber)
      .runWith(Sink.ignore)

  override def createPlatformApplication(name: String, platform: String, principal: String, credential: String)
                                        (implicit as: ActorSystem) =
    Source.single(CreatePlatformApplicationRequest(name, platform, principal, credential))
      .via(streaming.platformApplicationCreator)
      .runWith(Sink.head)

  override def createPlatformApplication(name: String, platform: String, principal: String, credential: String,
                                         attributes: Map[String, String])
                                        (implicit as: ActorSystem) =
    Source.single(CreatePlatformApplicationRequest(name, platform, principal, credential, attributes))
      .via(streaming.platformApplicationCreator)
      .runWith(Sink.head)

  override def getPlatformApplicationAttributes(arn: String)(implicit as: ActorSystem) =
    Source.single(arn)
      .via(streaming.platformApplicationAttributesGetter)
      .runWith(Sink.head)

  override def getPlatformApplicationAttribute(arn: String, attributeName: String)(implicit as: ActorSystem) =
    getPlatformApplicationAttributes(arn)
      .map(attributes => attributes.get(attributeName))(as.dispatcher)

  override def setPlatformApplicationAttribute(arn: String, attributeName: String, attributeValue: String)
                                              (implicit as: ActorSystem) =
    Source.single(SetPlatformApplicationAttributesRequest(arn, attributeName, attributeValue))
      .via(streaming.platformApplicationAttributesSetter)
      .runWith(Sink.ignore)

  override def setPlatformApplicationAttribute(arn: String, attributeName: String, attributeValue: Option[String])
                                              (implicit as: ActorSystem) =
    Source.single(SetPlatformApplicationAttributesRequest(arn, attributeName, attributeValue))
      .via(streaming.platformApplicationAttributesSetter)
      .runWith(Sink.ignore)

  override def setPlatformApplicationAttributes(arn: String, attributes: Map[String, String])
                                               (implicit as: ActorSystem) =
    Source.single(SetPlatformApplicationAttributesRequest(arn, attributes))
      .via(streaming.platformApplicationAttributesSetter)
      .runWith(Sink.ignore)

  override def deletePlatformApplication(arn: String)(implicit as: ActorSystem) =
    Source.single(arn)
      .via(streaming.platformApplicationDeleter)
      .runWith(Sink.ignore)

  override def listPlatformApplications()(implicit as: ActorSystem) =
    streaming.platformApplicationLister.runWith(Sink.seq)

  override def createPlatformEndpoint(platformApplicationArn: String, token: String)(implicit as: ActorSystem) =
    Source.single(CreatePlatformEndpointRequest(platformApplicationArn, token))
      .via(streaming.platformEndpointCreator)
      .runWith(Sink.head)

  override def createPlatformEndpoint(platformApplicationArn: String, token: String, customUserData: String)
                                     (implicit as: ActorSystem) =
    Source.single(CreatePlatformEndpointRequest(platformApplicationArn, token, customUserData))
      .via(streaming.platformEndpointCreator)
      .runWith(Sink.head)

  override def createPlatformEndpoint(platformApplicationArn: String, token: String, attributes: Map[String, String])
                                     (implicit as: ActorSystem) =
    Source.single(CreatePlatformEndpointRequest(platformApplicationArn, token, attributes))
      .via(streaming.platformEndpointCreator)
      .runWith(Sink.head)

  override def createPlatformEndpoint(platformApplicationArn: String, token: String, customUserData: String,
                                      attributes: Map[String, String])
                                     (implicit as: ActorSystem) =
    Source.single(CreatePlatformEndpointRequest(platformApplicationArn, token, customUserData, attributes))
      .via(streaming.platformEndpointCreator)
      .runWith(Sink.head)

  override def getPlatformEndpointAttribute(platformEndpointArn: String, attributeName: String)(implicit as: ActorSystem) =
    getPlatformEndpointAttributes(platformEndpointArn).map(_.get(attributeName))(as.dispatcher)

  override def getPlatformEndpointAttributes(platformEndpointArn: String)(implicit as: ActorSystem) =
    Source.single(platformEndpointArn)
      .via(streaming.platformEndpointAttributesGetter)
      .runWith(Sink.head)

  override def setPlatformEndpointAttributes(platformEndpointArn: String, attributeName: String, attributeValue: String)
                                            (implicit as: ActorSystem) =
    Source.single(SetPlatformEndpointAttributesRequest(platformEndpointArn, attributeName, attributeValue))
      .via(streaming.platformEndpointAttributesSetter)
      .runWith(Sink.ignore)

  override def setPlatformEndpointAttributes(platformEndpointArn: String, attributeName: String, attributeValue: Option[String])
                                            (implicit as: ActorSystem) =
    Source.single(SetPlatformEndpointAttributesRequest(platformEndpointArn, attributeName, attributeValue))
      .via(streaming.platformEndpointAttributesSetter)
      .runWith(Sink.ignore)

  override def setPlatformEndpointAttributes(platformEndpointArn: String, attributes: Map[String, String])
                                            (implicit as: ActorSystem) =
    Source.single(SetPlatformEndpointAttributesRequest(platformEndpointArn, attributes))
      .via(streaming.platformEndpointAttributesSetter)
      .runWith(Sink.ignore)

  override def deletePlatformEndpoint(platformEndpointArn: String)(implicit as: ActorSystem) =
    Source.single(platformEndpointArn)
      .via(streaming.platformEndpointDeleter)
      .runWith(Sink.ignore)

  override def listPlatformEndpoints(platformApplicationArn: String)(implicit as: ActorSystem) =
    Source.single(platformApplicationArn)
      .via(streaming.platformEndpointLister)
      .runWith(Sink.seq)

  override def publish(targetArn: String, message: String)(implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: String, subject: String)(implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message, subject))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: String, attributes: Map[String, MessageAttributeValue])
                      (implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message, attributes))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: String, subject: String,
                       attributes: Map[String, MessageAttributeValue])
                      (implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message, subject, attributes))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: Map[String, String])
                      (implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: Map[String, String], subject: String)
                      (implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message, subject))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: Map[String, String],
                       attributes: Map[String, MessageAttributeValue])
                      (implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message, attributes))
      .via(streaming.publisher)
      .runWith(Sink.head)

  override def publish(targetArn: String, message: Map[String, String], subject: String,
                       attributes: Map[String, MessageAttributeValue])
                      (implicit as: ActorSystem) =
    Source.single(PublishRequest(targetArn, message, subject, attributes))
      .via(streaming.publisher)
      .runWith(Sink.head)
}
