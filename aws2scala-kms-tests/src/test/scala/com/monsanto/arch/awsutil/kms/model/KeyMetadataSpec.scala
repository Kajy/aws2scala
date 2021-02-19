package com.monsanto.arch.awsutil.kms.model

import com.monsanto.arch.awsutil.converters.KmsConverters._
import com.monsanto.arch.awsutil.kms.KMS
import com.monsanto.arch.awsutil.testkit.KmsScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class KeyMetadataSpec extends AnyFreeSpec {
  // ensure that key ARNs will parse
  KMS.init()

  "a KeyMetadata should" - {
    "convert to the correct AWS value" in {
      forAll { metadata: KeyMetadata =>
        metadata.asAws.getEnabled shouldBe metadata.enabled
        metadata.asAws should have(
          Symbol("KeyId") (metadata.id),
          Symbol("Arn") (metadata.arn.arnString),
          Symbol("AWSAccountId") (metadata.account.id),
          Symbol("CreationDate") (metadata.creationDate),
          Symbol("DeletionDate") (metadata.deletionDate.orNull),
          // the enabled field isn't detected for some reason
          //'Enabled (java.lang.Boolean.valueOf(metadata.enabled)),
          Symbol("KeyState") (metadata.state.name),
          Symbol("KeyUsage") (metadata.usage.name)
        )
      }
    }

    "round trip through its AWS equivalent" in {
      forAll { metadata: KeyMetadata =>
        metadata.asAws.asScala shouldBe metadata
      }
    }
  }
}
