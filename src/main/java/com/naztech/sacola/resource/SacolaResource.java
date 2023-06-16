package com.naztech.sacola.resource;

import com.naztech.sacola.model.Item;
import com.naztech.sacola.model.Sacola;
import com.naztech.sacola.resource.dto.ItemDto;
import com.naztech.sacola.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(value="/nazcookies/sacolas")
@RestController
@RequestMapping("/nazcookies/sacolas")
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {

        return sacolaService.incluirItemNaSacola(itemDto);
    }

//    @DeleteMapping
//    public Item excluirItemNaSacola(@RequestBody ItemDto itemDto) {
//
//        return sacolaService.excluirItemNaSacola(itemDto);
//    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(sacolaId, formaPagamento);
    }





}