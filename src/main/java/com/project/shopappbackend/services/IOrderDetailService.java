package com.project.shopappbackend.services;


import com.project.shopappbackend.dtos.OrderDetailDTO;
import com.project.shopappbackend.exceptions.DataNotFoundException;
import com.project.shopappbackend.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO newOrderDetail) throws Exception;
    OrderDetail getOrderDetail(Long id) throws DataNotFoundException;
    OrderDetail updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData)
            throws DataNotFoundException;
    void deleteById(Long id);
    List<OrderDetail> findByOrderId(Long orderId);


}
