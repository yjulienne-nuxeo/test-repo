/**
 * 
 */

package com.test.vg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.platform.ui.web.api.NavigationContext;
import org.nuxeo.ecm.webapp.documentsLists.DocumentsListsManager;

/**
 *
 * Code skeleton for a Seam bean that will manage a simple action.
 * This can be used to :
 *  - do a navigation
 *  - do some modification on the currentDocument (or other docs)
 *  - create new documents
 *   - send/retrive info from an external service
 *   - ...
 */
@Name("test")
@Scope(ScopeType.EVENT)
public class TestBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(TestBean.class);

    @In(create = true, required = false)
    protected transient CoreSession documentManager;

    @In(create = true)
    protected NavigationContext navigationContext;

    @In(create = true, required = false)
    protected transient FacesMessages facesMessages;

    @In(create = true, required = false)
    protected NuxeoPrincipal currentNuxeoPrincipal;

    @In(create = true)
    protected DocumentsListsManager documentsListsManager;

    // Sample code to show how to retrieve the list of selected documents in the
    // content listing view
    protected List<DocumentModel> getCurrentlySelectedDocuments() {

        if (navigationContext.getCurrentDocument().isFolder()) {
            return documentsListsManager.getWorkingList(DocumentsListsManager.CURRENT_DOCUMENT_SELECTION);
        } else {
            return null;
        }
    }

    // This the method that will be called when the action button/link is
    // clicked
    public String doGet() {
        List<DocumentModel> selectedDocs = getCurrentlySelectedDocuments();
        
        Map<String, Serializable> distributor = new HashMap<>();
        distributor.put(VGConstants.VGPRODUCT_DISTRIB_NAME, "CapCOM");
        distributor.put(VGConstants.VGPRODUCT_DISTRIB_SELL_LOCATION, "Australia");
        
        for (DocumentModel doc : selectedDocs) {
			doc.setPropertyValue(VGConstants.VGPRODUCT_DISTRIBUTOR, (Serializable) distributor);
			documentManager.saveDocument(doc);
		}
        
        String message = "Distributor updated for "+ selectedDocs.size()
        + " documents";
        
        facesMessages.add(StatusMessage.Severity.INFO, message);

        // if you need to change the current document and let Nuxeo
        // select the correct view
        // you can use navigationContext and return the view
        //
        // return navigationContext.navigateToDocument(doc);

        // If you want to explicitly go to a given view
        // just return the outcome string associated to the view
        //
        // return "someView";

        // stay on the same view
        return null;
    }

    // this method will be called by the action system to determine if the
    // action should be available
    //
    // the return value can depend on the context,
    // you can use the navigationContext to get the currentDocument,
    // currentWorkspace ...
    // you can cache the value in a member variable as long as the Bean stays
    // Event scoped
    //
    // if you don't need this, you should remove the filter in the associated
    // action contribution
    public boolean accept() {
    	//Action only available in Video Game workspace
    	return VGConstants.VG_WORKSPACE.equals(navigationContext.getCurrentDocument().getTitle());
    }

}
