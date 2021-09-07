package com.moslite.orderbackend.services;

import com.moslite.orderbackend.domain.ItemPedido;
import com.moslite.orderbackend.domain.PagamentoComBoleto;
import com.moslite.orderbackend.domain.Pedido;
import com.moslite.orderbackend.domain.enums.EstadoPagamento;
import com.moslite.orderbackend.repositories.ItemPedidoRepository;
import com.moslite.orderbackend.repositories.PagamentoRepository;
import com.moslite.orderbackend.repositories.PedidoRepository;
import com.moslite.orderbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Pedido buscar(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " +
                Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
        }

        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido item : obj.getItens()) {
            item.setProduto(produtoService.buscar(item.getProduto().getId()));
            item.setDesconto(0.0);
            item.setPreco(item.getProduto().getPreco());
            item.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        System.out.println(obj);
        return obj;
    }

}
