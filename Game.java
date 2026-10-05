import java.util.Scanner;

public class Game {
    public static Player gameA(){
        Room cell = new Room("a cell in the dungeon.");
        Room dungeon = new Room("the dungeon.");
        Room mainHall = new Room("the main hall of what appears to be a late twelfth century gothic castle.");
        Room frontGarden = new Room("the front garden of the gothic castle");
        Room forest = new Room("a forest, but you don't have a clue where in said forest");
        Room shrine = new Room("a shrine in the middle of the forest. There is a pool of holy water in the shrine.");
        Room lostInTheForest = new Room("the middle of no where and cannot find your way out. You're stuck.");
        Room halfWayRoom = new Room("a hallway. There's a disturbing presence emanating from the room in front of you.\nAre you sure you want to enter?");
        Room vampireRoom = new Room("the vampire's room");
        Room finish = new Room("the forest.\n You've escaped the premise!");
        Room halfWayTwo = new Room(" in the room from before.");


        Action a = new Action();
        a.gainItem("bread");
        a.setCanRepeatAction(false);
        cell.addAction("grab bread", a);

        a = new Action();
        a.gainItem("cheese");
        a.setCanRepeatAction(false);
        cell.addAction("grab cheese",a);

        a = new Action();
        a.setRange(2);
        a.setCanReuseItem(false);
        a.requireItem("cheese");
        a.setMessage("You feed the rat and it brings you the key that was hanging in the dungeon");
        a.gainItem("cell key");
        a.setIndex(1);
        a.setMessage("You feed the rat, and it scurries off into the darkness.");
        cell.addAction("feed rat", a);

        a = new Action();
        a.setMessage("You use the key to open your cell and enter the dungeon.");
        a.requireItem("cell key");
        a.setNextRoom(dungeon);
        cell.addAction("exit cell", a);

        a = new Action();
        a.setNextRoom(cell);
        a.setMessage("You head back into the cell.");
        dungeon.addAction("return to cell",a);

        a = new Action();
        a.gainItem("axe");
        a.setCanRepeatAction(false);
        dungeon.addAction("grab axe", a);

        a = new Action();
        a.setNextRoom(mainHall);
        a.setMessage("You ascend the stairs into a brightly lit hall that appears to be prepared for a feast.\n There are a great number of glasses filled with a thick red liquid.");
        dungeon.addAction("ascend stairs", a);

        a = new Action();
        a.gainItem("bread");
        a.setMessage("You took some bread from off of the massive dining table.");
        mainHall.addAction("take food from the table", a);

        a = new Action();
        a.setHealthChange(-60);
        a.setGrantedTask("drankBlood");
        a.setMessage("You drink the thick red liquid and don't feel too good.");
        mainHall.addAction("drink the red liquid", a);

        a = new Action();
        a.setCanRepeatAction(false);
        a.setRequiredTask("drankBlood");
        a.gainItem("empty glass");
        mainHall.addAction("grab empty glass",a);

        a = new Action();
        a.setMessage("You exit the main hall to find that the premises you're in are fenced in with a locked gate");
        a.setNextRoom(frontGarden);
        mainHall.addAction("go out the front door",a);

        a = new Action();
        a.setMessage("You go back into the main hall");
        a.setNextRoom(mainHall);
        frontGarden.addAction("go back inside",a);

        a = new Action();
        a.setMessage("You walk into the woods right of the castle");
        a.setNextRoom(forest);
        frontGarden.addAction("walk into the forest to the right", a);

        a = new Action();
        a.setMessage("You go down the stairs to the main hall");
        a.setNextRoom(mainHall);
        halfWayTwo.addAction("go downstairs",a);

        a = new Action();
        a.setRange(4);
        //index 0
        a.setMessage("You wander around in the woods and trip on a rock.");
        a.setHealthChange(-30);
        a.setIndex(1);
        a.setMessage("You wander around in the woods and find a shrine.");
        a.setNextRoom(shrine);
        a.setIndex(2);
        a.setMessage("You wander around in the woods and get lost. Oh dear.");
        a.setNextRoom(lostInTheForest);
        a.setIndex(3);
        a.setMessage("You wander around in the woods and find your way back to the front garden.");
        a.setNextRoom(frontGarden);
        forest.addAction("wander around", a);

        a = new Action();
        a.requireItem("empty glass");
        a.setCanReuseItem(false);
        a.setMessage("You fill the glass with water from the shrine.");
        a.gainItem("holy water");
        shrine.addAction("fill glass", a);

        a = new Action();
        a.setNextRoom(forest);
        a.setMessage("You walk back into the forest.");
        shrine.addAction("return to the woods", a);

        a = new Action();
        a.setHealthChange(-100);
        a.setMessage("You accept your fate. You came unprepared");
        vampireRoom.addAction("accept fate", a);

        a = new Action();
        a.setNextRoom(forest);
        a.setMessage("admin");
        lostInTheForest.addAction("oh dear",a);

        a = new Action();
        a.setCanRepeatAction(false);
        a.setGrantedTask("brokenBarricade");
        a.setMessage("You break the barricade blocking the door in the back");
        mainHall.addAction("break barricade", a);

        a = new Action();
        a.setNextRoom(halfWayRoom);
        a.setMessage("You ascend the stairs behind the door until you reach another door.");
        a.setRequiredTask("brokenBarricade");
        mainHall.addAction("go upstairs",a);

        a = new Action();
        a.gainItem("stake");
        a.setMessage("You cut down the tree and use the limbs to make a stake");
        a.requireItem("axe");
        a.setCanRepeatAction(false);
        frontGarden.addAction("cut down tree", a);

        a = new Action();
        a.setNextRoom(vampireRoom);
        a.setCanRepeatAction(false);
        a.setMessage("You enter the room. There's a vampire!");
        halfWayRoom.addAction("enter the room", a);

        a = new Action();
        a.setNextRoom(mainHall);
        a.setMessage("You head back down the stairs to the main hall");
        halfWayRoom.addAction("go back downstairs", a);

        a = new Action();
        a.requireItem("holy water");
        a.setGrantedTask("hasDoused");
        a.setMessage("You douse yourself in holy water to protect from the vampire.");
        a.setCanReuseItem(false);
        a.setCanRepeatAction(false);
        vampireRoom.addAction("douse yourself in water", a);

        a = new Action();
        a.setRange(40);
        a.setUnlessTask(true);
        a.setRequiredTask("hasDoused");
        a.setNextRoom(halfWayRoom);
        a.requireItem("stake");
        a.setMessage("You stab the vampire with the stake. It dies and you find a key!");
        a.gainItem("outside key");

        for(int i = 1; i < 40; i++){
            a.setIndex(i);
            a.setHealthChange(-20);
            a.setMessage("You miss and the vampire attacks you!");
        }
        vampireRoom.addAction("attack the vampire", a);

        a = new Action();
        a.setMessage("You descend the stairs back to the dungeon");
        a.setNextRoom(dungeon);
        mainHall.addAction("descend to the dungeon", a);

        a = new Action();
        a.setRange(8);
        a.requireItem("stake");
        a.setRequiredTask("hasDoused");
        a.setNextRoom(halfWayRoom);

        a.setMessage("You stab the vampire with the stake. It dies and you find a key!");
        a.gainItem("outside key");
        for(int i = 1; i < 8; i++){
            a.setIndex(i);
            a.setMessage("You miss the vampire, but the holy water protects you.");
        }
        vampireRoom.addAction("attack vampire", a);



        a = new Action();
        a.setMessage("You escape the premises!");
        a.requireItem("outside key");
        a.setHealthChange(-Integer.MAX_VALUE);
        a.setNextRoom(finish);
        frontGarden.addAction("escape the premises", a);

        allRoomsA(cell);
        allRoomsA(dungeon);
        allRoomsA(mainHall);
        allRoomsA(frontGarden);
        allRoomsA(shrine);
        allRoomsA(forest);
        allRoomsA(halfWayRoom);

        return new Player(cell);
    }

