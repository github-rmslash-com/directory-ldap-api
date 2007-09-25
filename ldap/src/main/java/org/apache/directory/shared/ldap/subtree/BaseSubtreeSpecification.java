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
package org.apache.directory.shared.ldap.subtree;


import org.apache.directory.shared.ldap.filter.ExprNode;
import org.apache.directory.shared.ldap.name.LdapDN;

import java.util.Iterator;
import java.util.Set;
import java.util.Collections;


/**
 * A simple implementation of the SubtreeSpecification interface.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$
 */
public class BaseSubtreeSpecification implements SubtreeSpecification
{
    /** the subtree base relative to the administration point */
    private final LdapDN base;

    /** the set of subordinates entries and their subordinates to exclude */
    private final Set<LdapDN> chopBefore;

    /** the set of subordinates entries whose subordinates are to be excluded */
    private final Set<LdapDN> chopAfter;

    /** the minimum distance below base to start including entries */
    private final int minBaseDistance;

    /** the maximum distance from base past which entries are excluded */
    private final int maxBaseDistance;

    /**
     * a filter using only assertions on objectClass attributes for subtree
     * refinement
     */
    private final ExprNode refinement;


    // -----------------------------------------------------------------------
    // C O N S T R U C T O R S
    // -----------------------------------------------------------------------

    /**
     * Creates a simple subtree whose administrative point is necessarily the
     * base and all subordinates underneath (excluding those that are part of
     * inner areas) are part of the the subtree.
     */
    @SuppressWarnings("unchecked")
    public BaseSubtreeSpecification()
    {
        this.base = new LdapDN();
        this.minBaseDistance = 0;
        this.maxBaseDistance = UNBOUNDED_MAX;
        this.chopAfter = Collections.EMPTY_SET;
        this.chopBefore = Collections.EMPTY_SET;
        this.refinement = null;
    }


    /**
     * Creates a simple subtree refinement whose administrative point is
     * necessarily the base and only those subordinates selected by the
     * refinement filter are included.
     *
     * @param refinement
     *            the filter expression only composed of objectClass attribute
     *            value assertions
     */
    @SuppressWarnings("unchecked")
    public BaseSubtreeSpecification(ExprNode refinement)
    {
        this.base = new LdapDN();
        this.minBaseDistance = 0;
        this.maxBaseDistance = UNBOUNDED_MAX;
        this.chopAfter = Collections.EMPTY_SET;
        this.chopBefore = Collections.EMPTY_SET;
        this.refinement = refinement;
    }


    /**
     * Creates a simple subtree whose administrative point above the base and
     * all subordinates underneath the base (excluding those that are part of
     * inner areas) are part of the the subtree.
     *
     * @param base
     *            the base of the subtree relative to the administrative point
     */
    @SuppressWarnings("unchecked")
    public BaseSubtreeSpecification( LdapDN base )
    {
        this.base = base;
        this.minBaseDistance = 0;
        this.maxBaseDistance = UNBOUNDED_MAX;
        this.chopAfter = Collections.EMPTY_SET;
        this.chopBefore = Collections.EMPTY_SET;
        this.refinement = null;
    }


    /**
     * Creates a subtree without a refinement filter where all other aspects can
     * be varied.
     *
     * @param base
     *            the base of the subtree relative to the administrative point
     * @param minBaseDistance
     *            the minimum distance below base to start including entries
     * @param maxBaseDistance
     *            the maximum distance from base past which entries are excluded
     * @param chopAfter
     *            the set of subordinates entries whose subordinates are to be
     *            excluded
     * @param chopBefore
     *            the set of subordinates entries and their subordinates to
     *            exclude
     */
    public BaseSubtreeSpecification( LdapDN base, int minBaseDistance, int maxBaseDistance, 
        Set<LdapDN> chopAfter, Set<LdapDN> chopBefore )
    {
        this( base, minBaseDistance, maxBaseDistance, chopAfter, chopBefore, null );
    }


    /**
     * Creates a subtree which may be a refinement filter where all aspects of
     * the specification can be set. If the refinement filter is null this
     * defaults to {@link #BaseSubtreeSpecification(LdapDN, int, int, Set, Set)}.
     *
     * @param base
     *            the base of the subtree relative to the administrative point
     * @param minBaseDistance
     *            the minimum distance below base to start including entries
     * @param maxBaseDistance
     *            the maximum distance from base past which entries are excluded
     * @param chopAfter
     *            the set of subordinates entries whose subordinates are to be
     *            excluded
     * @param chopBefore
     *            the set of subordinates entries and their subordinates to
     *            exclude
     * @param refinement
     *            the filter expression only composed of objectClass attribute
     *            value assertions
     */
    public BaseSubtreeSpecification( LdapDN base, int minBaseDistance, int maxBaseDistance, 
        Set<LdapDN> chopAfter, Set<LdapDN> chopBefore, ExprNode refinement )
    {
        this.base = base;
        this.minBaseDistance = minBaseDistance;

        if ( maxBaseDistance < 0 )
        {
            this.maxBaseDistance = UNBOUNDED_MAX;
        }
        else
        {
            this.maxBaseDistance = maxBaseDistance;
        }

        this.chopAfter = chopAfter;
        this.chopBefore = chopBefore;
        this.refinement = refinement;
    }


