# scoped-config
This provides helper code for getting scoped configs for a production environment.

[![Build Status](https://travis-ci.org/gilt/scoped-config.svg)](https://travis-ci.org/gilt/scoped-config)

[typesafehub/config](https://github.com/typesafehub/config) provides a very flexible config library, but it may be flexible to a fault in that it's not clear on how to just setup a simple configuration that has scoped overrides for production values. This library tries to fill that gap.

# Installation

For Scala 2.11 with Java 7 or higher.

##sbt dependency:

    libraryDependencies += "com.gilt" %% "scoped-config" % "0.0.1"

# Usage

It should work if as if you are using the default `ConfigFactory.load()` function, but it takes a scope which will be pulled up to the root of the config if it's there falling back to the default again.

If your config looked like this:

    foo = "default"
    beta.foo = "beta-scoped-foo"
    prod.foo = "prod-scoped-foo"

    bar = "baz"

You can get a `prod` scoped config like this:

    import com.gilt.config.ScopedConfigFactory

    ScopedConfigFactory.load("prod")

That config would look as though you had a config like this:

    foo = "prod-scoped-foo"

    bar = "baz"

A `beta` scoped conf would look like this:

    foo = "beta-scoped-foo"

    bar = "baz"

Notice we don't have any values scoped to `gamma` but we could still get a `gamma` scoped conf which would just look like the default one:

    foo = "default"

    bar = "baz"

# Complex scopes

More complex scopes have limited support. For example if you had a more complex scope that was formed of <region>.<stage>. Then you could have scopes like `US.Prod` and `GB.Test`.

There isn't any support for any wildcards though, so not sure how useful that is yet. If there's demand, maybe we could figure out how to add it.

# Limitations

It won't remove the other scopes from your config. So from the above example if you had a `prod` scoped config, you could see the `beta` values if you want like this:

    ScopedConfigFactory.load("prod").getString("beta.foo") // "beta-scoped-foo"

I think this is unavoidable without limiting the values that can be used for scopes and is a trade-off you get for multiplexing a single simple configuration into many scoped configurations.
