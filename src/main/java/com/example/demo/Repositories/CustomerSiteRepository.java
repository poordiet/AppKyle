package com.example.demo.Repositories;

import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerSiteRepository extends CrudRepository<CustomerSite, Integer> {
    CustomerSite findCustomerSiteByCustSiteId(int custSiteId);

    List<CustomerSite> findAllByOrderByCustSiteId();

    @Query(value = "select * from CUSTOMER_SITE where CUSTOMER_SITE.cust_site_status_id <> 4 order by CUSTOMER_SITE.state_id, CUSTOMER_SITE.cust_site_status_id, CUSTOMER_SITE.cust_site_name, CUSTOMER_SITE.cust_site_number;", nativeQuery = true)
    List<CustomerSite> findCustomerSiteByABunch();
}
