package com.soten.eatgo.menu.controller;

import com.soten.eatgo.menu.domain.MenuItem;
import com.soten.eatgo.menu.service.MenuItemService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemController {

    private MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId,
                             @RequestBody List<MenuItem> menuItems) {

        menuItemService.bulkUpdate(restaurantId, menuItems);
        return "";
    }
}
