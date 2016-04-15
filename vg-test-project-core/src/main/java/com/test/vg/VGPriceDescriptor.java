package com.test.vg;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("price")
public class VGPriceDescriptor {
	@XNode(value="@origin")
	String origin;
	@XNode(value="@value")
	double value;
}
