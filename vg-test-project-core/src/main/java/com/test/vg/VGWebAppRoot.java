package com.test.vg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.runtime.api.Framework;

@Path("vgtest")
public class VGWebAppRoot {
	@GET
	public Object doGet() {
		return "VG TEST WebApp!";
	}

	@GET
	@Path("count")
	public String doCount() {
		DocumentModel vgWorkspace = WebEngine.getActiveContext().getCoreSession().getDocument(new PathRef("/default-domain/workspaces/Video Games"));
		VGTestService vgTestService = Framework.getService(VGTestService.class);
		long count = vgTestService.getVGCount(vgWorkspace);
		return "There are "+count+" elements in the Video Game workspace";
	}
}
