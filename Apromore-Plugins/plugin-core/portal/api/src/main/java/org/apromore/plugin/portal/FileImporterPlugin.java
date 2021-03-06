/*
 * This file is part of "Apromore".
 *
 * Copyright (C) 2019 - 2020 The University of Melbourne.
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */
package org.apromore.plugin.portal;

import java.util.Set;
import org.zkoss.util.media.Media;

/**
 * Plug-in interface for importing files.
 *
 * This modifies the File/Import UI.
 */
public interface FileImporterPlugin {

    /**
     * @return the file extensions handled by this plugin; may be empty but should not be null
     */
    Set<String> getFileExtensions();

    /**
     * Call-back that is called when this plug-in is executed.
     *
     * @param media to be imported
     * @param context which provides access to the portal (e.g., selected items, create windows, ..)
     * @param isPublic whether to make the uploaded file publicly accessible
     */
    void importFile(Media media, PortalContext context, boolean isPublic);
}
