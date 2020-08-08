package design06;

public class RemoteControlTest {

    public static void main(String[] args){
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        SimpleRemoteControl remote = new SimpleRemoteControl();
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}
