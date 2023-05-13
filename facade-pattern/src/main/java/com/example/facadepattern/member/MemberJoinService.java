package com.example.facadepattern.member;

import com.example.facadepattern.email.SendEmail;
import com.example.facadepattern.member.point.MemberPoint;
import com.example.facadepattern.member.point.MemberPointAddition;
import org.springframework.stereotype.Service;

@Service
public class MemberJoinService {

    private final MemberIdDoubleChecker memberIdDoubleChecker;
    private final MemberRepository memberRepository;
    private final MemberPointAddition memberPointAddition;
    private final SendEmail sendEmail;

    //서브 시스템들 구성하기
    public MemberJoinService(MemberIdDoubleChecker memberIdDoubleChecker, MemberRepository memberRepository, MemberPointAddition memberPointAddition, SendEmail sendEmail) {
        this.memberIdDoubleChecker = memberIdDoubleChecker;
        this.memberRepository = memberRepository;
        this.memberPointAddition = memberPointAddition;
        this.sendEmail = sendEmail;
    }

    public void join(String id, String password){

        //중복 체크
        memberIdDoubleChecker.checkMemberId(id);

        //회원 정보 DB 적재
        Member saveMember = memberRepository.save(new Member(id, password));

        //회원가입 기념 포인트 적립
        MemberPoint memberPoint = new MemberPoint(id);
        memberPointAddition.addPoint(memberPoint, 1000L);

        //이메일 전송
        sendEmail.sendEmail(id + "님 회원가입을 해주셔서 진심으로 감사드립니다.");
    }
}
