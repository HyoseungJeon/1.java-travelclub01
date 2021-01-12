package io.namoosori.travelclub.step2.ui.console;

import io.namoosori.travelclub.util.util.ConsoleUtil;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

public class MemberConsole {

    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public MemberConsole(){
        this.narrator = new Narrator(this, TalkingAt.Left);
        consoleUtil = new ConsoleUtil(narrator);
    }
    public void findclub(){
        consoleUtil.getValueOf("\n You've select the club find menu [Enter to go back].");
    }
    public void add(){
        consoleUtil.getValueOf("\n You've select the club add menu [Enter to go back].");
    }
    public void find(){
        consoleUtil.getValueOf("\n You've select the club find menu [Enter to go back].");
    }
    public void modify(){
        consoleUtil.getValueOf("\n You've select the club modify menu [Enter to go back].");
    }
    public void remove(){
        consoleUtil.getValueOf("\n You've select the club remove menu [Enter to go back].");
    }
}

