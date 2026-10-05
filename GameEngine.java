import java.util.Scanner;

public class GameEngine
{
   public static void play(Player player)
   {
      long t = System.currentTimeMillis();
      Scanner in = new Scanner(System.in);
      
      while (player.getHealth() > 0)
      {
         Room room = player.getCurrentRoom();
         room.print(player);
         System.out.print("> ");
         String choice = in.nextLine();
         if (choice.equals("restart")){
            break;
         }
         if (choice.equals("inventory"))
            player.printInventory();
         else
            player.performAction(choice);
      }
      System.out.println("You died. Game over.");
      System.out.println("It took you " + (System.currentTimeMillis()-t)/1000 +"."+(System.currentTimeMillis()-t)%1000+" seconds");
   }
}