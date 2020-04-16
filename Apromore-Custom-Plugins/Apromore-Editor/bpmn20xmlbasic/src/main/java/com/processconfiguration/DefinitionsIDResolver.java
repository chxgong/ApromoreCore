
package com.processconfiguration;

/*-
 * #%L
 * Signavio Core Components
 * %%
 * Copyright (C) 2006 - 2020 The University of Melbourne.
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 * #L%
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import com.sun.xml.bind.IDResolver;

import de.hpi.bpmn2_0.model.activity.Activity;
import de.hpi.bpmn2_0.model.connector.Edge;
import de.hpi.bpmn2_0.model.event.BoundaryEvent;

/**
 * Custom handler for unmarshalling IDREFs from a {@link de.hpi.bpmn2_0.model.Definitions} document.
 *
 * This ensures that the {@link de.hpi.bpmn2_0.model.FlowElement#getIncoming} and
 * {@link de.hpi.bpmn2_0.model.FlowElement#getOutgoing} properties are populated during parsing.
 *
 * It also populates the {@link Activity#getAttachedBoundaryEvents} fields.
 *
 * The proprietary {@link IDResolver} API in the Sun JAXB-RI library is employed as follows:
 * <pre>
 * import com.sun.xml.bind.IDResolver;
 *
 * Unmarshaller unmarshaller = context.createUnmarshaller();
 * unmarshaller.setProperty(IDResolver.class.getName(), new DefinitionsIDResolver());
 * </pre>
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 * @see <a href="http://weblogs.java.net/blog/kohsuke/archive/2005/08/pluggable_ididr.html">Pluggable
 * ID/IDREF handling in JAXB 2.0</a>
 */
public class DefinitionsIDResolver extends IDResolver {

    /** Mapping from XML IDs to JAXB objects. */
    private final Map<String, Object> idMap = new HashMap<String, Object>();

    /**
     * Initialize {@link #idMap}.
     *
     * JAXB-RI magically knows about this method signature and will invoke it dynamically.
     */
    public final void startDocument() {
        idMap.clear();
    }

    /** {@inheritDoc} */
    public final void bind(final String id, final Object ref) {
        idMap.put(id, ref);
    }

    /** {@inheritDoc} */
    public final Callable resolve(final String id, final Class c) {
        return new Callable() {
            public Object call() { return idMap.get(id); }
        };
    }

    /**
     * After the document has been parsed, and then had all its IDREFs populated, we perform a third
     * pass to populate fields derived from the IDREFs.
     *
     * These are the lists of incoming and outgoing edges on {@link de.hpi.bpmn2_0.model.FlowElement}s,
     * and the attached {@link BoundaryEvent} lists on {@link Activity} elements.
     *
     * JAXB-RI magically knows about this method signature and will invoke it dynamically.
     */
    public final void endDocument() {
        for (Object ref : idMap.values()) {

            // FlowElements know about their incoming and outgoing edges
            if (ref instanceof Edge) {
                Edge edge = (Edge) ref;

                // Add this edge to its source's outgoings
                if (edge.getSourceRef() != null && !edge.getSourceRef().getOutgoing().contains(edge)) {
                    edge.getSourceRef().getOutgoing().add(edge);
                }

                // Add this edge to its target's incomings
                if (edge.getTargetRef() != null && !edge.getTargetRef().getIncoming().contains(edge)) {
                    edge.getTargetRef().getIncoming().add(edge);
                }
            }

            // Activities know about their attached boundary events
            if (ref instanceof BoundaryEvent) {
                Activity attachedTo = ((BoundaryEvent) ref).getAttachedToRef();

                attachedTo.getAttachedBoundaryEvents().addAll(attachedTo.getBoundaryEventRefs());
            }
        }
    }
}
