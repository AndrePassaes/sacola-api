package com.naztech.sacola.service.impl;

import com.naztech.sacola.enumeration.FormaPagamento;
import com.naztech.sacola.model.Item;
import com.naztech.sacola.model.Restaurante;
import com.naztech.sacola.model.Sacola;
import com.naztech.sacola.repository.ItemRepository;
import com.naztech.sacola.repository.ProdutoRepository;
import com.naztech.sacola.repository.SacolaRepository;
import com.naztech.sacola.resource.dto.ItemDto;
import com.naztech.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;
    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola está fechada.");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Esse produto não existe!");
                        }
                ))
                .build();

        List<Item> itensDaScola = sacola.getItens();
        if (itensDaScola.isEmpty()) {
            itensDaScola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaScola.get(0).getProduto().getRestaurante();
        }

        List<Double> valorDosItens = new ArrayList<>();
        for (Item itemDaSacola: itensDaScola) {
            double valorTotalItem =
                    itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);
        return itemParaSerInserido;
    }
//    @Override
//    public excluirItemNaSacola(ItemDto itemDto){
//        Sacola sacola = verSacola(itemDto.getSacolaId());
//
//        if (sacola.isFechada()) {
//            throw new RuntimeException("Esta sacola está fechada.");
//        }
//
//        Item itemParaSerExcluido = Item.builder()
//                .quantidade(itemDto.getQuantidade())
//                .sacola(sacola)
//                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
//                        () -> {
//                            throw new RuntimeException("Sacola está vazia!");
//                        }
//                ))
//                .build();
//
//        List<Item> itensDaScola = sacola.getItens();
//        if (itensDaScola.isEmpty()) {
//            itensDaScola.remove(itemParaSerExcluido);
//        } else {
//            Restaurante restauranteAtual = itensDaScola.get(0).getProduto().getRestaurante();
//            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
//            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
//                itensDaScola.add(itemParaSerInserido);
//            } else {
//                throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes. Feche a sacola ou esvazie.");
//            }
//        }
//
//        return itemParaSerExcluido;
//    }
    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não existe!");
                }
        );
    }
    @Override
    public Sacola fecharSacola(Long id, int numeroformaPagamento) {
        Sacola sacola = verSacola(id);

        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua ítens na sacola!");
        }
    /*if (numeroformaPagamento == 0) {
      sacola.setFormaPagamento(FormaPagamento.DINHEIRO);
    } else {
      sacola.setFormaPagamento(FormaPagamento.MAQUINETA);
    }*/
        FormaPagamento formaPagamento =
                numeroformaPagamento == 0 ? FormaPagamento.PIX : FormaPagamento.CARTAO_DE_CREDITO;
        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }
}