package uk.co.white.coutts.dao;

import uk.co.white.coutts.model.User;

import java.util.List;

public interface UserDao
{
    void addUser( User user );

    List<User> getAllUsers();

    List<User> getUser( int id );

    List<User> getUserByEmail( String email );
}