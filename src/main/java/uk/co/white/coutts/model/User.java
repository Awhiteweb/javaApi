package uk.co.white.coutts.model;

/**
 * Created by Alex on 01/05/2016.
 */
public class User
{
    private int id;
    private String name;
    private String email;

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        User user = (User) o;

        return email.equals( user.email );

    }

    @Override
    public int hashCode()
    {
        return email.hashCode();
    }

    public int getId()
    {

        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public User( String name, String email )
    {

        this.name = name;
        this.email = email;
    }
}
