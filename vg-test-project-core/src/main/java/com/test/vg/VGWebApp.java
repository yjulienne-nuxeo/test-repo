package com.test.vg;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class VGWebApp extends Application{
	@Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> result = new HashSet<Class<?>>();
        result.add(VGWebAppRoot.class);
        return result;
    }
}
