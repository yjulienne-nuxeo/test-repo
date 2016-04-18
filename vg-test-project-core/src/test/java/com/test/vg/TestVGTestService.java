package com.test.vg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import com.test.vg.feature.VGFeature;

@RunWith(FeaturesRunner.class)
@Features({ VGFeature.class })
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
    	VideoGameAdapter doc = session.createDocumentModel("/default-domain", "my-test-doc",
				VGConstants.VGPRODUCT_TYPE).getAdapter(VideoGameAdapter.class);
    	
    	assertEquals("The price value is wrong for unknown origin", 0, vGTestService.computePrice(doc),0);
    	
    	doc.setOrigin(VGConstants.ORIGIN_US);
    	assertEquals("The price value is wrong for ORIGIN_US", 666.6, vGTestService.computePrice(doc),0);
    	
    	doc.setOrigin(VGConstants.ORIGIN_JAP);
    	assertEquals("The price value is wrong for ORIGIN_JAP", 33.3, vGTestService.computePrice(doc),0);
    	
    	doc.setOrigin(VGConstants.ORIGIN_FR);
    	assertEquals("The price value is wrong for ORIGIN_FR", 9.9, vGTestService.computePrice(doc),0);
    }
}
