package com.monsanto.arch.awsutil.kms

import java.util.Date

import akka.Done
import akka.actor.ActorSystem
import com.monsanto.arch.awsutil.AsyncAwsClient
import com.monsanto.arch.awsutil.kms.model._

import scala.concurrent.Future

/** Future-based client for Amazon’s key management service.
  *
  * @author Daniel Solano Gómez
  */
trait AsyncKMSClient extends AsyncAwsClient {
  /** Creates a new KMS key and returns a future with the resulting key metadata. */
  def createKey(alias: String)(implicit as: ActorSystem): Future[KeyMetadata]

  /** Creates a new KMS key and returns a future with the resulting key metadata. */
  def createKey(alias: String, description: String)(implicit as: ActorSystem): Future[KeyMetadata]

  /** Creates a new KMS key and returns a future with the resulting key metadata. */
  def createKey(createKeyRequest: CreateKeyWithAliasRequest)(implicit as: ActorSystem): Future[KeyMetadata]

  /** Requests deletion of the given key within the given number of days.
    *
    * @param id the key to delete
    * @param pendingWindowDays the number of days that Amazon will wait before deleting the key
    * @return the date and time when Amazon will delete the key
    */
  def scheduleKeyDeletion(id: String, pendingWindowDays: Int = 30)(implicit as: ActorSystem): Future[Date]

  /** Cancels deletion of the given key. */
  def cancelKeyDeletion(id: String)(implicit as: ActorSystem): Future[Done]

  /** Enables the given key. */
  def enableKey(id: String)(implicit as: ActorSystem): Future[Done]

  /** Disables the given key. */
  def disableKey(id: String)(implicit as: ActorSystem): Future[Done]

  /** Given a key identifier or alias, describes the key.  If the input does not parse as a UUID, start with `arn:`,
    *  or start with `alias/`, then it will be prepended with `alias/`.
    */
  def describeKey(idOrAlis: String)(implicit as: ActorSystem): Future[Option[KeyMetadata]]

  /** Returns a listing of all available keys, including their aliases. */
  def listKeys()(implicit as: ActorSystem): Future[Seq[ListEntry]]

  /** Generates a data key using the given key.  Note that this method uses AES-256 and does not return the generated
    * key in plaintext.
    */
  def generateDataKey(idOrAlias: String)(implicit as: ActorSystem): Future[DataKey]

  /** Generates a data key using the given key and encryption context.  Note that this method uses AES-256 and does
    * not return the generated key in plaintext.
    */
  def generateDataKey(idOrAlias: String, context: Map[String,String])(implicit as: ActorSystem): Future[DataKey]

  /** Generates a data key specified by the given request. */
  def generateDataKey(request: GenerateDataKeyRequest)(implicit as: ActorSystem): Future[DataKey]

  /** Encrypts the given plaintext with the given master key. */
  def encrypt(idOrAlias: String, plaintext: Array[Byte])(implicit as: ActorSystem): Future[Array[Byte]]

  /** Encrypts the given plaintext with the given master key and encryption context. */
  def encrypt(idOrAlias: String, plaintext: Array[Byte], context: Map[String,String])
             (implicit as: ActorSystem): Future[Array[Byte]]

  /** Encrypts the plaintext contained within the request using the specified parameters. */
  def encrypt(request: EncryptRequest)(implicit as: ActorSystem): Future[Array[Byte]]

  /** Decrypts the given ciphertext. */
  def decrypt(ciphertext: Array[Byte])(implicit as: ActorSystem): Future[Array[Byte]]

  /** Decrypts the given ciphertext using the given encryption context. */
  def decrypt(ciphertext: Array[Byte], context: Map[String,String])
             (implicit as: ActorSystem): Future[Array[Byte]]

  /** Decrypts the ciphertext contained within the request using the specified parameters. */
  def decrypt(request: DecryptRequest)(implicit as: ActorSystem): Future[Array[Byte]]
}
