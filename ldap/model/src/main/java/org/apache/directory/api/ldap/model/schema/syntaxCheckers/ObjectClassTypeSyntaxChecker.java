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
import org.apache.directory.api.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A syntax checker which checks to see if an objectClass' type is either: 
 * <em>AUXILIARY</em>, <em>STRUCTURAL</em>, or <em>ABSTRACT</em>.  The case is NOT ignored.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
@SuppressWarnings("serial")
public class ObjectClassTypeSyntaxChecker extends SyntaxChecker
{
    /** A logger for this class */
    private static final Logger LOG = LoggerFactory.getLogger( ObjectClassTypeSyntaxChecker.class );
    
    /**
     * A static instance of ObjectClassTypeSyntaxChecker
     */
    public static final ObjectClassTypeSyntaxChecker INSTANCE = new ObjectClassTypeSyntaxChecker();

    
    /**
     * Creates a new instance of ObjectClassTypeSyntaxChecker.
     */
    public ObjectClassTypeSyntaxChecker()
    {
        super( SchemaConstants.OBJECT_CLASS_TYPE_SYNTAX );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidSyntax( Object value )
    {
        String strValue;

        if ( value == null )
        {
            LOG.debug( I18n.err( I18n.ERR_04489_SYNTAX_INVALID, "null" ) );
            return false;
        }

        if ( value instanceof String )
        {
            strValue = ( String ) value;
        }
        else if ( value instanceof byte[] )
        {
            strValue = Strings.utf8ToString( ( byte[] ) value );
        }
        else
        {
            strValue = value.toString();
        }

        if ( strValue.length() < 8 || strValue.length() > 10 )
        {
            LOG.debug( I18n.err( I18n.ERR_04489_SYNTAX_INVALID, value ) );
            return false;
        }

        switch ( strValue )
        {
            case "AUXILIARY" :
            case "ABSTRACT" :
            case "STRUCTURAL" :
                LOG.debug( I18n.msg( I18n.MSG_04490_SYNTAX_VALID, value ) );
                return true;
                
            default :
                LOG.debug( I18n.err( I18n.ERR_04489_SYNTAX_INVALID, value ) );
                return false;
        }
    }
}
