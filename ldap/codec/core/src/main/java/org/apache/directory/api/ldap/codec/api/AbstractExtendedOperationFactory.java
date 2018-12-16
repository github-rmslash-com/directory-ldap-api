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
package org.apache.directory.api.ldap.codec.api;

import org.apache.directory.api.asn1.util.Asn1Buffer;
import org.apache.directory.api.ldap.model.message.ExtendedRequest;
import org.apache.directory.api.ldap.model.message.ExtendedResponse;

/**
 * A Factory to encode Extended Request and Response messages
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public abstract class AbstractExtendedOperationFactory implements ExtendedOperationFactory
{
    /** The LDAP codec responsible for encoding and decoding */
    protected LdapApiService codec;

    /**
     *
     * Creates a new instance of AbstractExtendedOperationFactory.
     *
     * @param codec The LdapApiService instance
     */
    protected AbstractExtendedOperationFactory( LdapApiService codec )
    {
        this.codec = codec;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeValue( Asn1Buffer buffer, ExtendedRequest extendedRequest )
    {
        // Nothing to do by default
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeValue( Asn1Buffer buffer, ExtendedResponse extendedResponse )
    {
        // Nothing to do by default
    }
}
