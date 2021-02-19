package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class AffinitySpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Affinity enumeration" - {
    behave like anAwsEnumeration(Affinity)
  }
}
