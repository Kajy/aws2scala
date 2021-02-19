package com.monsanto.arch.awsutil.kms.model

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

import scala.jdk.CollectionConverters._


class DecryptRequestSpec extends AnyFreeSpec {
  val ciphertext = Array.fill(32)(0.toByte)

  "an EncryptRequest" - {
    "generates a correct AWS request" - {
      "with no additional args" in {
        val request = DecryptRequest(ciphertext).toAws

        request.getCiphertextBlob.array shouldBe ciphertext
        request.getEncryptionContext shouldBe empty
        request.getGrantTokens shouldBe empty
      }

      "with a context" in {
        val context = Map("some" ->"context")
        val request = DecryptRequest(ciphertext, context).toAws

        request.getCiphertextBlob.array shouldBe ciphertext
        request.getEncryptionContext.asScala shouldBe context
        request.getGrantTokens shouldBe empty
      }

      "with grant tokens" in {
        val grantTokens = Seq("a", "b", "c")

        val request = DecryptRequest(ciphertext, grantTokens = grantTokens).toAws

        request.getCiphertextBlob.array shouldBe ciphertext
        request.getEncryptionContext shouldBe empty
        request.getGrantTokens.asScala shouldBe grantTokens
      }
    }
  }
}
