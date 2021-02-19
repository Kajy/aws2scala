package com.monsanto.arch.awsutil.s3.model

import akka.actor.ActorSystem
import akka.stream.scaladsl.Sink
import com.amazonaws.services.s3.{model => aws}
import com.monsanto.arch.awsutil.s3.StreamingS3Client
import com.monsanto.arch.awsutil.s3.model.AwsConverters._

import scala.concurrent.Future
import scala.language.implicitConversions

/** Object-oriented interface to Amazon S3.
  *
  * @author Daniel Solano Gómez
  */
object S3 {
  /** Returns the bucket with the given name, if any. */
  def find(name: String)(implicit streamingS3Client: StreamingS3Client, as: ActorSystem): Future[Option[Bucket]] =
    streamingS3Client.bucketLister
      .filter(_.name == name)
      .runWith(Sink.headOption)

  object Implicits {
    implicit def fromAws(awsStorageClass: aws.StorageClass): StorageClass = awsStorageClass match {
      case aws.StorageClass.Glacier                  => StorageClass.Glacier
      case aws.StorageClass.ReducedRedundancy        => StorageClass.ReducedRedundancy
      case aws.StorageClass.Standard                 => StorageClass.Standard
      case aws.StorageClass.StandardInfrequentAccess => StorageClass.StandardInfrequentAccess
    }
    implicit def fromAws(objectSummary: aws.S3ObjectSummary): Object =
      Object(
        objectSummary.getBucketName,
        objectSummary.getKey,
        objectSummary.getETag,
        objectSummary.getLastModified,
        objectSummary.getOwner.asScala,
        objectSummary.getSize,
        aws.StorageClass.fromValue(objectSummary.getStorageClass))
  }
}
