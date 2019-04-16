package com.example.demo.Repositories;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerSiteRepository extends CrudRepository<CustomerSite, Integer> {
    CustomerSite findCustomerSiteByCustSiteId(int custSiteId);

    // Retrieve all ACTIVE customer sites and order by Customer name then Customer Number
    @Query(value = "select * from CUSTOMER_SITE where cust_site_status_id = 1 \n" +
            "Order by cust_site_name, cust_site_number;", nativeQuery = true)
    List<CustomerSite> findActiveCustomerSiteOrderByNameThenNumber();

    List<CustomerSite> findAllByOrderByCustSiteId();

}
