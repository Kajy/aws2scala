package com.monsanto.arch.awsutil.rds

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import com.amazonaws.services.rds.model.{CreateDBInstanceRequest, DBInstance, DescribeDBInstancesRequest}

class DefaultAsyncRDSClient(streamingClient: StreamingRDSClient) extends AsyncRDSClient {
  override def createDBInstance(request: CreateDBInstanceRequest)(implicit as: ActorSystem) =
    Source.single(request)
      .via(streamingClient.dbInstanceCreator)
      .runWith(Sink.head)

  override def deleteDBInstance(dbInstanceIdentifier: String)(implicit as: ActorSystem) =
    Source.single(dbInstanceIdentifier ->None)
      .via(streamingClient.dbInstanceDeleter)
      .runWith(Sink.head)

  override def deleteDBInstance(dbInstanceIdentifier: String, finalDbSnapshotIdentifier: String)
                               (implicit as: ActorSystem) =
    Source.single(dbInstanceIdentifier ->Some(finalDbSnapshotIdentifier))
      .via(streamingClient.dbInstanceDeleter)
      .runWith(Sink.head)

  override def describeDBInstance(dbInstanceIdentifier: String)(implicit as: ActorSystem) =
    Source.single(dbInstanceIdentifier)
      .via(streamingClient.identifiedDbInstanceDescriber)
      .runWith(Sink.head)

  override def describeDBInstances()(implicit as: ActorSystem) =
    Source.single(new DescribeDBInstancesRequest)
      .via(streamingClient.rawDbInstanceDescriber)
      .runWith(Sink.fold(Vector.empty[DBInstance])(_ :+ _))
}
