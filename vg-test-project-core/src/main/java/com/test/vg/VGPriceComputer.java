package com.test.vg;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.collectors.DocumentModelCollector;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.runtime.api.Framework;

/**
 *
 */
@Operation(id=VGPriceComputer.ID, category=Constants.CAT_DOCUMENT, label="Compute Price", description="Compute the price of one or more Video Game product(s).")
public class VGPriceComputer {

    public static final String ID = "Document.VGPriceComputer";
    
    @OperationMethod(collector=DocumentModelCollector.class)
    public DocumentModel run(DocumentModel input) {
    	if (!(VGConstants.VGPRODUCT_TYPE.equals(input.getType()))) {
            throw new NuxeoException("Operation works only with "
                    + VGConstants.VGPRODUCT_TYPE + " document type.");
        }
    
    	VGTestService vgTestService = Framework.getService(VGTestService.class);
    	double price = vgTestService.computePrice(input);
    	
    	input.setPropertyValue(VGConstants.VGPRODUCT_PRICE, price);
    	
        return input;
    }
}
