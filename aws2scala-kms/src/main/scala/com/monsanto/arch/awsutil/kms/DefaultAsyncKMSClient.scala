package com.monsanto.arch.awsutil.kms

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import com.monsanto.arch.awsutil.kms.model._

import scala.concurrent.Future

private[awsutil] class DefaultAsyncKMSClient(streamingClient: StreamingKMSClient) extends AsyncKMSClient {
  override def createKey(alias: String)(implicit as: ActorSystem): Future[KeyMetadata] =
    createKey(CreateKeyWithAliasRequest(alias, None, None, KeyUsage.EncryptDecrypt, None))

  override def createKey(alias: String, description: String)(implicit as: ActorSystem): Future[KeyMetadata] =
    createKey(CreateKeyWithAliasRequest(alias, None, Some(description), KeyUsage.EncryptDecrypt, None))

  override def createKey(createKeyRequest: CreateKeyWithAliasRequest)(implicit as: ActorSystem): Future[KeyMetadata] =
    Source.single(createKeyRequest)
      .via(streamingClient.keyWithAliasCreator)
      .runWith(Sink.head)

  override def scheduleKeyDeletion(keyId: String, pendingWindowDays: Int)(implicit as: ActorSystem) =
    Source.single((keyId, pendingWindowDays))
      .via(streamingClient.keyDeletionScheduler)
      .runWith(Sink.head)

  override def cancelKeyDeletion(keyId: String)(implicit as: ActorSystem) =
    Source.single(keyId)
      .via(streamingClient.keyDeletionCanceller)
      .runWith(Sink.ignore)

  override def enableKey(id: String)(implicit as: ActorSystem) =
    Source.single(id)
      .via(streamingClient.keyEnabler)
      .runWith(Sink.ignore)

  override def disableKey(id: String)(implicit as: ActorSystem) =
    Source.single(id)
      .via(streamingClient.keyDisabler)
      .runWith(Sink.ignore)

  override def describeKey(idOrAlis: String)(implicit as: ActorSystem) =
    Source.single(idOrAlis)
      .via(streamingClient.keyDescriber)
      .runWith(Sink.head)

  override def listKeys()(implicit as: ActorSystem) = streamingClient.lister.runWith(Sink.seq)

  override def generateDataKey(idOrAlias: String)(implicit as: ActorSystem) =
    generateDataKey(GenerateDataKeyRequest(idOrAlias))

  override def generateDataKey(idOrAlias: String, context: Map[String, String])(implicit as: ActorSystem) =
    generateDataKey(GenerateDataKeyRequest(idOrAlias, context))

  override def generateDataKey(request: GenerateDataKeyRequest)(implicit as: ActorSystem) =
    Source.single(request)
      .via(streamingClient.dataKeyGenerator)
      .runWith(Sink.head)

  override def encrypt(idOrAlias: String, plaintext: Array[Byte])(implicit as: ActorSystem) =
    encrypt(EncryptRequest(idOrAlias, plaintext))

  override def encrypt(idOrAlias: String, plaintext: Array[Byte], context: Map[String, String])
                      (implicit as: ActorSystem) =
    encrypt(EncryptRequest(idOrAlias, plaintext, context))

  override def encrypt(request: EncryptRequest)(implicit as: ActorSystem) =
    Source.single(request)
      .via(streamingClient.encryptor)
      .runWith(Sink.head)

  override def decrypt(ciphertext: Array[Byte])(implicit as: ActorSystem) = decrypt(DecryptRequest(ciphertext))

  override def decrypt(ciphertext: Array[Byte], context: Map[String, String])(implicit as: ActorSystem) =
    decrypt(DecryptRequest(ciphertext, context))

  override def decrypt(request: DecryptRequest)(implicit as: ActorSystem) =
    Source.single(request)
      .via(streamingClient.decryptor)
      .runWith(Sink.head)
}
