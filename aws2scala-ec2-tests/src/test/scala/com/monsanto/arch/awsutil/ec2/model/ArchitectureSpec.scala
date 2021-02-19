package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class ArchitectureSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Architecture enumeration" - {
    behave like anAwsEnumeration(Architecture)
  }
}
