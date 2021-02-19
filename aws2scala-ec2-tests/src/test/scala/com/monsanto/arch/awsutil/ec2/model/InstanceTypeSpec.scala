package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class InstanceTypeSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the InstanceType enumeration" - {
    behave like anAwsEnumeration(InstanceType)
  }
}
