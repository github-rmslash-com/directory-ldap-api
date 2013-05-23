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
package org.apache.directory.api.ldap.extras.controls.syncrepl_impl;


import org.apache.directory.api.asn1.ber.AbstractContainer;
import org.apache.directory.api.ldap.codec.api.LdapApiService;
import org.apache.directory.api.ldap.extras.controls.SyncDoneValue;


/**
 * 
 * ASN.1 container for SyncDoneValueControl.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class SyncDoneValueContainer extends AbstractContainer
{
    /** syncDoneValue*/
    private SyncDoneValue control;

    private LdapApiService codec;


    /**
     * 
     * Creates a new SyncDoneValueControlContainer object.
     *
     */
    public SyncDoneValueContainer( LdapApiService codec )
    {
        super();
        this.codec = codec;
        this.control = new SyncDoneValueDecorator( codec );
        grammar = SyncDoneValueGrammar.getInstance();
        setTransition( SyncDoneValueStatesEnum.START_STATE );
    }


    /**
     * 
     * Creates a new SyncDoneValueControlContainer object.
     *
     */
    public SyncDoneValueContainer( LdapApiService codec, SyncDoneValue control )
    {
        super();
        this.codec = codec;
        this.control = control;
        grammar = SyncDoneValueGrammar.getInstance();
        setTransition( SyncDoneValueStatesEnum.START_STATE );
    }


    /**
     * @return the SyncDoneValueControlCodec object
     */
    public SyncDoneValue getSyncDoneValueControl()
    {
        return control;
    }


    /**
     * Set a SyncDoneValueControlCodec Object into the container. It will be completed
     * by the ldapDecoder.
     * 
     * @param control the SyncDoneValueControlCodec to set.
     */
    public void setSyncDoneValueControl( SyncDoneValue control )
    {
        this.control = control;
    }


    public LdapApiService getCodecService()
    {
        return codec;
    }


    /**
     * clean the container
     */
    @Override
    public void clean()
    {
        super.clean();
        control = null;
    }

}
