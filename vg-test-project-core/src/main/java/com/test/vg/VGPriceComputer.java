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
    static final String VGPRODUCT_TYPE = "VGProductItem";
    static final String VGPRODUCT_SCHEMA = "vgpi:";
    static final String VGPRODUCT_PRICE = VGPRODUCT_SCHEMA + "price";
    
    
    @OperationMethod(collector=DocumentModelCollector.class)
    public DocumentModel run(DocumentModel input) {
    	if (!(VGPRODUCT_TYPE.equals(input.getType()))) {
            throw new NuxeoException("Operation works only with "
                    + VGPRODUCT_TYPE + " document type.");
        }
    
    	VGTestService vgTestService = Framework.getService(VGTestService.class);
    	vgTestService.computePrice(input);
    	
        return input;
    }
}
