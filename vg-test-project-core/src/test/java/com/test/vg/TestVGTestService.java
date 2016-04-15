package com.test.vg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features({ PlatformFeature.class })
@Deploy({ "com.test.vg.vg-test-project-core", "studio.extensions.yjulienne-SANDBOX" })
@LocalDeploy({ "com.test.vg.vg-test-project-core:mocked-filemanager-contrib.xml",
		"com.test.vg.vg-test-project-core:mocked-actions.xml" })
public class TestVGTestService {

	@Inject
	protected CoreSession session;
	
    @Inject
    protected VGTestService vGTestService;

    @Test
    public void testService() {
        assertNotNull(vGTestService);
    }
    
    @Test
    public void testComputePrice() {
    	DocumentModel doc = session.createDocumentModel("/default-domain", "my-test-doc",
				VGPriceComputer.VGPRODUCT_TYPE);
    	session.createDocument(doc);
    	
    	// At this stage, the price value should be empty
    	assertNull(doc.getPropertyValue(VGPriceComputer.VGPRODUCT_PRICE));
    	
    	vGTestService.computePrice(doc);
    	session.save();
    	
    	assertEquals("The price value should be set", 55.2, doc.getPropertyValue(VGPriceComputer.VGPRODUCT_PRICE));
    }
}
