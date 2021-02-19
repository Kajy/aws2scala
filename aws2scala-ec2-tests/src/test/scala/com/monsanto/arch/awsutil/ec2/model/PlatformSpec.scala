package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec

class PlatformSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Platform enumeration" - {
    behave like anAwsEnumeration(Platform)
  }
}
