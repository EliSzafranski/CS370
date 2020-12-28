# Assignment 5
### Design Information
 1.  A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples). 
	 1. A **User** class can create **GroceryList** objects, and can access the **GroceryList** methods ```addItemToList(itemID, quantity)```, ```removeItemFromList(listID, itemID)``` and ``setQuantity(quantity)``. I added an association class, **ListItem**, which is connected to the association between **GroceryList** and **Item**;  this is the class which stores the quantity and checkStatus of the **Item** in the **GroceryList**, and as such we can modify it's quantity. 
2. The application must contain a database (DB) of ​items​ and corresponding ​item types​.  
		2. The **Item** class and the **ItemType** class are both created in the design
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.  
		3. The **GroceryList** class has a method ``addItemByItemType(itemType)`` where a **User** firsts selects an **ItemType** which then calls **ItemType**'s method ``getAllItemsOfItemType()`` to choose a specific **Item**. The **ListItem** class then calls its own ``setQuantity(quantity)`` to set the amount of an **Item** the **User** wishes to add. 
4.  Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.  
		4. The method ```addItemByName(itemName)``` allows a user to add an **Item** to the **GroceryList** by name. If that fails, then the function ```addNewItemToDB()``` is called, and prompts the user to choose an **ItemType** and adds that to the Database along with the name
5. Lists must be saved automatically and immediately after they are modified.  
		5. This is automatically taken care of by the program since if the variables are modified, the changes are immediate 
6.  Users must be able to check off items in a list (without deleting them)  
		6. The Association Class **ListItem** has a boolean attribute ``isChecked`` which can be modified using the methods ``checkItem()`` and ``uncheckItem()``
7.  Users must also be able to clear all the check-off marks in a list at once.  
		7. There is a method ``clearAllCheckMarks()`` in the **GroceryList** class which iterates through the **ListItems** in said list and calls their respective ``uncheckItem()`` methods. 
8.  Check-off marks for a list are persistent and must also be saved immediately.  
		8. This is automatically taken care of by the program (by virtue of the value being stored in a boolean variable)
9.  The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).  
		9. The **GroceryList** class has a method ``groupByItemType()`` which iterates through its own **Items** and groups them by **ItemType**. The front-end take care of showing it
10.  The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.   
		10. The relationship between **User** and **GroceryList** has a multiplicity of (0...*), meaning a single **User** can create multiple **GroceryLists**. **User** has the methods ``createList()``,  ``deleteList(listID)``, and ``getList(listId)``. The last of these is used to get a specific **GroceryList** and invoke that **GroceryLists**'s own ``updateListName()`` method. 
11. The User Interface (UI) must be intuitive and responsive.  
		11. Our front-end engineers are working on showing the app to beta testers of different age ranges and communities all over the world to ensure that each one can easily understand how to use the app 
