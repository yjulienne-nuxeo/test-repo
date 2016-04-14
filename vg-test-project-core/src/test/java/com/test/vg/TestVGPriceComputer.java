package com.test.vg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.LocalDeploy;

@RunWith(FeaturesRunner.class)
@Features(AutomationFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Deploy({ "com.test.vg.vg-test-project-core", "studio.extensions.yjulienne-SANDBOX" })
@LocalDeploy({ "com.test.vg.vg-test-project-core:mocked-filemanager-contrib.xml",
		"com.test.vg.vg-test-project-core:mocked-actions.xml" })
public class TestVGPriceComputer {

	@Inject
	protected CoreSession session;

	@Inject
	protected AutomationService automationService;

	@Test
	public void shouldCallTheOperation() throws OperationException {
		// OperationContext ctx = new OperationContext(session);
		// DocumentModel doc = (DocumentModel) automationService.run(ctx,
		// VGPriceComputer.ID);
		// assertEquals("/", doc.getPathAsString());

		// Create a contract, currently stored in memory
		DocumentModel doc = session.createDocumentModel("/default-domain", "my-test-doc",
				VGPriceComputer.VGPRODUCT_TYPE);

		// At this stage, the price value should be empty
		assertNull(doc.getPropertyValue(VGPriceComputer.VGPRODUCT_PRICE));

		// We'll save the document in the database
		// That should trigger an event handler that will set the reminder date
		doc = session.createDocument(doc);
		session.save();

		assertEquals("The price value should be set", 55.2, doc.getPropertyValue(VGPriceComputer.VGPRODUCT_PRICE));
	}

}
