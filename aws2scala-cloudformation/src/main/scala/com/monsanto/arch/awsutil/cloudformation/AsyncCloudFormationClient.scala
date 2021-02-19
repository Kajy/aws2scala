package com.monsanto.arch.awsutil.cloudformation

import java.net.URL

import akka.Done
import akka.actor.ActorSystem
import com.amazonaws.services.cloudformation.model._
import com.monsanto.arch.awsutil.AsyncAwsClient
import com.monsanto.arch.awsutil.cloudformation.AsyncCloudFormationClient._
import com.monsanto.arch.awsutil.cloudformation.model.ValidatedTemplate

import scala.collection.JavaConverters
import scala.concurrent.Future

trait AsyncCloudFormationClient extends AsyncAwsClient {
  /** Requests creation of a new stack using the given request.  Since a stack creation request is non-trivial, there
    * is no shorthand for generating the request.  However, you can use the [[AsyncCloudFormationClient.Implicits.EnhancedCreateStackRequest]]
    * implicit class to help with converting Scala maps into collections of tags or parameters.
    *
    * @param request an AWS request for creating the stack
    * @return a future with the resulting ID for the new stack
    */
  def createStack(request: CreateStackRequest)(implicit as: ActorSystem): Future[String]

  /** Lists all of the stacks that match the given filter.
    *
    * @param statuses only stacks with the given statuses are listed
    * @param toStackStatus converts arguments to [[com.amazonaws.services.cloudformation.model.StackStatus StackStatus]] instances
    * @return a future containing vector of the summaries of all stacks with the given statuses
    */
  def listStacks[T](statuses: Seq[T] = Seq.empty[StackStatus])
                   (implicit toStackStatus: StackStatusConvertible[T], as: ActorSystem): Future[Seq[StackSummary]]

  /** Describes all of the stacks. */
  def describeStacks()(implicit as: ActorSystem): Future[Seq[Stack]]

  /** Describes the stack with the matching name or ID.  Note that describing a non-existent stack will result in an
    * error.
    */
  def describeStack(stackNameOrID: String)(implicit as: ActorSystem): Future[Stack]

  /** Returns all of the events for the given stack. */
  def describeStackEvents(stackNameOrID: String)(implicit as: ActorSystem): Future[Seq[StackEvent]]

  /** Requests deletion of the stack with the given name or ID.
    *
    * @param stackNameOrID the name or ID of stack to delete
    */
  def deleteStack(stackNameOrID: String)(implicit as: ActorSystem): Future[Done]

  /** Requests deletion of the stack with the given name or ID.
    *
    * @param stackNameOrID the name or ID of stack to delete
    * @param retainResources For stacks in the `DELETE_FAILED` state, a list of resource logical IDs that are associated
    *                        with the resources you want to retain.  During deletion, AWS CloudFormation deletes the
    *                        stack but does not delete the retained resources.
    */
  def deleteStack(stackNameOrID: String, retainResources: Seq[String])(implicit as: ActorSystem): Future[Done]

  /** Validates the given template body. */
  def validateTemplateBody(body: String)(implicit as: ActorSystem): Future[ValidatedTemplate]

  /** Validates the template at the given S3 bucket URL. */
  def validateTemplateURL(url: URL)(implicit as: ActorSystem): Future[ValidatedTemplate]

  /** Returns a summary of all of the resources for the given stack. */
  def listStackResources(stackNameOrID: String)(implicit as: ActorSystem): Future[Seq[StackResourceSummary]]
}

object AsyncCloudFormationClient {
  /** Type class for converting an object to a [[com.amazonaws.services.cloudformation.model.StackStatus StackStatus]]
    * instance.
    */
  trait StackStatusConvertible[A] extends (A => StackStatus)

  /** Contains implementations. */
  object StackStatusConvertible {
    implicit val stackStatusIdentity = new StackStatusConvertible[StackStatus] {
      override def apply(stackStatus: StackStatus) = stackStatus
    }
    implicit val stringToStackStatus = new StackStatusConvertible[String] {
      override def apply(value: String) = StackStatus.fromValue(value)
    }
  }

  object Implicits {
    implicit class EnhancedCreateStackRequest(val request: CreateStackRequest) extends AnyVal {
      import JavaConverters._

      def withTags(tags: Map[String,String]): CreateStackRequest = {
        val awsTags = tags.map(t => new Tag().withKey(t._1).withValue(t._2))
        request.withTags(awsTags.asJavaCollection)
      }

      def withParameters(parameters: Map[String, String]): CreateStackRequest = {
        val awsParameters = parameters.map(p => new Parameter().withParameterKey(p._1).withParameterValue(p._2))
        request.withParameters(awsParameters.asJavaCollection)
      }
    }

    implicit class EnhancedStack(val stack: Stack) extends AnyVal {
      def stackStatus: StackStatus = StackStatusConvertible.stringToStackStatus(stack.getStackStatus)
    }
  }
}
