package com.monsanto.arch.awsutil.ec2.model

import com.monsanto.arch.awsutil.ec2.model.AwsConverters._
import com.monsanto.arch.awsutil.testkit.Ec2ScalaCheckImplicits._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks._

class ReservationSpec extends AnyFreeSpec {
  "a Reservation should" - {
    "be constructible from its AWS equivalent" in {
      forAll { reservation: Reservation =>
        Reservation.fromAws(reservation.toAws) shouldBe reservation
      }
    }
  }
}
