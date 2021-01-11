package io.namoosori.travelclub.step1.entity;

public class ClubMember {

    private String email;
    private String name;
    private String phoneNumber;
    private String nickname;
    private String birthDay;
    private RolelnClub role;

    public ClubMember(String email, String name, String phoneNumber){
        this.setEmail(email);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = RolelnClub.Member;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("ClubMember{");
        builder.append("email='").append(email).append('\'');
        builder.append(", name='").append(name).append('\'');
        builder.append(", phoneNumber='").append(phoneNumber).append('\'');
        builder.append(", nickname='").append(nickname).append('\'');
        builder.append(", birthDay='").append(birthDay).append('\'');
        builder.append(", role=").append(role);
        builder.append('}');

        return builder.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public RolelnClub getRole() {
        return role;
    }

    public void setRole(RolelnClub role) {
        this.role = role;
    }

    private void checkEmailValidation(String email){
        String ePattern ="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0,9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        boolean valid = m.matches();

        if(!valid){
            //throw new InvalidEmailException("Email is invalid.");
        }
    }

    public static ClubMember sample(){
        ClubMember member= new ClubMember("mymy@nextree.co.kr", "Minsoo Lee", "010-3321-1001");
        member.setBirthDay("2001.09.23");
        return member;
    }

    public static void main(String[] args){
        System.out.println(sample().toString());
    }
}
