package io.namoosori.travelclub.step2;

import io.namoosori.travelclub.step2.ui.menu.ClubMenu;

public class StoryAssistant {
    public static void main(String[] args){
        StoryAssistant storyAssistant = new StoryAssistant();
        storyAssistant.start();
    }

    private void start() {
        ClubMenu clubMenu = new ClubMenu();
        clubMenu.show();
    }
}