    private static void allRoomsA(Room room){
        Action a = new Action();
        a.requireItem("cheese");
        a.setCanReuseItem(false);
        a.setMessage("You eat the cheese");
        a.setHealthChange(15);
        room.addAction("eat cheese", a);

        a = new Action();
        a.requireItem("bread");
        a.setCanReuseItem(false);
        a.setMessage("You eat the bread");
        a.setHealthChange(25);
        room.addAction("eat bread", a);

        a = new Action();
        a.requireItem("holy water");
        a.setGrantedTask("hasDoused");
        a.setMessage("You douse yourself in holy water.");
        a.setCanReuseItem(false);
        a.setCanRepeatAction(false);
        room.addAction("douse yourself in water", a);
    }

    public static Player gameB(){
        Room ocean = new Room("the ocean. Best get fishing!");
        Room shop = new Room("the shop. Buy and Sell!\n" +
                "Useful info:\n" +
                "Basic rod=20$\n" +
                "Reinforced rod = 600$\n" +
                "Advanced rod = 6500$\n" +
                "Ultimate rod = 25000$");

        Action a = new Action();
        a.setNextRoom(shop);
        a.setMessage("You go head back to the shore shop");
        ocean.addAction("go to shop", a);

        a = new Action();
        a.setNextRoom(ocean);
        a.setMessage("You return to the ocean. Best get fishing!");
        shop.addAction("return to ocean", a);

        a = new Action();
        a.requireItem("basic fishing rod");
        a.setMillis((long)(30000*(Math.random()+0.65)*0.95));
        a.setPreMessage("You cast your line");
        a.setRange(20);
        a.gainItem("peeper");
        a.setMessage("You got a peeper!");
        a.setIndex(1);
        a.gainItem("bladderfish");
        a.setMessage("You got a bladderfish!");
        a.setIndex(2);
        a.gainItem("cuddlefish");
        a.setMessage("You got a cuddlefish!");
        a.setIndex(3);
        a.gainItem("eyeye");
        a.setMessage("You got an eyeye!");
        a.setIndex(4);
        a.gainItem("garryfish");
        a.setMessage("You got a garryfish!");
        a.setIndex(5);
        a.gainItem("holefish");
        a.setMessage("You got a holefish!");
        a.setIndex(6);
        a.setMessage("You got a hoopfish!");
        a.gainItem("hoopfish");
        a.setIndex(7);
        a.gainItem("hoverfish");
        a.setMessage("You got a hoverfish!");
        a.setIndex(8);
        a.gainItem("jellyray");
        a.setMessage("You got a jellyray!");
        a.setIndex(9);
        a.setMessage("You got an oculus!");
        a.gainItem("oculus");
        a.setIndex(10);
        a.gainItem("rabbit ray");
        a.setMessage("You got a rabbit ray!");
        a.setIndex(11);
        a.gainItem("spadefish");
        a.setMessage("You got a spadefish!");
        a.setIndex(12);
        a.gainItem("spinefish");
        a.setMessage("You got a spinefish!");
        a.setIndex(13);
        a.gainItem("crashfish");
        a.setMessage("You got a crashfish!");
        a.setIndex(14);
        a.gainItem("sea emperor leviathan baby");
        a.setMessage("You got a sea emperor leviathan baby");
        for(int i = 15; i < 20; i++){
            a.setIndex(i);
            a.setMessage("You got nothin");
        }
        ocean.addAction("use basic rod", a);

        a = new Action();
        a.requireItem("reinforced fishing rod");
        a.setMillis((long)(30000*(Math.random()+0.65)*0.90));a.setPreMessage("You cast your line");
        a.setRange(30);
        a.setIndex(0);
        a.setMessage("You got a peeper!");
        a.gainItem("peeper");
        a.setIndex(1);
        a.setMessage("You got a bladderfish!");
        a.gainItem("bladderfish");
        a.setIndex(2);
        a.setMessage("You got a boomerang!");
        a.gainItem("boomerang");
        a.setIndex(3);
        a.setMessage("You got a crimson ray!");
        a.gainItem("crimson ray");
        a.setIndex(4);
        a.setMessage("You got a cuddlefish!");
        a.gainItem("cuddlefish");
        a.setIndex(5);
        a.setMessage("You got a eyeye!");
        a.gainItem("eyeye");
        a.setIndex(6);
        a.setMessage("You got a garryfish!");
        a.gainItem("garryfish");
        a.setIndex(7);
        a.setMessage("You got a gasopod!");
        a.gainItem("gasopod");
        a.setIndex(8);
        a.setMessage("You got a ghostray!");
        a.gainItem("ghostray");
        a.setIndex(9);
        a.setMessage("You got a holefish!");
        a.gainItem("holefish");
        a.setIndex(10);
        a.setMessage("You got a hoopfish!");
        a.gainItem("hoopfish");
        a.setIndex(11);
        a.setMessage("You got a hoverfish!");
        a.gainItem("hoverfish");
        a.setIndex(12);
        a.setMessage("You got a jellyray!");
        a.gainItem("jellyray");
        a.setIndex(13);
        a.setMessage("You got a magmarang!");
        a.gainItem("magmarang");
        a.setIndex(14);
        a.setMessage("You got a oculus!");
        a.gainItem("oculus");
        a.setIndex(15);
        a.setMessage("You got a rabbit ray!");
        a.gainItem("rabbit ray");
        a.setIndex(16);
        a.setMessage("You got a red eyeye!");
        a.gainItem("red eyeye");
        a.setIndex(17);
        a.setMessage("You got a skyray!");
        a.gainItem("skyray");
        a.setIndex(18);
        a.setMessage("You got a spadefish!");
        a.gainItem("spadefish");
        a.setIndex(19);
        a.setMessage("You got a spinefish!");
        a.gainItem("spinefish");
        a.setIndex(20);
        a.setMessage("You got a biter!");
        a.gainItem("biter");
        a.setIndex(21);
        a.setMessage("You got a blighter!");
        a.gainItem("blighter");
        a.setIndex(22);
        a.setMessage("You got a crashfish!");
        a.gainItem("crashfish");
        a.setIndex(23);
        a.setMessage("You got a mesmer!");
        a.gainItem("mesmer");
        a.setIndex(24);
        a.setMessage("You got a river prowler!");
        a.gainItem("river prowler");
        a.setIndex(25);
        a.setMessage("You got a sea emperor leviathan baby!");
        a.gainItem("sea emperor leviathan baby");
        for(int i=26;i<30;i++){
            a.setIndex(i);
            a.setMessage("You got nothin");
        }
        ocean.addAction("use reinforced rod", a);

        a = new Action();
        a.requireItem("advanced fishing rod");
        a.setRange(42);
        a.setMillis((long)(30000*(Math.random()+0.65)*0.85));a.setPreMessage("You cast your line");
        a.setIndex(0);
        a.setMessage("You got a peeper!");
        a.gainItem("peeper");
        a.setIndex(1);
        a.setMessage("You got a bladderfish!");
        a.gainItem("bladderfish");
        a.setIndex(2);
        a.setMessage("You got a boomerang!");
        a.gainItem("boomerang");
        a.setIndex(3);
        a.setMessage("You got a crimson ray!");
        a.gainItem("crimson ray");
        a.setIndex(4);
        a.setMessage("You got a cuddlefish!");
        a.gainItem("cuddlefish");
        a.setIndex(5);
        a.setMessage("You got a eyeye!");
        a.gainItem("eyeye");
        a.setIndex(6);
        a.setMessage("You got a garryfish!");
        a.gainItem("garryfish");
        a.setIndex(7);
        a.setMessage("You got a gasopod!");
        a.gainItem("gasopod");
        a.setIndex(8);
        a.setMessage("You got a ghostray!");
        a.gainItem("ghostray");
        a.setIndex(9);
        a.setMessage("You got a holefish!");
        a.gainItem("holefish");
        a.setIndex(10);
        a.setMessage("You got a hoopfish!");
        a.gainItem("hoopfish");
        a.setIndex(11);
        a.setMessage("You got a hoverfish!");
        a.gainItem("hoverfish");
        a.setIndex(12);
        a.setMessage("You got a jellyray!");
        a.gainItem("jellyray");
        a.setIndex(13);
        a.setMessage("You got a magmarang!");
        a.gainItem("magmarang");
        a.setIndex(14);
        a.setMessage("You got a oculus!");
        a.gainItem("oculus");
        a.setIndex(15);
        a.setMessage("You got a rabbit ray!");
        a.gainItem("rabbit ray");
        a.setIndex(16);
        a.setMessage("You got a red eyeye!");
        a.gainItem("red eyeye");
        a.setIndex(17);
        a.setMessage("You got a reginald!");
        a.gainItem("reginald");
        a.setIndex(18);
        a.setMessage("You got a skyray!");
        a.gainItem("skyray");
        a.setIndex(19);
        a.setMessage("You got a spadefish!");
        a.gainItem("spadefish");
        a.setIndex(20);
        a.setMessage("You got a spinefish!");
        a.gainItem("spinefish");
        a.setIndex(21);
        a.setMessage("You got a ampeel!");
        a.gainItem("ampeel");
        a.setIndex(22);
        a.setMessage("You got a biter!");
        a.gainItem("biter");
        a.setIndex(23);
        a.setMessage("You got a blighter!");
        a.gainItem("blighter");
        a.setIndex(24);
        a.setMessage("You got a boneshark!");
        a.gainItem("boneshark");
        a.setIndex(25);
        a.setMessage("You got a crabsnake!");
        a.gainItem("crabsnake");
        a.setIndex(26);
        a.setMessage("You got a crabsquid!");
        a.gainItem("crabsquid");
        a.setIndex(27);
        a.setMessage("You got a crashfish!");
        a.gainItem("crashfish");
        a.setIndex(28);
        a.setMessage("You got a lava lizard!");
        a.gainItem("lava lizard");
        a.setIndex(29);
        a.setMessage("You got a mesmer!");
        a.gainItem("mesmer");
        a.setIndex(30);
        a.setMessage("You got a river prowler!");
        a.gainItem("river prowler");
        a.setIndex(31);
        a.setMessage("You got a sand shark!");
        a.gainItem("sand shark");
        a.setIndex(32);
        a.setMessage("You got a stalker!");
        a.gainItem("stalker");
        a.setIndex(33);
        a.setMessage("You got a warper!");
        a.gainItem("warper");
        a.setIndex(34);
        a.setMessage("You got a ghost leviathan juvenile!");
        a.gainItem("ghost leviathan juvenile");
        a.setIndex(35);
        a.setMessage("You got a reefback leviathan juvenile!");
        a.gainItem("reefback leviathan juvenile");
        a.setIndex(36);
        a.setMessage("You got a sea emperor leviathan baby!");
        a.gainItem("sea emperor leviathan baby");
        a.setIndex(37);
        a.setMessage("You got a sea emperor leviathan juvenile!");
        a.gainItem("sea emperor leviathan juvenile");
        for(int i=38;i<42;i++){
            a.setIndex(i);
            a.setMessage("You got nothin");
        }
        ocean.addAction("use advanced rod",a);

        a = new Action();
        a.setRange(47);
        a.requireItem("ultimate fishing rod");
        a.setMillis((long)(30000*(Math.random()+0.65)*0.85));a.setPreMessage("You cast your line");
        a.setIndex(0);
        a.setMessage("You got a peeper!");
        a.gainItem("peeper");
        a.setIndex(1);
        a.setMessage("You got a bladderfish!");
        a.gainItem("bladderfish");
        a.setIndex(2);
        a.setMessage("You got a boomerang!");
        a.gainItem("boomerang");
        a.setIndex(3);
        a.setMessage("You got a crimson ray!");
        a.gainItem("crimson ray");
        a.setIndex(4);
        a.setMessage("You got a cuddlefish!");
        a.gainItem("cuddlefish");
        a.setIndex(5);
        a.setMessage("You got a eyeye!");
        a.gainItem("eyeye");
        a.setIndex(6);
        a.setMessage("You got a garryfish!");
        a.gainItem("garryfish");
        a.setIndex(7);
        a.setMessage("You got a gasopod!");
        a.gainItem("gasopod");
        a.setIndex(8);
        a.setMessage("You got a ghostray!");
        a.gainItem("ghostray");
        a.setIndex(9);
        a.setMessage("You got a holefish!");
        a.gainItem("holefish");
        a.setIndex(10);
        a.setMessage("You got a hoopfish!");
        a.gainItem("hoopfish");
        a.setIndex(11);
        a.setMessage("You got a hoverfish!");
        a.gainItem("hoverfish");
        a.setIndex(12);
        a.setMessage("You got a jellyray!");
        a.gainItem("jellyray");
        a.setIndex(13);
        a.setMessage("You got a magmarang!");
        a.gainItem("magmarang");
        a.setIndex(14);
        a.setMessage("You got a oculus!");
        a.gainItem("oculus");
        a.setIndex(15);
        a.setMessage("You got a rabbit ray!");
        a.gainItem("rabbit ray");
        a.setIndex(16);
        a.setMessage("You got a red eyeye!");
        a.gainItem("red eyeye");
        a.setIndex(17);
        a.setMessage("You got a reginald!");
        a.gainItem("reginald");
        a.setIndex(18);
        a.setMessage("You got a skyray!");
        a.gainItem("skyray");
        a.setIndex(19);
        a.setMessage("You got a spadefish!");
        a.gainItem("spadefish");
        a.setIndex(20);
        a.setMessage("You got a spinefish!");
        a.gainItem("spinefish");
        a.setIndex(21);
        a.setMessage("You got a ampeel!");
        a.gainItem("ampeel");
        a.setIndex(22);
        a.setMessage("You got a biter!");
        a.gainItem("biter");
        a.setIndex(23);
        a.setMessage("You got a blighter!");
        a.gainItem("blighter");
        a.setIndex(24);
        a.setMessage("You got a boneshark!");
        a.gainItem("boneshark");
        a.setIndex(25);
        a.setMessage("You got a crabsnake!");
        a.gainItem("crabsnake");
        a.setIndex(26);
        a.setMessage("You got a crabsquid!");
        a.gainItem("crabsquid");
        a.setIndex(27);
        a.setMessage("You got a crashfish!");
        a.gainItem("crashfish");
        a.setIndex(28);
        a.setMessage("You got a lava lizard!");
        a.gainItem("lava lizard");
        a.setIndex(29);
        a.setMessage("You got a mesmer!");
        a.gainItem("mesmer");
        a.setIndex(30);
        a.setMessage("You got a river prowler!");
        a.gainItem("river prowler");
        a.setIndex(31);
        a.setMessage("You got a sand shark!");
        a.gainItem("sand shark");
        a.setIndex(32);
        a.setMessage("You got a stalker!");
        a.gainItem("stalker");
        a.setIndex(33);
        a.setMessage("You got a warper!");
        a.gainItem("warper");
        a.setIndex(34);
        a.setMessage("You got a ghost leviathan!");
        a.gainItem("ghost leviathan");
        a.setIndex(35);
        a.setMessage("You got a ghost leviathan juvenile!");
        a.gainItem("ghost leviathan juvenile");
        a.setIndex(36);
        a.setMessage("You got a reaper leviathan!");
        a.gainItem("reaper leviathan");
        a.setIndex(37);
        a.setMessage("You got a reefback leviathan!");
        a.gainItem("reefback leviathan");
        a.setIndex(38);
        a.setMessage("You got a reefback leviathan juvenile!");
        a.gainItem("reefback leviathan juvenile");
        a.setIndex(39);
        a.setMessage("You got a sea dragon leviathan!");
        a.gainItem("sea dragon leviathan");
        a.setIndex(40);
        a.setMessage("You got a sea emperor leviathan!");
        a.gainItem("sea emperor leviathan");
        a.setIndex(41);
        a.setMessage("You got a sea emperor leviathan baby!");
        a.gainItem("sea emperor leviathan baby");
        a.setIndex(42);
        a.setMessage("You got a sea emperor leviathan juvenile!");
        a.gainItem("sea emperor leviathan juvenile");
        for(int i=43;i<47;i++){
            a.setIndex(i);
            a.setMessage("You got nothin");
        }
        ocean.addAction("use ultimate rod",a);


        fillShop(shop);
        Player p = new Player(ocean);
        initiateB(p);
        return p;
    }

