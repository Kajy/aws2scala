package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class AttachmentStatusSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the AttachmentStatus enumeration" - {
    behave like anAwsEnumeration(Architecture)
  }
}
