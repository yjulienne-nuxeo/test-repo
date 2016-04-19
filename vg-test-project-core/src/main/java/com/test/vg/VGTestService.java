package com.test.vg;

import org.nuxeo.ecm.core.api.DocumentModel;

public interface VGTestService {

	double computePrice(VideoGameAdapter videoGameAdapter);

	String computeDistributorPolicy(VideoGameAdapter videoGameAdapter);

	long getVGCount(DocumentModel vgWorkspace);
	
    /** Add some methods here. **/
}
