package com.monsanto.arch.awsutil.rds

import akka.actor.ActorSystem
import com.amazonaws.services.rds.model.{CreateDBInstanceRequest, DBInstance, Tag}
import com.monsanto.arch.awsutil.AsyncAwsClient

import scala.jdk.CollectionConverters._
import scala.concurrent.Future

/** Future-based client for Amazon’s relational database service.
  *
  * @author Jorge Montero
  */
trait AsyncRDSClient extends AsyncAwsClient {
  /** Requests creation of a a new database. */
  def createDBInstance(request: CreateDBInstanceRequest)(implicit as: ActorSystem): Future[DBInstance]

  /** Requests deletion of the given database without taking a final snapshot. */
  def deleteDBInstance(dbInstanceIdentifier: String)(implicit as: ActorSystem): Future[DBInstance]

  /** Requests deletion of the given database taking a final snapshot with the given identifier. */
  def deleteDBInstance(dbInstanceIdentifier: String, finalDbSnapshotIdentifier: String)
                      (implicit as: ActorSystem): Future[DBInstance]

  /** Retrieves a description of all available RDS instances. */
  def describeDBInstances()(implicit as: ActorSystem): Future[Seq[DBInstance]]

  /** Retrieves a description of the given RDS instance. */
  def describeDBInstance(dbInstanceIdentifier: String)(implicit as: ActorSystem): Future[DBInstance]
}

object AsyncRDSClient {
  object Implicits {
    implicit class EnhancedCreateDBInstanceRequest(val request: CreateDBInstanceRequest) extends AnyVal {
      def withTags(tags: Map[String,String]): CreateDBInstanceRequest = {
        val awsTags = tags.map(t => new Tag().withKey(t._1).withValue(t._2))
        request.withTags(awsTags.asJavaCollection)
      }
    }
  }
}
