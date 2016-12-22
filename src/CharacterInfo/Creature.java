/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterInfo;

import Items.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Creature extends Character{
    
    private List<Item> itemDrop = new ArrayList<>();
    private List<Integer> dropChance = new ArrayList<>();
    
    public Creature(String name, int age) {
        super(name, age);
    }
    
}
