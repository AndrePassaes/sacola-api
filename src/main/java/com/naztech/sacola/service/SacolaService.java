package com.naztech.sacola.service;

import com.naztech.sacola.model.Item;
import com.naztech.sacola.model.Sacola;
import com.naztech.sacola.resource.dto.ItemDto;


public interface SacolaService {
    Item incluirItemNaSacola(ItemDto itemDto);
//    Item excluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
}
