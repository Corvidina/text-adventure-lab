import java.util.Map;
import java.util.TreeMap;

public class Room
{
   private String roomName;
   private Map<String, Action> actions;

   public Room(String roomName){
      this.roomName=roomName;
      actions = new TreeMap<String, Action>();
   }

   public void addAction(String key, Action action){
      actions.put(key, action);
   }

   public Action removeAction(String key){
      return actions.remove(key);
   }

   public void print(Player player){
      System.out.println("Health: " + player.getHealth());
      System.out.println("You are in " + roomName);
      for(String key : actions.keySet()){
         if (actions.get(key).getMessage()!=null && actions.get(key).getMessage().equals("admin"));
         else if (player.hasItem(actions.get(key).getRequiredItem(), actions.get(key).getNumRequiredItem()))
            if(player.hasRequiredTask(actions.get(key).getRequiredTasks()) && !actions.get(key).getUnlessTask())
               System.out.println("You can " + key);
            else if (!player.hasRequiredTask(actions.get(key).getRequiredTasks()) && actions.get(key).getUnlessTask())
               System.out.println("You can " + key);
      }
   }

   public Action getAction(String key){
      return actions.get(key);
   }


}