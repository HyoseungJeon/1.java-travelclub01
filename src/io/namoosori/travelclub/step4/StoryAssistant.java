package io.namoosori.travelclub.step4;

import io.namoosori.travelclub.step4.ui.menu.ClubMenu;

public class StoryAssistant {
    private void startStort(){
        ClubMenu clubMenu = new ClubMenu();
        clubMenu.show();
    }

    public static void main(String[] args){
        StoryAssistant assistant = new StoryAssistant();
        assistant.startStort();
    }
}
