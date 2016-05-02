package uk.co.white.coutts.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import uk.co.white.coutts.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex on 01/05/2016.
 */
public class Sql2oUserDao implements UserDao
{
    private final Sql2o sql2o;

    public Sql2oUserDao( Sql2o sql2o )
    {
        this.sql2o = sql2o;
    }

    public void addUser( User user )
    {
        String sql = "INSERT INTO Users( name, email ) VALUES( :name, :email );";
        try( Connection conn = sql2o.open() )
        {
            int id = (int) conn.createQuery( sql ).bind( user ).executeUpdate().getKey();
            user.setId( id );
        }
        catch( Sql2oException e )
        {
            System.out.println( e.getMessage() );
        }
    }

    public List<User> getAllUsers()
    {
        String sql = "SELECT * FROM users";
        try( Connection conn = sql2o.open() )
        {
            return conn.createQuery( sql ).executeAndFetch( User.class );
        }
        catch( Sql2oException e )
        {
            System.out.println( e.getMessage() );
        }
        return null;
    }

    public List<User> getUser( int id )
    {
        return null;
    }

    public List<User> getUserByEmail( String email )
    {
        return Arrays.asList( new User( "Alex", "alex@email.com" ) );
    }
}
