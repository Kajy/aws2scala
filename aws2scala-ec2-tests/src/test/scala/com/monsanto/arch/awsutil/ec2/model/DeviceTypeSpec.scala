package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class DeviceTypeSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the DeviceType enumeration" - {
    behave like anAwsEnumeration(DeviceType)
  }
}
