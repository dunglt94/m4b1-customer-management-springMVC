package service;

import DAO.CustomerDAO;
import DAO.ICustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService implements ICustomerService {
    ICustomerDAO customerDAO = new CustomerDAO();

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer findByID(int id) {
        return customerDAO.findByID(id);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void create(Customer customer) {
        customerDAO.create(customer);
    }

    @Override
    public void remove(int id) {
        customerDAO.remove(id);
    }
}
