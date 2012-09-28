package com.expedia.archetype.maven.java.application.web.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.expedia.archetype.maven.java.application.web.contract.HelloWorldService;
import com.expedia.archetype.maven.java.application.web.contract.World;
import com.expedia.archetype.maven.java.application.web.contract.Hello;
//import com.expedia.commons.debug.DebugUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldServiceImpl implements HelloWorldSoapService
{
    private Logger logger;
    
    public HelloWorldServiceImpl()
    {
        this( null );
    }

    public HelloWorldServiceImpl( Logger logger )
    {
        this.setLogger( logger );
    }
    
    public void setLogger( Logger logger )
    {
        this.logger = ( logger == null ? LoggerFactory.getLogger( this.getClass() ) : logger );
    }

    private Logger getLogger()
    {
        return this.logger;
    }
    
    public Hello getHello( String name )
    {
        Hello hello = new Hello( name + "\nDon't forget to check the logs for a remote invocation of getWorld() to demonstrate logging correlation id transport :)" );

        return hello;
    }
    
    public World getWorld( String name )
    {
        World world = new World( name );
        
        return world;
    }
}

