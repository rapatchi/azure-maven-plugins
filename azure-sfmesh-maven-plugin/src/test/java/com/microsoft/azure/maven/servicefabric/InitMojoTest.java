/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.maven.servicefabric;

import java.io.File;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.settings.Settings;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class InitMojoTest
{
    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {
        }

        @Override
        protected void after() {
        }
    };

    @Test
    public void testInitMojo() throws Exception{
        File testPom = new File(this.getClass().getResource("/pom.xml").toURI());
        assertNotNull(testPom);
        final InitMojo mojo = (InitMojo) rule.lookupMojo("init", testPom );
        final Settings settings = new Settings();
        PowerMockito.mockStatic(Utils.class);
        Mockito.when(Utils.getServicefabricResourceDirectory(null, null)).thenReturn("hello");
        rule.setVariableValueToObject(mojo, "settings", settings);
        rule.setVariableValueToObject(mojo, "schemaVersion", "1.0.0-preview2");
        rule.setVariableValueToObject(mojo, "applicationName", "sfmeshtest");
        rule.setVariableValueToObject(mojo, "applicationDescription", "Serivce Fabric Mesh init goal test");
        mojo.doExecute();
    }
}