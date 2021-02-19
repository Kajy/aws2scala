package com.monsanto.arch.awsutil.rds

import com.monsanto.arch.awsutil.test_support.AwsClientProviderBehaviours
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AnyFreeSpec
class RDSSpec extends AnyFreeSpec with MockFactory with AwsClientProviderBehaviours {
  "the RDS provider should" - {
    behave like anAwsClientProvider(RDS)
  }
}
