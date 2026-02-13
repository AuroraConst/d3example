package org.aurora

import org.scalatest._
import wordspec._
import matchers._
// import org.scalatest.Matchers._


class BasicTest extends AnyWordSpec with should.Matchers {

    "Euler Test" should {
        "equal itself" in {
            1 shouldEqual 1
        }
    }
}   