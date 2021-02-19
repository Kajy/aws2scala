package com.monsanto.arch.awsutil.sns.model

import com.monsanto.arch.awsutil.sns.model.AwsConverters._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class MessageAttributeValueSpec extends AnyFreeSpec {
  "a MessageAttributeValue" - {
    "can be built from" - {
      "strings" in {
        forAll { value: String ⇒
          val result = MessageAttributeValue(value)
          result should matchPattern { case MessageAttributeValue.StringValue(`value`) ⇒ }
        }
      }

      "byte arrays" in {
        forAll { value: Array[Byte] ⇒
          val result = MessageAttributeValue(value)
          result should matchPattern { case MessageAttributeValue.BinaryValue(`value`) ⇒ }
        }
      }
    }
  }

  "the MessageAttributeValue.AwsAdapter type class supports" - {
    "strings" in {
      forAll { value: String ⇒
        val result = MessageAttributeValue(value).asAws
        result.getDataType shouldBe "String"
        result.getBinaryValue shouldBe null
        result.getStringValue shouldBe value
      }
    }

    "byte arrays" in {
      forAll { value: Array[Byte] ⇒
        val result = MessageAttributeValue(value).asAws
        result.getDataType shouldBe "Binary"
        result.getBinaryValue.array() shouldBe value
        result.getStringValue shouldBe null
      }
    }
  }
}
