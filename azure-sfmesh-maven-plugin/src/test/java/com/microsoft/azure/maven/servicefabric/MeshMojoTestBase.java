package com.microsoft.azure.maven.servicefabric;

import java.io.File;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;

public class MeshMojoTestBase {

	public String fileName = "/pom.xml";
	public String mojoName;

    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {
        }

        @Override
        protected void after() {
        }
    };

    protected Mojo getMojoFromPom(final String goal) throws Exception {
        final File pom = new File(this.getClass().getResource(fileName).toURI());
        return rule.lookupMojo(goal, pom);
    }
}
