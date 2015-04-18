package com.gilt.config

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
    val result = Try {
      if (Option(scope).forall(_.isEmpty)) {
        defaultConfig
      } else {
        defaultConfig.getConfig(scope).withFallback(defaultConfig)
      }
    } recover {
      case e: ConfigException.Missing => defaultConfig
    }

    result.get
  }
}