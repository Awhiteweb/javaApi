package uk.co.white.coutts;

import com.google.gson.Gson;
import org.sql2o.Sql2o;
import org.sql2o.quirks.NoQuirks;
import uk.co.white.coutts.dao.Sql2oUserDao;
import uk.co.white.coutts.dao.UserDao;
import uk.co.white.coutts.model.User;

import java.util.HashMap;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        Sql2o sql2o = new Sql2o( "jdbc:mysql://localhost/users", "root", "password", new NoQuirks(  ) );
        UserDao userDao = new Sql2oUserDao( sql2o );
        Gson gson = new Gson();

        post( "/users", "application/json", ( req, res ) -> {
            User user = gson.fromJson( req.body(), User.class );
            userDao.addUser( user );
            res.status( 201 );
            return user;
        }, gson::toJson );

        get( "/users", "application/json", (req, res) -> userDao.getAllUsers(), gson::toJson );

        post( "/users/user", "application/json", ( req, res ) -> {
            HashMap<String, String> email = gson.fromJson( req.body(), HashMap.class );
            userDao.getUserByEmail( email.get( "email" ) );
            return email;
        }, gson::toJson );

        after((req, res ) -> res.type( "application/json" ));
    }
}
