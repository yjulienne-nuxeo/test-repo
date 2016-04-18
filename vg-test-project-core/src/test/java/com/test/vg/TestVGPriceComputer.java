package com.test.vg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationChain;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import com.test.vg.feature.VGFeature;

@RunWith(FeaturesRunner.class)
@Features({ AutomationFeature.class, VGFeature.class })
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
public class TestVGPriceComputer {

	@Inject
	protected CoreSession session;

	@Test
	public void shouldCallTheOperation() throws OperationException {
		// Create a contract, currently stored in memory
		DocumentModel doc = session.createDocumentModel("/default-domain", "my-test-doc",
				VGConstants.VGPRODUCT_TYPE);

		// At this stage, the price value should be empty
		assertNull(doc.getPropertyValue(VGConstants.VGPRODUCT_PRICE));

		AutomationService automationService = Framework.getService(AutomationService.class);
		OperationContext ctx = new OperationContext();
		ctx.setCoreSession(session);
		ctx.setInput(doc);
		OperationChain chain = new OperationChain("testChain");
		chain.add(VGPriceComputer.ID);
		
		doc = (DocumentModel) automationService.run(ctx, chain);
		assertEquals("The price value should be set", 0.0, doc.getPropertyValue(VGConstants.VGPRODUCT_PRICE));
		
		
		doc.setPropertyValue(VGConstants.VGPRODUCT_ORIGIN, VGConstants.ORIGIN_US);
		doc = (DocumentModel) automationService.run(ctx, chain);
		assertEquals("The price value should be set", 666.6, doc.getPropertyValue(VGConstants.VGPRODUCT_PRICE));
		
		doc.setPropertyValue(VGConstants.VGPRODUCT_ORIGIN, VGConstants.ORIGIN_JAP);
		doc = (DocumentModel) automationService.run(ctx, chain);
		assertEquals("The price value should be set", 33.3, doc.getPropertyValue(VGConstants.VGPRODUCT_PRICE));
		
		doc.setPropertyValue(VGConstants.VGPRODUCT_ORIGIN, VGConstants.ORIGIN_FR);
		doc = (DocumentModel) automationService.run(ctx, chain);
		assertEquals("The price value should be set", 9.9, doc.getPropertyValue(VGConstants.VGPRODUCT_PRICE));
	}

}
