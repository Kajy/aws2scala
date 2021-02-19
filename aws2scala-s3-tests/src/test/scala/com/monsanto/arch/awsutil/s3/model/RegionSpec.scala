package com.monsanto.arch.awsutil.s3.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec

class RegionSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Region enumeration" - {
    behave like anAwsEnumeration(Region)
  }
}
