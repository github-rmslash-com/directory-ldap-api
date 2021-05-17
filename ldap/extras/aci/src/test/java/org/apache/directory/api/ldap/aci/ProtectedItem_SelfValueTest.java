/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    https://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.api.ldap.aci;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.apache.directory.api.ldap.aci.protectedItem.SelfValueItem;
import org.apache.directory.api.ldap.model.schema.AttributeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * Unit tests class ProtectedItem.SelfValue.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
@Execution( ExecutionMode.CONCURRENT)
public class ProtectedItem_SelfValueTest
{
    SelfValueItem selfValueA;
    SelfValueItem selfValueACopy;
    SelfValueItem selfValueB;
    SelfValueItem selfValueC;


    /**
     * Initialize name instances
     */
    @BeforeEach
    public void initNames() throws Exception
    {
        Set<AttributeType> colA = new HashSet<AttributeType>();
        colA.add( new AttributeType( "aa" ) );
        colA.add( new AttributeType( "bb" ) );
        colA.add( new AttributeType( "cc" ) );
        Set<AttributeType> colB = new HashSet<AttributeType>();
        colB.add( new AttributeType( "aa" ) );
        colB.add( new AttributeType( "bb" ) );
        colB.add( new AttributeType( "cc" ) );
        Set<AttributeType> colC = new HashSet<AttributeType>();
        colC.add( new AttributeType( "bb" ) );
        colC.add( new AttributeType( "cc" ) );
        colC.add( new AttributeType( "dd" ) );

        selfValueA = new SelfValueItem( colA );
        selfValueACopy = new SelfValueItem( colA );
        selfValueB = new SelfValueItem( colB );
        selfValueC = new SelfValueItem( colC );
    }


    @Test
    public void testEqualsNull() throws Exception
    {
        assertFalse( selfValueA.equals( null ) );
    }


    @Test
    public void testEqualsReflexive() throws Exception
    {
        assertEquals( selfValueA, selfValueA );
    }


    @Test
    public void testHashCodeReflexive() throws Exception
    {
        assertEquals( selfValueA.hashCode(), selfValueA.hashCode() );
    }


    @Test
    public void testEqualsSymmetric() throws Exception
    {
        assertEquals( selfValueA, selfValueACopy );
        assertEquals( selfValueACopy, selfValueA );
    }


    @Test
    public void testHashCodeSymmetric() throws Exception
    {
        assertEquals( selfValueA.hashCode(), selfValueACopy.hashCode() );
        assertEquals( selfValueACopy.hashCode(), selfValueA.hashCode() );
    }


    @Test
    public void testEqualsTransitive() throws Exception
    {
        assertEquals( selfValueA, selfValueACopy );
        assertEquals( selfValueACopy, selfValueB );
        assertEquals( selfValueA, selfValueB );
    }


    @Test
    public void testHashCodeTransitive() throws Exception
    {
        assertEquals( selfValueA.hashCode(), selfValueACopy.hashCode() );
        assertEquals( selfValueACopy.hashCode(), selfValueB.hashCode() );
        assertEquals( selfValueA.hashCode(), selfValueB.hashCode() );
    }


    @Test
    public void testNotEqualDiffValue() throws Exception
    {
        assertFalse( selfValueA.equals( selfValueC ) );
        assertFalse( selfValueC.equals( selfValueA ) );
    }
}
