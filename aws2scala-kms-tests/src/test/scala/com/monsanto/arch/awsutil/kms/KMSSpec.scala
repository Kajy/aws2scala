package com.monsanto.arch.awsutil.kms

import com.monsanto.arch.awsutil.test_support.AwsClientProviderBehaviours
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec
class KMSSpec extends AnyFreeSpec with MockFactory with AwsClientProviderBehaviours {
  "the KMS provider should" - {
    behave like anAwsClientProvider(KMS)
  }
}