    private static void fillShop(Room shop){
        Action a;
        a = new Action();
        a.setCanReuseItem(false);
        a.requireItem("dollar");
        a.setNumRequiredItem(20);
        a.gainItem("basic fishing rod");
        a.setMessage("You bought a basic fishing rod for 20 dollars!");
        shop.addAction("buy basic fishing rod", a);

        a = new Action();
        a.setCanReuseItem(false);
        a.requireItem("dollar");
        a.setNumRequiredItem(600);
        a.gainItem("reinforced fishing rod");
        a.setMessage("You bought a reinforced fishing rod for 600 dollars!");
        shop.addAction("buy reinforced fishing rod", a);

        a = new Action();
        a.setCanReuseItem(false);
        a.setNumRequiredItem(6500);
        a.requireItem("dollar");
        a.gainItem("advanced fishing rod");
        a.setMessage("You bought an advanced fishing rod!");
        shop.addAction("buy advanced fishing rod", a);

        a = new Action();
        a.setCanReuseItem(false);
        a.setNumRequiredItem(25000);
        a.requireItem("dollar");
        a.gainItem("ultimate fishing rod");
        a.setMessage("You bought the ultimate fishing rod!");
        shop.addAction("buy ultimate fishing rod", a);

        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("peeper");
        a.setMessage("You sold a peeper for 5 dollars");
        a.setNumGainedItem(5);
        shop.addAction("sell peeper", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("bladderfish");
        a.setMessage("You sold a bladderfish for 5 dollars");
        a.setNumGainedItem(5);
        shop.addAction("sell bladderfish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("boomerang");
        a.setMessage("You sold a boomerang for 8 dollars");
        a.setNumGainedItem(8);
        shop.addAction("sell boomerang", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("crimson ray");
        a.setMessage("You sold a crimson ray for 100 dollars");
        a.setNumGainedItem(100);
        shop.addAction("sell crimson ray", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("cuddlefish");
        a.setMessage("You sold a cuddlefish for 1 dollars");
        a.setNumGainedItem(1);
        shop.addAction("sell cuddlefish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("eyeye");
        a.setMessage("You sold a eyeye for 20 dollars");
        a.setNumGainedItem(20);
        shop.addAction("sell eyeye", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("garryfish");
        a.setMessage("You sold a garryfish for 15 dollars");
        a.setNumGainedItem(15);
        shop.addAction("sell garryfish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("gasopod");
        a.setMessage("You sold a gasopod for 75 dollars");
        a.setNumGainedItem(75);
        shop.addAction("sell gasopod", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("ghostray");
        a.setMessage("You sold a ghostray for 65 dollars");
        a.setNumGainedItem(65);
        shop.addAction("sell ghostray", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("holefish");
        a.setMessage("You sold a holefish for 10 dollars");
        a.setNumGainedItem(10);
        shop.addAction("sell holefish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("hoopfish");
        a.setMessage("You sold a hoopfish for 10 dollars");
        a.setNumGainedItem(10);
        shop.addAction("sell hoopfish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("hoverfish");
        a.setMessage("You sold a hoverfish for 12 dollars");
        a.setNumGainedItem(12);
        shop.addAction("sell hoverfish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("jellyray");
        a.setMessage("You sold a jellyray for 30 dollars");
        a.setNumGainedItem(30);
        shop.addAction("sell jellyray", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("magmarang");
        a.setMessage("You sold a magmarang for 40 dollars");
        a.setNumGainedItem(40);
        shop.addAction("sell magmarang", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("oculus");
        a.setMessage("You sold a oculus for 50 dollars");
        a.setNumGainedItem(50);
        shop.addAction("sell oculus", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("rabbit ray");
        a.setMessage("You sold a rabbit ray for 35 dollars");
        a.setNumGainedItem(35);
        shop.addAction("sell rabbit ray", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("red eyeye");
        a.setMessage("You sold a red eyeye for 50 dollars");
        a.setNumGainedItem(50);
        shop.addAction("sell red eyeye", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("reginald");
        a.setMessage("You sold a reginald for 75 dollars");
        a.setNumGainedItem(75);
        shop.addAction("sell reginald", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("skyray");
        a.setMessage("You sold a skyray for 55 dollars");
        a.setNumGainedItem(55);
        shop.addAction("sell skyray", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("spadefish");
        a.setMessage("You sold a spadefish for 35 dollars");
        a.setNumGainedItem(35);
        shop.addAction("sell spadefish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("spinefish");
        a.setMessage("You sold a spinefish for 40 dollars");
        a.setNumGainedItem(40);
        shop.addAction("sell spinefish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("ampeel");
        a.setMessage("You sold a ampeel for 240 dollars");
        a.setNumGainedItem(240);
        shop.addAction("sell ampeel", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("biter");
        a.setMessage("You sold a biter for 75 dollars");
        a.setNumGainedItem(75);
        shop.addAction("sell biter", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("blighter");
        a.setMessage("You sold a blighter for 90 dollars");
        a.setNumGainedItem(90);
        shop.addAction("sell blighter", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("boneshark");
        a.setMessage("You sold a boneshark for 400 dollars");
        a.setNumGainedItem(400);
        shop.addAction("sell boneshark", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("crabsnake");
        a.setMessage("You sold a crabsnake for 450 dollars");
        a.setNumGainedItem(450);
        shop.addAction("sell crabsnake", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("crabsquid");
        a.setMessage("You sold a crabsquid for 650 dollars");
        a.setNumGainedItem(650);
        shop.addAction("sell crabsquid", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("crashfish");
        a.setMessage("You sold a crashfish for 55 dollars");
        a.setNumGainedItem(55);
        shop.addAction("sell crashfish", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("lava lizard");
        a.setMessage("You sold a lava lizard for 320 dollars");
        a.setNumGainedItem(320);
        shop.addAction("sell lava lizard", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("mesmer");
        a.setMessage("You sold a mesmer for 160 dollars");
        a.setNumGainedItem(160);
        shop.addAction("sell mesmer", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("river prowler");
        a.setMessage("You sold a river prowler for 190 dollars");
        a.setNumGainedItem(190);
        shop.addAction("sell river prowler", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("sand shark");
        a.setMessage("You sold a sand shark for 340 dollars");
        a.setNumGainedItem(340);
        shop.addAction("sell sand shark", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("stalker");
        a.setMessage("You sold a stalker for 280 dollars");
        a.setNumGainedItem(280);
        shop.addAction("sell stalker", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("warper");
        a.setMessage("You sold a warper for 1200 dollars");
        a.setNumGainedItem(1200);
        shop.addAction("sell warper", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("ghost leviathan");
        a.setMessage("You sold a ghost leviathan for 2500 dollars");
        a.setNumGainedItem(2500);
        shop.addAction("sell ghost leviathan", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("ghost leviathan juvenile");
        a.setMessage("You sold a ghost leviathan juvenile for 1750 dollars");
        a.setNumGainedItem(1750);
        shop.addAction("sell ghost leviathan juvenile", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("reaper leviathan");
        a.setMessage("You sold a reaper leviathan for 2750 dollars");
        a.setNumGainedItem(2750);
        shop.addAction("sell reaper leviathan", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("reefback leviathan");
        a.setMessage("You sold a reefback leviathan for 2000 dollars");
        a.setNumGainedItem(2000);
        shop.addAction("sell reefback leviathan", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("reefback leviathan juvenile");
        a.setMessage("You sold a reefback leviathan juvenile for 1500 dollars");
        a.setNumGainedItem(1500);
        shop.addAction("sell reefback leviathan juvenile", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("sea dragon leviathan");
        a.setMessage("You sold a sea dragon leviathan for 4000 dollars");
        a.setNumGainedItem(4000);
        shop.addAction("sell sea dragon leviathan", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("sea emperor leviathan");
        a.setMessage("You sold a sea emperor leviathan for 25000 dollars");
        a.setNumGainedItem(25000);
        shop.addAction("sell sea emperor leviathan", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("sea emperor leviathan baby");
        a.setMessage("You sold a sea emperor leviathan baby for 2000 dollars");
        a.setNumGainedItem(2000);
        shop.addAction("sell sea emperor leviathan baby", a);
        a = new Action();
        a.setCanReuseItem(false);
        a.gainItem("dollar");
        a.requireItem("sea emperor leviathan juvenile");
        a.setMessage("You sold a sea emperor leviathan juvenile for 4000 dollars");
        a.setNumGainedItem(4000);
        shop.addAction("sell sea emperor leviathan juvenile", a);
        a = new Action();
        a.setActionsPerformed(new String[]{
                "sell peeper",
                "sell bladderfish",
                "sell boomerang",
                "sell crimson ray",
                "sell cuddlefish",
                "sell eyeye",
                "sell garryfish",
                "sell gasopod",
                "sell ghostray",
                "sell holefish",
                "sell hoopfish",
                "sell hoverfish",
                "sell jellyray",
                "sell magmarang",
                "sell oculus",
                "sell rabbit ray",
                "sell red eyeye",
                "sell reginald",
                "sell skyray",
                "sell spadefish",
                "sell spinefish",
                "sell ampeel",
                "sell biter",
                "sell blighter",
                "sell boneshark",
                "sell crabsnake",
                "sell crabsquid",
                "sell crashfish",
                "sell lava lizard",
                "sell mesmer",
                "sell river prowler",
                "sell sand shark",
                "sell stalker",
                "sell warper",
                "sell ghost leviathan",
                "sell ghost leviathan juvenile",
                "sell sea emperor leviathan",
                "sell sea emperor leviathan baby",
                "sell sea emperor leviathan juvenile",
                "sell sea dragon leviathan",
                "sell reefback leviathan",
                "sell reefback leviathan juvenile",
                "sell reaper leviathan"
        });
        shop.addAction("sell all", a);

        a = new Action();
        a.setMessage("admin");
        a.gainItem("dollar");
        a.setNumGainedItem(100000);
        shop.addAction("admin money", a);

    }


    private static void initiateB(Player player){

        Action a = new Action();
        a.setCanRepeatAction(false);
        a.setNumGainedItem(500);
        a.gainItem("dollar");
        player.getCurrentRoom().addAction("initiate", a);
        player.performAction("initiate");

        a = new Action();
        a.setCanRepeatAction(false);
        a.gainItem("basic fishing rod");
        player.getCurrentRoom().addAction("initiate", a);
        player.performAction("initiate");

        System.out.println("Would you like to play the realistic way or not (ie including wait times). Answer use yes or no");
        optionsB(player);

    }

    private static void optionsB(Player player){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        if(str.equals("yes")){
            player.setIncludeSleeps(true);
            System.out.println("You have opted to play the realistic way.");
        } else if (str.equals("no")){
            player.setIncludeSleeps(false);
            System.out.println("You have opted not to play the realistic way.");
        } else {
            System.out.println("Please format your input correctly");
            optionsB(player);
        }
    }
}
