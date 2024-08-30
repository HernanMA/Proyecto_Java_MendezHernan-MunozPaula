/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Dish;
import com.mycompany.model.DishDAO;
import com.mycompany.model.DishIngredient;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hernan
 */
public class DishController {
    private DishDAO dishDAO;

    public DishController(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public void loadDishesData(DefaultTableModel tableModel) {
        List<Dish> dishes = dishDAO.getAllDishes();
        tableModel.setRowCount(0);

        for (Dish dish : dishes) {
            tableModel.addRow(new Object[]{
                dish.getId(),
                dish.getRestaurantId(),
                dish.getName(),
                dish.getPrice(),
                dish.getType(),
                dish.getPreparationTime()
            });
        }
    }

    public void loadDishIngredientsData(DefaultTableModel tableModel) {
        List<DishIngredient> ingredients = dishDAO.getAllDishIngredients();
        tableModel.setRowCount(0);

        for (DishIngredient ingredient : ingredients) {
            tableModel.addRow(new Object[]{
                ingredient.getDishId(),
                ingredient.getIngredientId(),
                ingredient.getQuantityRequired()
            });
        }
    }
}