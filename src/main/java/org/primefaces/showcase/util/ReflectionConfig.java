package org.primefaces.showcase.util;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets = {org.omnifaces.cdi.push.SocketEndpoint.class}, serialization = true)
public class ReflectionConfig {
}