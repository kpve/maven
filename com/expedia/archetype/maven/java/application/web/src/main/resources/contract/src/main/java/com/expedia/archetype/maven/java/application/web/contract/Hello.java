package com.expedia.archetype.maven.java.application.web.contract;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hello
{
    private String name;
    
    public Hello()
    {
        this( null );
    }
    
    public Hello( String name )
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
        return "hello " + this.getName();
    }
}
