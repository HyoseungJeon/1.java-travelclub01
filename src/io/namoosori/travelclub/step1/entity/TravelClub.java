package io.namoosori.travelclub.step1.entity;

import java.util.ArrayList;
import java.util.List;

public class TravelClub {
    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MINIMUM_INTRO_LENGTH = 10;

    private String name;
    private String intro;
    private long foundationTime;

    private List<ClubMember> members;

    public TravelClub(){

        this.members = new ArrayList<ClubMember>();
    }

    public TravelClub(String name, String intro) {

        this();
        this.name = name;
        this.intro = intro;
        this.foundationTime = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public long getFoundationTime() {
        return foundationTime;
    }

    public void setFoundationTime(long foundationTime) {
        this.foundationTime = foundationTime;
    }

    public List<ClubMember> getMembers() {
        return members;
    }

    public void setMembers(List<ClubMember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("TravelClub{");
        builder.append("name='").append(name).append('\'');
        builder.append(", intro='").append(intro).append('\'');
        builder.append(", foundationTime=").append(foundationTime);
        builder.append(", members=").append(members);
        builder.append('}');
        return builder.toString();
    }

    private void checkNameValidation(String name){
        if( name.length() < TravelClub.MINIMUM_NAME_LENGTH){
            throw new IllegalArgumentException("\t > name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
        }
    }

    private void checkIntroValidation(String intro){
        if( intro.length() < TravelClub.MINIMUM_INTRO_LENGTH){
            throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
        }
    }

    public static TravelClub sample(){
        String name = "JavaTravelClub";
        String intro = "Travel club to the Java island.";

        return new TravelClub(name,intro);
    }

    public static void main(String[] args){
        System.out.println(sample().toString());
    }
}
