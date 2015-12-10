/*
 * Copyright © 2009-2015 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package org.apromore.canoniser.bpmn;

// Java 2 Standard packages
import java.io.File;

/**
 * Shared directory locations for test data.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public interface TestConstants {

    /** Source for BPMN test data. */
    final static File BPMN_MODELS_DIR = new File("src/test/resources/BPMN_models/");

    /** Destination for converted documents generated by tests. */
    final static File OUTPUT_DIR = new File("target/");

    /** Source for ANF and CPF test data. */
    final static File CANONICAL_MODELS_DIR = new File("src/test/resources/canonical_models/");
}
