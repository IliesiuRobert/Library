package Repository.User;

import Model.Builder.UserBuilder;
import Model.User;
import Model.Validator.Notification;
import Repository.Security.RightsRolesRepository;

import java.sql.*;
import java.util.*;

import static Database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {
    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;

    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

// implementeaza
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String fetchAllUsersSQL = "SELECT * FROM `" + USER + "`";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(fetchAllUsersSQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User user = new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setUsername(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(resultSet.getLong("id")))
                        .build();

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

// repara fetchUserSQL (repara concatenarea de string-uri)
    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();

        String fetchUserSQL = "SELECT * FROM `" + USER + "` WHERE `username` = ? AND `password` = ?";
        try {
            //Statement statement = connection.createStatement();
            //String fetchUserSql =
            //        "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";

            PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            //ResultSet userResultSet = statement.executeQuery(fetchUserSql);

            ResultSet userResultSet = preparedStatement.executeQuery();

            if (userResultSet.next())
            {
                User user = new UserBuilder()
                        .setId(userResultSet.getLong("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();

                findByUsernameAndPasswordNotification.setResult(user);
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid username or password!");
                //return findByUsernameAndPasswordNotification;
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database!");
        }

        return findByUsernameAndPasswordNotification;
    }

    //modifica codul de la register astfel incat sa verifice daca se afla email-ul in baza de date (video 3 => 1:17:00)
    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteUser(User user) {
        String newSql = "DELETE FROM user WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(newSql);
            preparedStatement.setString(1, user.getUsername());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    // repara concatenarea de string-uri
    @Override
    public boolean existsByUsername(String email) {
        String fetchUserSQL = "SELECT * FROM `" + USER + "` WHERE `username` = ?";

        try {
            //Statement statement = connection.createStatement();
            //String fetchUserSql =
            //        "Select * from `" + USER + "` where `username`=\'" + email + "\'";

            PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSQL);
            preparedStatement.setString(1, email);

            //ResultSet userResultSet = statement.executeQuery(fetchUserSql);

            ResultSet userResultSet = preparedStatement.executeQuery();

            return userResultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
