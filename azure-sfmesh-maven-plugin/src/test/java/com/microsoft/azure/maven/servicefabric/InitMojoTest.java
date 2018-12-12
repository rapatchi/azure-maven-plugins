/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.maven.servicefabric;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class InitMojoTest extends MeshMojoTestBase
{

    private InitMojo mojo = null;
    private InitMojo mojoSpy = null;
    @Before
    public void setUp() throws Exception {
    	mojoName = "init";
        mojo = (InitMojo) getMojoFromPom(mojoName);
        mojoSpy = spy(mojo);
        spy(Utils.class);
        Files.createDirectory(Paths.get("./target/" + mojoName));
    }

    @After
    public void cleanUp() throws Exception{
    	FileUtils.deleteDirectory(new File(Paths.get("./target/" + mojoName).toString()));
    }
    
    @Test
    public void testInitMojo() throws Exception{
        final Settings settings = new Settings();
        final Log logger = (Log) rule.getVariableValueFromObject(mojoSpy, "logger");
        final MavenProject project = (MavenProject) rule.getVariableValueFromObject(mojoSpy, "project");
        doReturn((Paths.get("./target/"+mojoName + "/servicefabric").toString())).when(Utils.class, "getServicefabricResourceDirectory", logger, project);
        rule.setVariableValueToObject(mojoSpy, "settings", settings);
        rule.setVariableValueToObject(mojoSpy, "schemaVersion", "1.0.0-preview2");
        rule.setVariableValueToObject(mojoSpy, "applicationName", "sfmeshtest");
        rule.setVariableValueToObject(mojoSpy, "applicationDescription", "Serivce Fabric Mesh init goal test");
        rule.setVariableValueToObject(mojoSpy, "allowTelemetry", false);
        mojoSpy.doExecute();
    }
}