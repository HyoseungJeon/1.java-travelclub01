package io.namoosori.travelclub.step2.ui.console;

import io.namoosori.travelclub.util.util.ConsoleUtil;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

public class ClubConsole {
    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public ClubConsole(){
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public void register(){
        consoleUtil.getValuOf("\n You've select the club register menu [Enter to go back].");
    }
    public void find(){
        consoleUtil.getValuOf("\n You've select the club find menu [Enter to go back].");
    }
    public void modify(){
        consoleUtil.getValuOf("\n You've select the club modify menu [Enter to go back].");
    }
    public void remove(){
        consoleUtil.getValuOf("\n You've select the club remove menu [Enter to go back].");
    }
}
