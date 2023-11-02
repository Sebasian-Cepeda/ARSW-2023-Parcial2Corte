/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.myrestaurant.restcontrollers;

import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.model.ProductType;
import edu.eci.arsw.myrestaurant.model.RestaurantProduct;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/orders")
public class OrdersAPIController {

    @Autowired
    RestaurantOrderServicesStub restaurantService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrders() {
        
       
        try {
            Set<Order> resp = new HashSet<>();
            Order no = new Order(1);
            resp.add(no);

            Order[] array = new Order [this.restaurantService.getTablesWithOrders().size()];

            array[0] = this.restaurantService.getTableOrder(1) ;

            JSONObject jsonObject = new JSONObject(array[0]);
            String codificado = jsonObject.toString();
            System.out.println("json: " + codificado);
            return new ResponseEntity<Set<Integer>>(this.restaurantService.getTablesWithOrders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
