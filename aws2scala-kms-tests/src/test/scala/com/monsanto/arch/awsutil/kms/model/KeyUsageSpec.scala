package com.monsanto.arch.awsutil.kms.model

import com.amazonaws.services.kms.model.KeyUsageType
import com.monsanto.arch.awsutil.converters.KmsConverters._
import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks.{Table, forAll}

class KeyUsageSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "a KeyUsage should" - {
    val keyUsages = Table("key usage", KeyUsage.values: _*)

    "have an ID that matches AWS string enumeration name" in {
      forAll(keyUsages) { keyUsage ⇒
        keyUsage.name shouldBe keyUsage.asAws.name()
      }
    }

    "be buildable from an identifier string" in {
      forAll(keyUsages) { keyUsage ⇒
        KeyUsage.fromName(keyUsage.name) shouldBe keyUsage
      }
    }

    "not be buildable from an invalid identifier string" in {
      an [IllegalArgumentException] shouldBe thrownBy {
        KeyUsage.fromName("foo")
      }
    }
  }

  behave like anAwsEnumeration(
    KeyUsageType.values(),
    KeyUsage.values,
    (_: KeyUsage).asAws,
    (_: KeyUsageType).asScala)
}
