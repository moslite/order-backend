package com.moslite.orderbackend.repositories;

import com.moslite.orderbackend.domain.ItemPedido;
import com.moslite.orderbackend.domain.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
