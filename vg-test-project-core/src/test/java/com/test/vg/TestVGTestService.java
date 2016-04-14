package com.test.vg;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy("com.test.vg.vg-test-project-core")
public class TestVGTestService {

    @Inject
    protected VGTestService vGTestService;

    @Test
    public void testService() {
        assertNotNull(vGTestService);
    }
}
