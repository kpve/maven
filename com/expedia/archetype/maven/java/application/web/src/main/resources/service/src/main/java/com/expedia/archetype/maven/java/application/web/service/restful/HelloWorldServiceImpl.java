package com.expedia.archetype.maven.java.application.web.service.restful;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.expedia.archetype.maven.java.application.web.contract.HelloWorldService;
import com.expedia.archetype.maven.java.application.web.contract.World;
import com.expedia.archetype.maven.java.application.web.client.restful.HelloWorldClient;
import com.expedia.archetype.maven.java.application.web.contract.Hello;
//import com.expedia.commons.debug.DebugUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldServiceImpl implements HelloWorldService
{
    private Logger      logger;
    private String      serviceUri;
    
    public HelloWorldServiceImpl()
    {
        this( null );
    }
    
    public HelloWorldServiceImpl( String serviceUri )
    {
        this( null, serviceUri );
    }
    
    public HelloWorldServiceImpl( Logger logger, String serviceUri )
    {
        this.setLogger     ( logger     );
        this.setServiceUri ( serviceUri );
    }
    
    private void setLogger( Logger logger )
    {
        if( logger == null )
        {
            this.logger = LoggerFactory.getLogger( this.getClass() );
            this.logger.warn( "Undefined logger provided, substituing default: " + this );
        }
        else
        {
            this.logger = logger;
        }
    }

    private Logger getLogger()
    {
        return this.logger;
    }

    public String getServiceUri()
    {
        return this.serviceUri;
    }

    private void setServiceUri( String serviceUri )
    {
        this.serviceUri = serviceUri;
    }

    private void httpGetWorld( String name )
    {
        try
        {
            // get the world resource
            URI worldServiceURI = new URI( this.getServiceUri() );
            HelloWorldClient helloWorldClient =  new HelloWorldClient( worldServiceURI );
            World world = helloWorldClient.getWorld( name );
            this.getLogger().info( "world response: " + world );
            world = new World( name );                        
        }
        catch( Exception e  )
        {
            this.getLogger().error( "Remote invocation of world operation at " + this.getServiceUri() + " failed: \n " + e.getMessage() );// TODO dependency not available yet DebugUtilities.getStackTraceAsString( e ) );
        }
    }
    
    @GET
    @Path( "/hello/{name}" )
    @Produces("application/xml")
    @Override
    public Hello getHello( @PathParam( "name" )String name )
    {
        Hello hello = new Hello( name );
        
        // demonstrate invoking one rest service from another
        this.httpGetWorld( name );
        
        return hello;
    }

    @GET
    @Path( "/world/{name}" )
    @Produces("application/xml")
    @Override
    public World getWorld( @PathParam( "name" )String name )
    {
        World world = new World( name );
        
        return world;
    }
}


