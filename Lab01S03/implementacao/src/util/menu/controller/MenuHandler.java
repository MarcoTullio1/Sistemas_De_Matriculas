package util.menu.controller;

import util.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuHandler {
    Map<String, Menu> menus;
    String path;

    public MenuHandler(String path){
        menus = new HashMap<>();
    }

    public Map<String, Menu> getMenus() {
        return menus;
    }
}
