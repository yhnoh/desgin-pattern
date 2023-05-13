package com.example.facadepattern.member.point;

import org.springframework.stereotype.Service;

@Service
public class MemberPointAddition {

    private final MemberPointRepository memberPointRepository;

    public MemberPointAddition(MemberPointRepository memberPointRepository) {
        this.memberPointRepository = memberPointRepository;
    }

    public void addPoint(MemberPoint memberPoint, long addingPoint){

        if(addingPoint < 0){
            throw new IllegalArgumentException("추가 포인트는 0이하일 수 없습니다.");
        }

        memberPoint.setPoint(memberPoint.getPoint() + addingPoint);

        memberPointRepository.update(memberPoint);
    }
}
