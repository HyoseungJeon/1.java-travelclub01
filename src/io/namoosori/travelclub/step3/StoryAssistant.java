package io.namoosori.travelclub.step3;

import io.namoosori.travelclub.step3.ui.menu.ClubMenu;

public class StoryAssistant {

    public  static void main(String[] args){
        StoryAssistant storyAssistant = new StoryAssistant();
        storyAssistant.start();
    }

    public void start() {
        ClubMenu clubMenu = new ClubMenu();
        clubMenu.show();
    }
}
