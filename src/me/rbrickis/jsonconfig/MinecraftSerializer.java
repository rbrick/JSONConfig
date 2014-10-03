package me.rbrickis.jsonconfig;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface MinecraftSerializer {

    /**
     * Serialize a item in the JSON format.
     * 
     * @param item
     * @return 
     */
    String serializeItem(ItemStack item);
    

    /**
     * Serialize a entity in the JSON format.
     * 
     * @param entity
     * @return 
     */    
    String serializeEntity(Entity entity);
    
    /**
     * Serializes an ItemStack array in the JSON format.
     * 
     * @param items
     * @return 
     */
    String serializeItemArray(ItemStack[] items);
    
    /**
     * Serializes an Inventory in the JSON format.
     * 
     * @param inv
     * @return 
     */
    String serializeInventory(Inventory inv);
    
    /**
     * Deserialize a {@link java.lang.Object} from a JSON String
     * 
     * @param <T>
     * @param obj
     * @return 
     */
    <T> T deserialize(Object obj);
}
