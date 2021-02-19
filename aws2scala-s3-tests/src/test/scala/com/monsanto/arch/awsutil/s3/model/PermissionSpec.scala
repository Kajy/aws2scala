package com.monsanto.arch.awsutil.s3.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class PermissionSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Permission enumeration" - {
    behave like anAwsEnumeration(Permission)
  }
}
