package com.test.vg.feature;

import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.LocalDeploy;
import org.nuxeo.runtime.test.runner.SimpleFeature;

@Features({ PlatformFeature.class })
@Deploy({ "com.test.vg.vg-test-project-core", "studio.extensions.yjulienne-SANDBOX" })
@LocalDeploy({ "com.test.vg.vg-test-project-core:mocked-filemanager-contrib.xml",
		"com.test.vg.vg-test-project-core:mocked-actions.xml" })
public class VGFeature extends SimpleFeature {

}
