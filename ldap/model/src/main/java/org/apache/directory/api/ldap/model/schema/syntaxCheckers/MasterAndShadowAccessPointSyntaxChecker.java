/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.api.ldap.model.schema.syntaxCheckers;


import org.apache.directory.api.i18n.I18n;
import org.apache.directory.api.ldap.model.constants.SchemaConstants;
import org.apache.directory.api.ldap.model.schema.SyntaxChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A SyntaxChecker which verifies that a value is a 
 * MasterAndShadowAccessPoint.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
@SuppressWarnings("serial")
public class MasterAndShadowAccessPointSyntaxChecker extends SyntaxChecker
{
    /** A logger for this class */
    private static final Logger LOG = LoggerFactory.getLogger( MasterAndShadowAccessPointSyntaxChecker.class );
    
    /**
     * A static instance of MasterAndShadowAccessPointSyntaxChecker
     */
    public static final MasterAndShadowAccessPointSyntaxChecker INSTANCE = new MasterAndShadowAccessPointSyntaxChecker();

    
    /**
     * Create a new instance of MasterAndShadowAccessPointSyntaxChecker
     */
    public MasterAndShadowAccessPointSyntaxChecker()
    {
        super( SchemaConstants.MASTER_AND_SHADOW_ACCESS_POINTS_SYNTAX );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidSyntax( Object value )
    {
        LOG.debug( I18n.msg( I18n.MSG_04490_SYNTAX_VALID, value ) );
        return true;
    }
}
