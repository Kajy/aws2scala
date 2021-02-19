package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class VirtualizationTypeSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the VirtualizationType enumeration" - {
    behave like anAwsEnumeration(VirtualizationType)
  }
}
