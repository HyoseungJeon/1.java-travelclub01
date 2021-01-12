package io.namoosori.travelclub.step4.ui.console;

import io.namoosori.travelclub.step1.entity.ClubMember;
import io.namoosori.travelclub.step1.entity.RolelnClub;
import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.step4.logic.ClubCoordinator;
import io.namoosori.travelclub.step4.logic.MemberHelper;
import io.namoosori.travelclub.util.exception.InvalidEmailException;
import io.namoosori.travelclub.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.util.util.ConsoleUtil;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

public class MemberConsole {
    private TravelClub currentClub;
    private ClubCoordinator clubCoordinator;
    private MemberHelper memberHelper;

    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public MemberConsole(ClubCoordinator clubCoordinator){

        this.clubCoordinator = clubCoordinator;
        this.memberHelper = clubCoordinator.getMemberHelper();
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public TravelClub getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(TravelClub currentClub) {
        this.currentClub = currentClub;
    }

    public boolean hasCurrentClub(){
        return currentClub != null;
    }

    public String requestCurrentClubName(){
        String clubName = null;

        if(hasCurrentClub()){
            clubName = currentClub.getName();
        }

        return clubName;
    }

    public  TravelClub findAnotherClub(){

        TravelClub clubFound = null;
        if (!clubCoordinator.hasClubs()) {
            narrator.sayln("<?> No clubs in the storage.");
            currentClub = null;
            return null;
        }

        while(true){
            String clubName = consoleUtil.getValueOf("\n name to find(0.Member menu) ");
            if(clubName.equals("0")){
                break;
            }

            if(clubCoordinator.existByname(clubName)){
                clubFound = clubCoordinator.find(clubName);
                narrator.sayln("\t > Found club: " + clubName);
                break;
            }else{
                narrator.sayln("\t > No such club in the storage --> " + clubName);

            }
        }
        currentClub = clubFound;
        return  clubFound;
    }

    public void add(){

        if(currentClub == null){
            narrator.sayln("No target club yet. Find target club first.");
            return;
        }

        while (true){
            String email = consoleUtil.getValueOf("\n new member's email(0.Member menu)");
            if(email.equals("0"))
                break;

            if(memberHelper.existByEmail(currentClub, email)){
                narrator.sayln("Member with this email already exists. --> " + email);
                continue;
            }

            String name = consoleUtil.getValueOf("name");
            String phoneNumber = consoleUtil.getValueOf("phone number");
            String nickname = consoleUtil.getValueOf("nickname");
            String bitrhDay = consoleUtil.getValueOf("birthday(yyyy.mm.dd)");
            String memberRole = consoleUtil.getValueOf("President|Member");

            try {
                ClubMember newMember = new ClubMember(email, name, phoneNumber);
                newMember.setNickname(nickname);
                newMember.setBirthDay(bitrhDay);
                newMember.setRole(RolelnClub.valueOf(memberRole));

                if(!memberHelper.hasMembers(currentClub)){
                    newMember.setRole(RolelnClub.President);
                }

                memberHelper.register(currentClub,newMember);
                narrator.sayln("Registered member :" + newMember.toString());

            }catch (InvalidEmailException | MemberDuplicationException e){
                narrator.sayln(e.getMessage());
            }
        }
    }
    public ClubMember find(){
        if(currentClub == null){
            narrator.sayln("No target club yet. Find target club first.");
            return  null;
        }

        if(!memberHelper.hasMembers(currentClub)){
            narrator.sayln("No members in the target club --> " + currentClub.getName());
            return null;
        }

        ClubMember targetMember = null;

        while(true){
            String email = consoleUtil.getValueOf("\t member email to find(0.Member menu)");
            if(email.equals("0"))
                break;

            if(memberHelper.existByEmail(currentClub, email)){
                targetMember = memberHelper.find(currentClub, email);
                narrator.sayln("\t > Found memeber: " + targetMember.toString());
            }else {
                System.out.print("\t > No such member in the club storage");
                            }
        }
        return targetMember;
    }

    public ClubMember findOne(){
        if(currentClub == null){
            narrator.sayln("No target club yet. Find target club first.");
            return null;
        }

        if(!memberHelper.hasMembers(currentClub)){
            narrator.sayln("No members in the target club --> " + currentClub.getName());
            return null;
        }

        ClubMember targetMember = null;

        while (true){
            String email = consoleUtil.getValueOf("\n member email to find(0.Member menu)");
            if(email.equals("0"))
                break;

            if(memberHelper.existByEmail(currentClub, email)){
                targetMember = memberHelper.find(currentClub, email);
                narrator.sayln("\t > Found member: " + targetMember.toString());
                break;
            }else{
                System.out.print("\t > No such member in the club storage");
            }
        }
        return targetMember;
    }

    public void modify(){

        if(currentClub == null){
            narrator.sayln("No target club yet. Find target club first.");
            return;
        }

        ClubMember targetMember = findOne();
        if(targetMember == null)
            return;

        String newName = consoleUtil.getValueOf(" new name(Enter. no change)");
        if (newName.equals("")) {
            newName = targetMember.getName();
        }

        String newPhoneNumber = consoleUtil.getValueOf(" new phone number(Enter. no change)");
        if (newPhoneNumber.equals("")) {
            newPhoneNumber = targetMember.getPhoneNumber();
        }

        String newNickname = consoleUtil.getValueOf(" new nickname(Enter. no change)");
        if (newNickname.equals("")) {
            newNickname = targetMember.getNickname();
        }

        String newBirthDay = consoleUtil.getValueOf(" new birthday(yyyy.mm.dd)(Enter. no change)");
        if (newBirthDay.equals("")) {
            newBirthDay = targetMember.getBirthDay();
        }

        String newRole = consoleUtil.getValueOf(" new President|Member(Enter. no change)");
        if (newRole.equals("")) {
            newRole = targetMember.getRole().toString();
        }

        try {
            ClubMember modifiedMember = new ClubMember(targetMember.getEmail(), newName, newPhoneNumber);
            modifiedMember.setPhoneNumber(newPhoneNumber);
            modifiedMember.setNickname(newNickname);
            modifiedMember.setBirthDay(newBirthDay);
            modifiedMember.setRole(RolelnClub.valueOf(newRole));

            memberHelper.modify(currentClub, targetMember.getEmail(), modifiedMember);
            narrator.sayln("Modified member:" + modifiedMember.toString());
        } catch (InvalidEmailException e){
            narrator.sayln(e.getMessage());
        }
    }

    public void remove(){

        if(currentClub == null){
            narrator.sayln("No target club yet. Find target club first.");
            return;
        }

        ClubMember targetMember = findOne();
        if(targetMember == null)
            return;

        memberHelper.remove(currentClub, targetMember.getEmail());
        narrator.sayln("\t > Removed by email --> " + targetMember.getEmail());
    }
}