    // -----------------------------------------------------------------------
    // A C C E S S O R S
    // -----------------------------------------------------------------------


    public LdapDN getBase()
    {
        return this.base;
    }


    public Set<LdapDN> getChopBeforeExclusions()
    {
        return this.chopBefore;
    }


    public Set<LdapDN> getChopAfterExclusions()
    {
        return this.chopAfter;
    }


    public int getMinBaseDistance()
    {
        return this.minBaseDistance;
    }


    public int getMaxBaseDistance()
    {
        return this.maxBaseDistance;
    }


    public ExprNode getRefinement()
    {
        return this.refinement;
    }


    /**
     * Converts this item into its string representation as stored
     * in directory.
     *
     * @param buffer the string buffer
     */
    public void printToBuffer( StringBuilder buffer )
    {
        buffer.append( '{' );

        if(!base.isEmpty()) {
            buffer.append( ' ' );
            buffer.append( "base" );
            buffer.append( ' ' );
            buffer.append( '"' );
            buffer.append( base.getUpName() );
            buffer.append( '"' );
            buffer.append( ',' );
        }

        if(minBaseDistance > 0) {
            buffer.append( ' ' );
            buffer.append( "minimum" );
            buffer.append( ' ' );
            buffer.append( minBaseDistance );
            buffer.append( ',' );
        }

        if(maxBaseDistance > UNBOUNDED_MAX) {
            buffer.append( ' ' );
            buffer.append( "maximum" );
            buffer.append( ' ' );
            buffer.append( maxBaseDistance );
            buffer.append( ',' );
        }

        if ( !chopBefore.isEmpty() || !chopAfter.isEmpty() )
        {
            buffer.append( ' ' );
            buffer.append( "specificExclusions" );
            buffer.append( ' ' );
            buffer.append( '{' );

            for ( Iterator<LdapDN> it = chopBefore.iterator(); it.hasNext(); )
            {
                LdapDN dn = it.next();
                buffer.append( ' ' );
                buffer.append( "chopBefore" );
                buffer.append( ':' );
                buffer.append( ' ' );
                buffer.append( '"' );
                buffer.append( dn.getUpName() );
                buffer.append( '"' );

                if(it.hasNext())
                {
                    buffer.append( ',' );
                    buffer.append( ' ' );
                }
            }

            if ( !chopBefore.isEmpty() && !chopAfter.isEmpty() )
            {
                buffer.append( ',' );
                buffer.append( ' ' );
            }

            for ( Iterator<LdapDN> it = chopAfter.iterator(); it.hasNext(); )
            {
                LdapDN dn = it.next();
                buffer.append( ' ' );
                buffer.append( "chopAfter" );
                buffer.append( ':' );
                buffer.append( ' ' );
                buffer.append( '"' );
                buffer.append( dn.getUpName() );
                buffer.append( '"' );

                if(it.hasNext())
                {
                    buffer.append( ',' );
                    buffer.append( ' ' );
                }
            }

            buffer.append( ' ' );
            buffer.append( '}' );

            buffer.append( ',' );
        }

        if ( refinement != null )
        {
            buffer.append( ' ' );
            buffer.append( "specificationFilter" );
            buffer.append( ' ' );

            // The ExprNode could represent both, a refinement
            // or a filter. First we try to print the ExprNode
            // as refinement. If that fails it is printed as
            // LDAP filter.
            try
            {
                // Must use a tempBuffer here because the 
                // exception could occur after some characters
                // were added to the buffer.
            	StringBuilder tempBuffer = new StringBuilder();
                refinement.printRefinementToBuffer( tempBuffer );
                buffer.append( tempBuffer );
            }
            catch ( UnsupportedOperationException e )
            {
                buffer.append( refinement.toString() );
            }

            buffer.append( ',' );
        }

        if(buffer.charAt( buffer.length()-1 ) == ',') {
            buffer.deleteCharAt( buffer.length()-1 );
        }

        buffer.append( ' ' );
        buffer.append( '}' );
    }


}
