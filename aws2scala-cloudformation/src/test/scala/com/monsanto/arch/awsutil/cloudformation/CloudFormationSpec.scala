package com.monsanto.arch.awsutil.cloudformation

import com.monsanto.arch.awsutil.test_support.AwsClientProviderBehaviours
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec

class CloudFormationSpec extends AnyFreeSpec with MockFactory with AwsClientProviderBehaviours {
  "the CloudFormation provider should" - {
    behave like anAwsClientProvider(CloudFormation)
  }
}
