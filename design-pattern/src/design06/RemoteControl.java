package design06;

public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl(){
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
    }

    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
    }

    public String toString(){
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n ------Remote Control -----\n");
        for(int i = 0; i < onCommands.length;i++){
            stringBuff.append("[slot " + i +"]" + onCommands[i].getClass().getName() + "  " +  onCommands[i].getClass().getName() + "\n");
        }
        return stringBuff.toString();
    }

}