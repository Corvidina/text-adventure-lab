import java.io.*;
import java.util.*;

public class Player
{
   private Room currentRoom;
   private int health;
   private Set<String> accomplishedTasks;
   private Map<String,Integer> inventory;
   private boolean includeSleeps;

   public Player(Room room) {
      currentRoom = room;
      health = 100;
      includeSleeps = true;
      inventory = new TreeMap<>();
      accomplishedTasks = new TreeSet<>();
   }

   public void setIncludeSleeps(boolean b){
      includeSleeps = b;
   }

   public Room getCurrentRoom(){
      return currentRoom;
   }

   public int getHealth(){
      return health;
   }

   public void printInventory(){
      if(!inventory.isEmpty()){
         for(String key : inventory.keySet())
            System.out.println(inventory.get(key)+" "+key);
      } else
         System.out.println("You have nothing");
   }

   public boolean hasItem(String str){
      if(str==null)
         return true;
      return inventory.containsKey(str);
   }

   public boolean hasItem(String str, int num){
      if(str==null)
         return true;
      return inventory.containsKey(str) && inventory.get(str)>=num;
   }

   public boolean hasRequiredTask(String task){
      if(task == null)
         return true;
      else {
         return accomplishedTasks.contains(task);
      }
   }

   public void performAction(String str){
      Room room = currentRoom;
      PrintStream stream = System.out;
      PrintStream dummyStream = new PrintStream(new OutputStream() {
         public void write(int b) throws IOException {
            // none
         }
      });
      if (currentRoom.getAction(str) == null) {
         System.out.println("You cannot " + str);
         return;
      }
      if (!hasItem(currentRoom.getAction(str).getRequiredItem(), currentRoom.getAction(str).getNumRequiredItem()) || (!hasRequiredTask(currentRoom.getAction(str).getRequiredTasks()) && !currentRoom.getAction(str).getUnlessTask()) || ((hasRequiredTask(currentRoom.getAction(str).getRequiredTasks()) && currentRoom.getAction(str).getUnlessTask()))) {
         System.out.println("You cannot " + str);
         return;
      }
      if(currentRoom.getAction(str).getMillis()!=0 && currentRoom.getAction(str).getPreMessage()!=null)
         System.out.println(currentRoom.getAction(str).getPreMessage());
      try {
         if(includeSleeps)
            Thread.sleep(currentRoom.getAction(str).getMillis());
         if(currentRoom.getAction(str).getActionsPerformed()!=null)
            if(str.equals("sell all")){
               int most = 0;
               for(String key : inventory.keySet())
                  if(inventory.get(key)>most)
                     most = inventory.get(key);
               for(int j = 0; j < most; j++)
                  for(int i = 0; i < currentRoom.getAction(str).getActionsPerformed().length; i++){
                     System.setOut(dummyStream);
                     performAction(currentRoom.getAction(str).getActionsPerformed()[i]);
                     System.setOut(stream);
                  }
            } else for(int i = 0; i < currentRoom.getAction(str).getActionsPerformed().length; i++){
               System.setOut(dummyStream);
               performAction(currentRoom.getAction(str).getActionsPerformed()[i]);
               System.setOut(stream);
            }
         currentRoom.getAction(str).randomizeIndex();
         //if(str.equals("use ultimate rod") )
            //currentRoom.getAction(str).setIndex(46);
         if (str.equals("use ultimate rod") && includeSleeps && currentRoom.getAction(str).getIndex()==46){
            System.out.println("Quick! Brace yourself by typing in \"brace\" or else the fish is going to pull you over into the ocean!");
            Scanner in = new Scanner(System.in);
            long beg = System.currentTimeMillis();
            if(in.nextLine().equals("brace")){
               if(System.currentTimeMillis()-beg > 5000){
                  System.out.println("You weren't quick enough and got pulled out of the boat and eaten.");
                  health = 0;
               } else {
                  System.out.println("You braced yourself, but you're line snapped and you didn't get the fish.");
                  if(inventory.get("ultimate fishing rod")>1)
                     inventory.put("ultimate fishing rod", inventory.get("ultimate fishing rod")-1);
                  else
                     inventory.remove("ultimate fishing rod");
               }
            } else {
               System.out.println("You didn't brace yourself and got pulled out of the boat and eaten.");
               health = 0;
            }
            return;
         }
         if (currentRoom.getAction(str).getGainedItem() != null) {
            if (hasItem(currentRoom.getAction(str).getGainedItem())) {
               int num = inventory.get(currentRoom.getAction(str).getGainedItem());
               num += currentRoom.getAction(str).getNumGainedItem();
               inventory.remove(currentRoom.getAction(str).getGainedItem());
               inventory.put(currentRoom.getAction(str).getGainedItem(), num);
            } else
               inventory.put(currentRoom.getAction(str).getGainedItem(), currentRoom.getAction(str).getNumGainedItem());
         }
         if (!currentRoom.getAction(str).canReuseItem()) {
            int v = inventory.get(currentRoom.getAction(str).getRequiredItem()) - currentRoom.getAction(str).getNumRequiredItem();
            if (v == 0)
               inventory.remove(currentRoom.getAction(str).getRequiredItem());
            else
               inventory.replace(currentRoom.getAction(str).getRequiredItem(), v);
         }
         if (currentRoom.getAction(str).getGrantedTask() != null)
            accomplishedTasks.add(currentRoom.getAction(str).getGrantedTask());
         if (currentRoom.getAction(str).getMessage() != null)
            System.out.println(currentRoom.getAction(str).getMessage());
         if (health + currentRoom.getAction(str).getHealthChange() >= 100)
            health = 100;
         else if (health+currentRoom.getAction(str).getHealthChange()<0)
            this.health=0;
         else
            health += currentRoom.getAction(str).getHealthChange();
         if (currentRoom.getAction(str).getNextRoom() != null)
            currentRoom = currentRoom.getAction(str).getNextRoom();
         if (!room.getAction(str).canRepeatAction())
            room.removeAction(str);
      } catch (Exception ignored) {

      }
   }
}