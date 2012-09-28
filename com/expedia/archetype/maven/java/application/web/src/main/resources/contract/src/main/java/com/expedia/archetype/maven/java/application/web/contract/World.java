package com.expedia.archetype.maven.java.application.web.contract;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class World
{
    private String name;
    
    public World()
    {
        this( null );
    }
    
    public World( String name )
    {
        this.setName( name );
    }
    
    public void setName( String name )
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String toString()
    {
        return "this is your world " + this.getName();
    }
}
