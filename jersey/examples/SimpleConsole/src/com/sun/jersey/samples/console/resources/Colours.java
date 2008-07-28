/*
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://jersey.dev.java.net/CDDL+GPL.html
 * or jersey/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at jersey/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.jersey.samples.console.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * A web resource for a list of colours.
 */
public class Colours {
    
    private static String colours[] = {"red","orange","yellow","green","blue","indigo","violet"};
    
    /**
     * Returns a list of colours as plain text, one colour per line.
     * @param filter If not empty, constrains the list of colours to only
     * those that contain this substring
     * @return the list of colours matching the filter
     */
    @GET
    @Produces("text/plain")
    public String getColourListAsText(@QueryParam("match") String filter) {
        StringBuffer buf = new StringBuffer();
        for (String colour: getMatchingColours(filter)) {
            buf.append(colour);
            buf.append('\n');
        }
        return buf.toString();
    }
    
    /**
     * Returns a list of colours as a JSON array.
     * @param filter If not empty, constrains the list of colours to only 
     * those that contain this substring
     * @return the list of colours matching the filter
     */
    @GET
    @Produces("application/json")
    public String getColourListAsJSON(@QueryParam("match") String filter) {
        StringBuffer buf = new StringBuffer();
        buf.append('[');
        boolean first = true;
        for (String colour: getMatchingColours(filter)) {
            if (!first)
                buf.append(',');
            buf.append('\'');
            buf.append(colour);
            buf.append('\'');
            first = false;
        }
        buf.append(']');
        return buf.toString();
    }
    
    /**
     * Returns a list of colours.
     * @param filter If not empty, constrains the list of colours to only
     * those that contain this substring
     * @return the list of colours matching the filter
     */
    public static List<String> getMatchingColours(String filter) {
        List<String> matches = new ArrayList<String>();

        for (String colour: colours) {
            if (filter==null || filter.length()==0 || colour.contains(filter)) {
                matches.add(colour);
            }
        }
        
        return matches;
    }
}
