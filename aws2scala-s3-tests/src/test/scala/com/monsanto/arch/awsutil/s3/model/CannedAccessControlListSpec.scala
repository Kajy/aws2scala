package com.monsanto.arch.awsutil.s3.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class CannedAccessControlListSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the CannedAccessControlListSpec enumeration" - {
    behave like anAwsEnumeration(CannedAccessControlList)
  }
}
