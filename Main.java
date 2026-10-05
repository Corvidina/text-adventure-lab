import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        GameEngine.play(Game.gameA());
        //GameEngine.play(Game.gameB());
    }

    public static Player firstGame(){
        Room hub = new Room("a void devoid of...everything. Except you and some...bananas?");
        Room pond = new Room("a pond");

        Action a;
        a = new Action();
        a.setMessage("You ponder. To what end?");
        a.setCanRepeatAction(false);
        hub.addAction("ponder", a);

        a = new Action();
        a.setCanRepeatAction(false);
        a.gainItem("bananas");
        hub.addAction("grab bananas", a);

        a = new Action();
        a.setCanReuseItem(false);
        a.requireItem("bananas");
        a.setMessage("You throw the bananas and they travel unaffected by any other forces until eventually out of your sight");
        hub.addAction("throw bananas", a);

        a = new Action();
        a.setNextRoom(pond);
        a.setMessage("You appear at a pond");
        hub.addAction("think of a pond", a);

        a = new Action();
        a.gainItem("fishing rod");
        a.setCanRepeatAction(false);
        pond.addAction("grab fishing rod", a);

        a = new Action();
        a.setRange(4);
        a.setMessage("You got a halibut!");
        a.gainItem("halibut");
        a.setIndex(1);
        a.setMessage("You got a bluegill!");
        a.gainItem("bluegill");
        a.setIndex(2);
        a.setMessage("You got a boot!");
        a.gainItem("boot");
        a.setIndex(3);
        a.setMessage("You got a salmon!");
        a.gainItem("salmon");
        a.requireItem("fishing rod");
        pond.addAction("fish", a);

        a = new Action();
        a.setMessage("The cat acts as though you should follow it.");
        a.requireItem("bluegill");
        a.setCanReuseItem(false);
        a.setGrantedTask("feed_cat");
        pond.addAction("feed cat", a);

        a = new Action();
        a.setMessage("You follow the cat and it leads you back to the hub");
        a.setRequiredTask("feed_cat");
        a.setNextRoom(hub);
        pond.addAction("follow cat", a);

        return new Player(hub);
    }
}
