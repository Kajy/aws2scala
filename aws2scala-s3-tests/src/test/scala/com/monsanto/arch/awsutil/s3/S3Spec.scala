package com.monsanto.arch.awsutil.s3

import com.monsanto.arch.awsutil.test_support.AwsClientProviderBehaviours
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec
class S3Spec extends AnyFreeSpec with MockFactory with AwsClientProviderBehaviours {
  "the S3 provider should" - {
    behave like anAwsClientProvider(S3)
  }
}
