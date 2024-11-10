package Repository;

import Model.Book;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import java.util.Optional;

public class BookRepositoryMySQL implements BookRepository {
    private final Connection connection;

    public BookRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM book;";

        List<Book> books = new ArrayList<>();

        try {
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                //books.add(getBookFromResultSet(resultSet));
                Long id = resultSet.getLong("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                Date publishedDate = resultSet.getDate("publishedDate");

                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");

                Book book = new Book();

                book.setId(id);
                book.setAuthor(author);
                book.setTitle(title);
                book.setPublishedDate(publishedDate.toLocalDate());

                book.setAmount(amount);
                book.setPrice(price);

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public Optional<Book> findByTitle(String title1) {
        String sql = "SELECT * FROM book WHERE title = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                Date publishedDate = resultSet.getDate("publishedDate");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");

                Book book = new Book();

                book.setId(id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublishedDate(publishedDate.toLocalDate());
                book.setAmount(amount);
                book.setPrice(price);

                return Optional.of(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = "SELECT * FROM book WHERE id = " + id;

        //Optional<Book> book = Optional.empty();

        try {
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                //book = Optional.of(getBookFromResultSet(resultSet));
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                Date publishedDate = resultSet.getDate("publishedDate");

                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");

                Book book = new Book();

                book.setId(id);
                book.setAuthor(author);
                book.setTitle(title);
                book.setPublishedDate(publishedDate.toLocalDate());

                book.setAmount(amount);
                book.setPrice(price);

                return Optional.of(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return book;
        return Optional.empty();
    }

    @Override
    public boolean updateAmount(String title, int newAmount) {
        String sql = "UPDATE book SET amount = ? WHERE title = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAmount);
            preparedStatement.setString(2, title);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(Book book) {
        //String newSql = "INSERT INTO book VALUES(null, \'" + book.getAuthor() +"\', \'" + book.getTitle()+"\', \'" + book.getPublishedDate() + "\' );";
        String newSql = "INSERT INTO book VALUES(null,?,?,?,?,?);";

        try{
            //Statement statement = connection.createStatement();
            //statement.executeUpdate(newSql);
            PreparedStatement preparedStatement = connection.prepareStatement(newSql);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(book.getPublishedDate()));

            preparedStatement.setInt(4, book.getAmount());
            preparedStatement.setDouble(5, book.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted == 1;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        //return true;
    }

    @Override
    public boolean delete(Book book) {
        //String newSql = "DELETE FROM book WHERE author=\'" + book.getAuthor() +"\' AND title=\'" + book.getTitle()+"\';";
        String newSql = "DELETE FROM book WHERE author = ? AND title = ?";

        try{
            //Statement statement = connection.createStatement();
            //statement.executeUpdate(newSql);
            PreparedStatement preparedStatement = connection.prepareStatement(newSql);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted == 1;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        //return true;
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM book WHERE id >= 0;";
        //String sql = "DELETE FROM book";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


//    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
//        return new BookBuilder()
//                .setId(resultSet.getLong("id"))
//                .setTitle(resultSet.getString("title"))
//                .setAuthor(resultSet.getString("author"))
//                .setPublishedDate(new java.sql.Date(resultSet.getDate("publishedDate").getTime()).toLocalDate())
//                .build();
//    }
}
