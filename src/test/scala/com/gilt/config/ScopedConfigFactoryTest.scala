package com.gilt.config

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

class ScopedConfigFactoryTest extends FlatSpec with Matchers {
  "ScopedConfigFactory" should "load a basic unscoped config when null is passed in" in {
    val config = ScopedConfigFactory.load(null)
    config shouldBe basicConfig
  }

  it should "load a basic unscoped config when an empty string is passed in" in {
    val config = ScopedConfigFactory.load("")
    config shouldBe basicConfig
  }

  it should "load a 'test' scoped config" in {
    val config = ScopedConfigFactory.load("test")
    config shouldNot be (basicConfig)
    config.getString("a") shouldBe "test-a"
    config.getInt("b") shouldBe 2
    config.getInt("c") shouldBe 200
    config.getString("d") shouldBe "d"
  }

  it should "load a 'prod' scoped config" in {
    val config = ScopedConfigFactory.load("prod")
    config shouldNot be (basicConfig)
    config.getString("a") shouldBe "prod-a"
    config.getInt("b") shouldBe 3
    config.getInt("c") shouldBe 100
    config.getString("d") shouldBe "d"
  }

  it should "load a 'dev' scoped config even though there are no 'dev' scoped config values" in {
    val config = ScopedConfigFactory.load("dev")
    config shouldBe basicConfig
  }

  private def basicConfig: Config = {
    ConfigFactory.load()
  }
}