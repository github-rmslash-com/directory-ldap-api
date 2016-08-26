/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.apache.directory.ldap.client.api.search;


/**
 * Creates a NOT filter
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
/* No qualifier*/final class UnaryFilter extends AbstractFilter
{
    /** The NOT filter */
    private Filter filter;


    /**
     * Creates a new instance of UnaryFilter.
     */
    private UnaryFilter()
    {
    }


    /**
     * Constructs a NOT filter 
     *
     * @return The constructed NOT Filter
     */
    public static UnaryFilter not()
    {
        return new UnaryFilter();
    }


    /**
     * Constructs a NOT filter with the associated inner Filter
     *
     * @param Filter The inner Filter
     * @return The constructed NOT Filter
     */
    public static UnaryFilter not( Filter filter )
    {
        UnaryFilter notFilter = not();
        notFilter.filter = filter;

        return notFilter;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder build( StringBuilder builder )
    {
        if ( filter == null )
        {
            throw new IllegalStateException( "filter not set" );
        }

        builder.append( "(" ).append( FilterOperator.NOT.operator() );
        filter.build( builder );

        return builder.append( ")" );
    }
}