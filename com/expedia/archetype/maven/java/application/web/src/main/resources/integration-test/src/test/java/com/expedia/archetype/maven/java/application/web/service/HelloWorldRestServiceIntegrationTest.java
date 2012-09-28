package com.expedia.archetype.maven.java.application.web.service;

import java.net.URI;

import com.expedia.archetype.maven.java.application.web.client.restful.HelloWorldClient;
import com.expedia.archetype.maven.java.application.web.contract.Hello;
import com.expedia.archetype.maven.java.application.web.contract.World;
import com.expedia.archetype.maven.java.application.web.service.restful.HelloWorldServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
(
   locations = { "classpath:context-integration-test.xml" }
)
public class HelloWorldRestServiceIntegrationTest
{
    private URI helloWorldRestServiceUri;
    
    // grrr junit requires test classes to have one empty constructor :( so please make setters public and inject via spring property instead of constructor arg :(  
    public HelloWorldRestServiceIntegrationTest()
    {
    }
    
    public void setHelloWorldRestServiceUri( URI helloWorldRestServiceUri )
    {
        this.helloWorldRestServiceUri = helloWorldRestServiceUri;
    }
    
    @Test
    public void testEnvironment()
    {
        // define test data
        String name = "foo";
        
        // set controls
        Hello controlGetHelloResult = new HelloWorldServiceImpl().getHello( name );
        World controlGetWorldResult = new HelloWorldServiceImpl().getWorld( name );
        
        // test
        HelloWorldClient helloWorldClient = new HelloWorldClient( this.helloWorldRestServiceUri );
        Hello testGetHelloResult = helloWorldClient.getHello( name );
        World testGetWorldResult = helloWorldClient.getWorld( name );
        
        // verify results
        Assert.assertEquals( controlGetHelloResult, testGetHelloResult );
        Assert.assertEquals( controlGetWorldResult, testGetWorldResult );
    }
}

