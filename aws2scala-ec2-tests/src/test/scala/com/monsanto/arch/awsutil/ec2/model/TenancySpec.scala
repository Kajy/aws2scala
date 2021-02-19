package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class TenancySpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Tenancy enumeration" - {
    behave like anAwsEnumeration(Tenancy)
  }
}
