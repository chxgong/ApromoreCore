/*-
 * #%L
 * This file is part of "Apromore Core".
 * %%
 * Copyright (C) 2018 - 2020 The University of Melbourne.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package org.apromore.processmining.models.graphbased.directed;

import java.util.Collection;

public interface DirectedGraphEdge<S extends DirectedGraphNode, T extends DirectedGraphNode> extends
		DirectedGraphElement {

	/**
	 * Implementations of this class should also implement DirectedGraphEdge<N,
	 * ?>
	 * 
	 * @author bfvdonge
	 * 
	 * @param <N>
	 */
	public static interface MultipleSources<N extends DirectedGraphNode> {
		/**
		 * returns all target nodes of this DirectedGraphEdge, including the
		 * node returned by getSource() of DirectedGraphEdge.
		 * 
		 * @return
		 */
		Collection<N> getSources();
	}

	/**
	 * Implementations of this class should also implement DirectedGraphEdge<?,
	 * N>
	 * 
	 * @author bfvdonge
	 * 
	 * @param <N>
	 */
	public static interface MultipleTargets<N extends DirectedGraphNode> {

		/**
		 * returns all target nodse of this DirectedGraphEdge, including the
		 * node returned by getTarget() of DirectedGraphEdge.
		 * 
		 * @return
		 */
		Collection<N> getTargets();
	}

	S getSource();

	T getTarget();

}