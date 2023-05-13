package com.example.facadepattern.member.point;

import com.example.facadepattern.member.Member;

public class MemberPoint {

    private String memberId;
    public Long point = 0L;

    public MemberPoint(String memberId) {
        this.memberId = memberId;
    }

    void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    void setPoint(Long point) {
        this.point = point;
    }

    public String getMemberId() {
        return memberId;
    }

    public Long getPoint() {
        return point;
    }
}
