package com.monsanto.arch.awsutil.regions

import com.amazonaws.{regions => aws}
import com.monsanto.arch.awsutil.converters.CoreConverters._
import com.monsanto.arch.awsutil.test_support.AwsEnumerationBehaviours
import org.scalatest.freespec.AnyFreeSpec
class RegionSpec extends AnyFreeSpec with AwsEnumerationBehaviours {
  "the Region enumeration" - {
    behave like anAwsEnumeration(
      aws.Regions.values(),
      Region.values,
      (_: Region).asAws,
      (_: aws.Regions).asScala)
  }
}
