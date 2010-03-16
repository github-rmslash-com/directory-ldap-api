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
package org.apache.directory.shared.ldap.entry;

import org.apache.directory.shared.ldap.exception.LdapException;

import org.apache.directory.shared.i18n.I18n;
import org.apache.directory.shared.ldap.schema.SyntaxChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A wrapper around byte[] values in entries.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public abstract class AbstractValue<T> implements Value<T>
{
    /** logger for reporting errors that might not be handled properly upstream */
    private static final Logger LOG = LoggerFactory.getLogger( AbstractValue.class );

    
    /** the wrapped binary value */
    protected T wrapped;
    
    /** the canonical representation of the wrapped value */
    protected T normalizedValue;

    /** A flag set when the value has been normalized */
    protected boolean normalized;

    /** cached results of the isValid() method call */
    protected Boolean valid;

    /** A flag set if the normalized data is different from the wrapped data */
    protected transient boolean same;
    
    /**
     * Reset the value
     */
    public void clear()
    {
        wrapped = null;
        normalized = false;
        normalizedValue = null;
        valid = null;
    }

    
    /**
     * {@inheritDoc}
     */
    public Value<T> clone()
    {
        try
        {
            return (Value<T>)super.clone();
        }
        catch ( CloneNotSupportedException cnse )
        {
            // Do nothing
            return null;
        }
    }
    
    
    /**
     * Gets a reference to the wrapped binary value.
     * 
     * Warning ! The value is not copied !!!
     *
     * @return a direct handle on the binary value that is wrapped
     */
    public T getReference()
    {
        return wrapped;
    }

    
    /**
     * Gets a copy of the wrapped binary value.
     * 
     * @return a copy of the binary value that is wrapped
     */
    public T get()
    {
        // Just call the specific Client copy method.
        return getCopy();
    }

    
    /**
     * Gets the normalized (canonical) representation for the wrapped value.
     * If the wrapped value is null, null is returned, otherwise the normalized
     * form is returned.  If the normalized Value is null, then the wrapped 
     * value is returned
     *
     * @return gets the normalized value
     */
    public T getNormalizedValue()
    {
        if ( isNull() )
        {
            return null;
        }

        if ( normalizedValue == null )
        {
            return getCopy();
        }

        return getNormalizedValueCopy();
    }


    /**
     * Gets a reference to the the normalized (canonical) representation 
     * for the wrapped value.
     *
     * @return gets a reference to the normalized value
     */
    public T getNormalizedValueReference()
    {
        if ( isNull() )
        {
            return null;
        }

        if ( normalizedValue == null )
        {
            return wrapped;
        }

        return normalizedValue;

    }

    
    /**
     * Check if the contained value is null or not
     * 
     * @return <code>true</code> if the inner value is null.
     */
    public final boolean isNull()
    {
        return wrapped == null; 
    }
    
    
    /**
     * @return Tells if the wrapped value and the normalized value are the same 
     */
    public final boolean isSame()
    {
        return same;
    }

    
    /**
     * Check if the Valid flag is set or not. This flag is set by a call
     * to the isValid( SyntaxChecker ) method for client values. It is overridden
     * for server values.
     * 
     * if the flag is not set, returns <code>false</code>
     *
     * @see ServerValue#isValid()
     */
    public boolean isValid()
    {
        if ( valid != null )
        {
            return valid;
        }

        return false;
    }


    /**
     * Uses the syntaxChecker associated with the attributeType to check if the
     * value is valid.  Repeated calls to this method do not attempt to re-check
     * the syntax of the wrapped value every time if the wrapped value does not
     * change. Syntax checks only result on the first check, and when the wrapped
     * value changes.
     *
     * @see ServerValue#isValid()
     */
    public final boolean isValid( SyntaxChecker syntaxChecker ) throws LdapException
    {
        if ( valid != null )
        {
            return valid;
        }
        
        if ( syntaxChecker == null )
        {
            String message = I18n.err( I18n.ERR_04139, toString() );
            LOG.error( message );
            throw new LdapException( message );
        }
        
        valid = syntaxChecker.isValidSyntax( getReference() );
        return valid;
    }


    /**
     * Normalize the value. In order to use this method, the Value
     * must be schema aware.
     * 
     * @exception LdapException If the value cannot be normalized
     */
    public void normalize() throws LdapException
    {
        normalized = true;
        normalizedValue = wrapped;
    }


    /**
     * Sets this value's wrapped value to a copy of the src array.
     *
     * @param wrapped the byte array to use as the wrapped value
     */
    public abstract void set( T wrapped );

    
    /**
     * Tells if the value has already be normalized or not.
     *
     * @return <code>true</code> if the value has already been normalized.
     */
    public final boolean isNormalized()
    {
        return normalized;
    }

    
    /**
     * Set the normalized flag.
     * 
     * @param the value : true or false
     */
    public final void setNormalized( boolean normalized )
    {
        this.normalized = normalized;
    }
}