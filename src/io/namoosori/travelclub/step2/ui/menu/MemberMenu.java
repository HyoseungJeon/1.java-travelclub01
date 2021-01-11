package io.namoosori.travelclub.step2.ui.menu;

import io.namoosori.travelclub.step2.ui.console.MemberConsole;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

import java.util.Scanner;

public class MemberMenu {
    private MemberConsole memberConsole;

    private Scanner scanner;
    private Narrator narrator;

    public MemberMenu(){

        this.memberConsole = new MemberConsole();
        this.scanner = new Scanner(System.in);
        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    public void show(){
        int inputNumber = 0;

        while(true){
            displayMenu();
            inputNumber = selectMenu();

            switch (inputNumber){

                case 1:
                    memberConsole.findclub();
                    break;
                case 2:
                    memberConsole.add();
                    break;
                case 3:
                    memberConsole.find();
                    break;
                case 4:
                    memberConsole.modify();
                    break;
                case 5:
                    memberConsole.remove();
                    break;
                case 0:
                    return;
                default:{
                    narrator.sayln("Choose again!");
                }
            }
        }
    }

    private int selectMenu() {
        System.out.print("Select: ");
        int menuNumber = scanner.nextInt();

        if(menuNumber >= 0 &&  menuNumber <= 5){
            scanner.nextLine();
            return menuNumber;
        } else{
            narrator.sayln("It's a invalid number --> " + menuNumber);
            return -1;
        }
    }

    private void displayMenu() {
        narrator.sayln("");
        narrator.sayln("..............................");
        narrator.sayln(" Members menu");
        narrator.sayln("..............................");
        narrator.sayln(" 1. Find a club");
        narrator.sayln(" 2. Add member");
        narrator.sayln(" 3. Find a member");
        narrator.sayln(" 4. Modify a member");
        narrator.sayln(" 5. Remove a member");
        narrator.sayln("..............................");
        narrator.sayln(" 0. Previous");
        narrator.sayln("..............................");
    }


}
