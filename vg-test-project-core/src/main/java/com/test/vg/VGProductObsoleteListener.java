package com.test.vg;

import java.io.Serializable;
import java.util.List;

import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;

public class VGProductObsoleteListener implements EventListener {

	public static final String HIDDEN_FOLDER_PATH = "/default-domain/workspaces/Video Games/obsolete";
	
	@Override
	public void handleEvent(Event event) {
		EventContext ctx = event.getContext();
        if (!(ctx instanceof DocumentEventContext)) {
            return;
        }
        DocumentModel doc = ((DocumentEventContext) ctx).getSourceDocument();
        if (doc == null) {
            return;
        }
        String type = doc.getType();
        String state = doc.getCurrentLifeCycleState();
        if (VGConstants.VGPRODUCT_TYPE.equals(type) && VGConstants.OBSOLETE_STATE.equals(state)) {
            process(doc);
        }
	}

	private void process(DocumentModel doc) {
		List<String> collectionIds = (List<String>) doc.getPropertyValue("collection:documentIds");
		CoreSession session = doc.getCoreSession();
		for (String itemId : collectionIds) {
			DocumentModel item = session.getDocument(new IdRef(itemId));
			session.move(new PathRef(item.getPathAsString()), new PathRef(HIDDEN_FOLDER_PATH), item.getName());
		}
	}

}
