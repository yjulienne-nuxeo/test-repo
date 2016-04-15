package com.test.vg;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;
import org.osgi.framework.Bundle;

public class VGTestServiceImpl extends DefaultComponent implements VGTestService {

    private Bundle bundle;
	private Map<String, Double> priceContributions;

	/**
     * Component activated notification.
     * Called when the component is activated. All component dependencies are resolved at that moment.
     * Use this method to initialize the component.
     *
     * @param context the component context.
     */
    @Override
    public void activate(ComponentContext context) {
        super.activate(context);
        Logger.getRootLogger().info("VGTestService ACTIVATED !!!!!!!!");
        bundle = context.getRuntimeContext().getBundle();
        priceContributions = new HashMap<>();
    }

    /**
     * Component deactivated notification.
     * Called before a component is unregistered.
     * Use this method to do cleanup if any and free any resources held by the component.
     *
     * @param context the component context.
     */
    @Override
    public void deactivate(ComponentContext context) {
        super.deactivate(context);
        bundle = null;
        priceContributions = null;
    }

    /**
     * Application started notification.
     * Called after the application started.
     * You can do here any initialization that requires a working application
     * (all resolved bundles and components are active at that moment)
     *
     * @param context the component context. Use it to get the current bundle context
     * @throws Exception
     */
    @Override
    public void applicationStarted(ComponentContext context) {
        // do nothing by default. You can remove this method if not used.
    }

    @Override
    public void registerContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
    	// We could have several extension points, a check is needed
    	if (contribution instanceof VGPriceDescriptor) { 

    		VGPriceDescriptor priceItem = (VGPriceDescriptor) contribution;

    		// Supposing the object contains an origin and value field mapped from the XML
    		priceContributions.put(priceItem.origin, priceItem.value); 
    	}
    }

    @Override
    public void unregisterContribution(Object contribution, String extensionPoint, ComponentInstance contributor) {
        // Logic to do when unregistering any contribution
    	// We could have several extension points, a check is needed
    	if (contribution instanceof VGPriceDescriptor) { 

    		VGPriceDescriptor priceItem = (VGPriceDescriptor) contribution;

    		// Supposing the object contains an origin field mapped from the XML
    		priceContributions.remove(priceItem.origin); 
    	}
    }

	@Override
	public double computePrice(DocumentModel doc) {
		String origin = (String) doc.getPropertyValue(VGConstants.VGPRODUCT_ORIGIN);
		if(priceContributions.containsKey(origin)){
			return priceContributions.get(origin);
		}
		else if(StringUtils.equalsIgnoreCase(origin, VGConstants.ORIGIN_US)){
			return 12.5;
		}
		else if(StringUtils.equalsIgnoreCase(origin, VGConstants.ORIGIN_JAP)){
			return 27.8;
		}
		else if(StringUtils.equalsIgnoreCase(origin, VGConstants.ORIGIN_FR)){
			return 55.9;
		}
		return 0;
	}
}
