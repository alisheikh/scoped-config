package com.gilt.job.alipay.config

import scala.util.Failure
import scala.util.Success
import scala.util.Try

import com.typesafe.config.Config
import com.typesafe.config.ConfigException
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

object ScopedConfigFactory extends LazyLogging {
  def load(scope: String): Config = {
    logger.info(s"Loading config scoped to [$scope]")

    val defaultConfig = ConfigFactory.load()
    Try {
      defaultConfig.getConfig(scope)
    } match {
      case Failure(ex: ConfigException.Missing) => defaultConfig
      case Failure(ex) => throw ex
      case Success(environmentConfig) => environmentConfig.withFallback(defaultConfig)
    }
  }
}