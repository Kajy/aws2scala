package com.monsanto.arch.awsutil.s3.model

import java.io.File
import java.net.URL
import java.util.Date

import akka.actor.ActorSystem
import com.monsanto.arch.awsutil.s3.{AsyncS3Client, DownloadSink}

import scala.concurrent.Future

/** Represents a handle to an S3 object (contains no actual data). */
case class Object(bucketName: String, key: String, eTag: String, lastModified: Date, owner: Owner, size: Long,
                  storageClass: StorageClass) {
  /** Deletes the object from S3. */
  def delete()(implicit asyncS3Client: AsyncS3Client, as: ActorSystem): Future[Unit] =
    asyncS3Client.deleteObject(bucketName, key).map(_ => ())(as.dispatcher)

  /** Retrieves the content of the object. */
  def download[T: DownloadSink]()(implicit asyncS3Client: AsyncS3Client, as: ActorSystem): Future[T] =
    asyncS3Client.download(bucketName, key)

  /** Retrieves the content of the object and saves it to a file. */
  def downloadTo(file: File)(implicit asyncS3Client: AsyncS3Client, as: ActorSystem): Future[File] =
    asyncS3Client.downloadTo(bucketName, key, file)

  /** Copies this object to another key within the same bucket, returning the new object. */
  def copy(destinationKey: String)(implicit asyncS3Client: AsyncS3Client, as: ActorSystem): Future[Object] =
    copy(bucketName, destinationKey)

  /** Copies this object to another bucket and key, returning the new object. */
  def copy(destinationBucketName: String, destinationKey: String)
          (implicit asyncS3Client: AsyncS3Client, as: ActorSystem): Future[Object] =
    asyncS3Client.copy(bucketName, key, destinationBucketName, destinationKey)
      .map(S3.Implicits.fromAws)(as.dispatcher)

  /** Retrieves the public URL for this object. */
  def getUrl()(implicit asyncS3Client: AsyncS3Client, as: ActorSystem): Future[URL] =
    asyncS3Client.getUrl(bucketName, key)
}
