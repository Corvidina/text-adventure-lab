public class Action
{
   private String[] message;
   private int[] healthChange;
   private Room[] nextRoom;
   private String[] gainedItem;
   private String requiredItem;
   private boolean isRepeatable;
   private boolean reuseItem;
   private int index;
   private int range;
   private String grantedTask;
   private String requiredTask;
   private boolean unlessTask;
   private int[] numGainedItem;
   private long millis;
   private String preMessage;
   private int numRequiredItem;
   private String[] actionsPerformed;
   
   public Action()
   {
      range = 1;
      message = new String[1];
      nextRoom = new Room[1];
      gainedItem = new String[1];
      healthChange = new int[1];
      numGainedItem = new int[1];
      numRequiredItem = 1;
      isRepeatable = true;
      reuseItem = true;
      unlessTask = false;
   }

   public int getIndex(){
      return index;
   }

   public void setActionsPerformed(String[] arr){
      actionsPerformed = arr;
   }

   public String[] getActionsPerformed(){
      return actionsPerformed;
   }

   public void setNumRequiredItem(int num){
      numRequiredItem = num;
   }

   public int getNumRequiredItem(){
      return numRequiredItem;
   }

   public void setPreMessage(String str){preMessage=str;}

   public String getPreMessage(){
      return preMessage;
   }

   public void setMillis(long num){
      millis = num;
   }

   public long getMillis(){
      return millis;
   }

   public void setNumGainedItem(int num){
      numGainedItem[index] = num-1;
   }

   public int getNumGainedItem(){
      return numGainedItem[index]+1;
   }

   public void setUnlessTask(boolean b){
      unlessTask = b;
   }

   public boolean getUnlessTask(){
      return unlessTask;
   }

   public void setRequiredTask(String str){
      requiredTask = str;
   }

   public String getRequiredTasks(){
      return requiredTask;
   }

   public void setGrantedTask(String task){
      this.grantedTask = task;
   }

   public String getGrantedTask(){
      return grantedTask;
   }

   public void setRange(int range){
      this.range = range;

      String[] arr = new String[range];
      System.arraycopy(message, 0, arr, 0, message.length);
      message = arr;
      arr = new String[range];
      System.arraycopy(gainedItem, 0, arr, 0, gainedItem.length);
      gainedItem = arr;
      Room[] rooms = new Room[range];
      System.arraycopy(nextRoom, 0, rooms, 0, nextRoom.length);
      nextRoom = rooms;
      int[] ints = new int[range];
      System.arraycopy(healthChange, 0, ints, 0, healthChange.length);
      healthChange = ints;
      ints = new int[range];
      System.arraycopy(numGainedItem, 0, ints, 0, numGainedItem.length);
      numGainedItem = ints;
   }

   public int getRange(){
      return range;
   }

   public void setIndex(int index){
      this.index = index;
   }

   public void randomizeIndex(){
      index = (int)(Math.random()*range);
   }

   public boolean canReuseItem(){
      return reuseItem;
   }

   public void setCanReuseItem(boolean b) {
      reuseItem = b;
   }

   public boolean canRepeatAction(){
      return isRepeatable;
   }

   public void setCanRepeatAction(boolean b){
      isRepeatable = b;
   }

   public String getMessage()
   {
      return message[index];
   }
   
   public void setMessage(String message)
   {
      this.message[index] = message;
   }
   
   public int getHealthChange()
   {
      return healthChange[index];
   }
   
   public void setHealthChange(int healthChange)
   {
      this.healthChange[index] = healthChange;
   }
   
   public Room getNextRoom()
   {
      return nextRoom[index];
   }

   public void setNextRoom(Room nextRoom)
   {
      this.nextRoom[index] = nextRoom;
   }

   public String getGainedItem()
   {
      return gainedItem[index];
   }

   public void gainItem(String gainedItem)
   {
      this.gainedItem[index] = gainedItem;
   }
      
   public String getRequiredItem()
   {
      return requiredItem;
   }
   
   public void requireItem(String requiredItem)
   {
      this.requiredItem = requiredItem;
   }
}