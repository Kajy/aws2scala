package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class NetworkInterfaceStatusSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the NetworkInterfaceStatus enumeration" - {
    behave like anAwsEnumeration(NetworkInterfaceStatus)
  }
}
