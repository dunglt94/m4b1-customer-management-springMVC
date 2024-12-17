package DAO;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcURL = "jdbc:mysql://localhost:3306/customer_management";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcUsername = "root";
    @SuppressWarnings("FieldCanBeLocal")
    private final String jdbcPassword = "123456";

    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String INSERT_CUSTOMER = "call insert_customer(?,?,?)";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String UPDATE_CUSTOMER = "call update_customer(?,?,?,?)";
    private static final String DELETE_CUSTOMER = "delete from customers where id = ?";


    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findByID(int id) {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println(preparedStatement);
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    customer = new Customer(id, name, email, address);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(UPDATE_CUSTOMER)) {
            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getEmail());
            callableStatement.setString(3, customer.getAddress());
            callableStatement.setInt(4, customer.getId());
            callableStatement.executeUpdate();
            System.out.println(callableStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    public void create(Customer customer) {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(INSERT_CUSTOMER)) {
            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getEmail());
            callableStatement.setString(3, customer.getAddress());
            callableStatement.executeUpdate();
            System.out.println(callableStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
