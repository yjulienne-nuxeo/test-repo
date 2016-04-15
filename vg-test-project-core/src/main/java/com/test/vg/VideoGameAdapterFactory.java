/**
 * 
 */

package com.test.vg;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.adapter.DocumentAdapterFactory;

/**
 * @author Yannis
 */
public class VideoGameAdapterFactory implements DocumentAdapterFactory {

    @Override
    public Object getAdapter(DocumentModel doc, Class<?> itf) {
        if (doc.hasSchema("VGArticleSchema") && doc.hasSchema("VGProductItemSchema")){
            return new VideoGameAdapter(doc);
        }else{
            return null;
        }
    }
}
