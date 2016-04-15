/**
 * 
 */

package com.test.vg;

import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.PropertyException;

/**
 * @author Yannis
 */
public class VideoGameAdapter {

    protected final DocumentModel doc;

    public VideoGameAdapter(DocumentModel doc) {
        this.doc = doc;
    }

    public void save(CoreSession session) throws NuxeoException {
        session.saveDocument(doc);
    }

    public String getId() {
        return doc.getId();
    }

    public String getArticleId() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("vg:articleId");
    }

    public void setArticleId(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vg:articleId", value);
    }

    public String getArticleName() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("vg:articleName");
    }

    public void setArticleName(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vg:articleName", value);
    }

    public String getCategory() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("vg:category");
    }

    public void setCategory(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vg:category", value);
    }

    public Calendar getDeliveryTime() throws PropertyException, NuxeoException {
        return (Calendar)doc.getPropertyValue("vgpi:deliveryTime");
    }

    public void setDeliveryTime(Calendar value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vgpi:deliveryTime", value);
    }

    public Object getDistributor() throws PropertyException, NuxeoException {
        return (Object)doc.getPropertyValue("vgpi:distributor");
    }

    public void setDistributor(Object value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vgpi:distributor", (Serializable) value);
    }

    public String getOrigin() throws PropertyException, NuxeoException {
        return (String)doc.getPropertyValue("vgpi:origin");
    }

    public void setOrigin(String value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vgpi:origin", value);
    }

    public Object getPrice() throws PropertyException, NuxeoException {
        return (Object)doc.getPropertyValue("vgpi:price");
    }

    public void setPrice(Object value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vgpi:price", (Serializable) value);
    }

    public Long getSize() throws PropertyException, NuxeoException {
        return (Long)doc.getPropertyValue("vgpi:size");
    }

    public void setSize(Long value) throws PropertyException, NuxeoException {
        doc.setPropertyValue("vgpi:size", value);
    }

}
