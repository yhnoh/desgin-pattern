package com.example.facadepattern.member.point;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberPointRepository {

    private Map<String, MemberPoint> memberPoints = new HashMap<>(){{
        put("id1", new MemberPoint("id1"));
        put("id2", new MemberPoint("id2"));
    }};

    public Optional<MemberPoint> findById(String id){
        MemberPoint memberPoint = memberPoints.get(id);
        if(memberPoint == null){
            return Optional.empty();
        }
        return Optional.of(memberPoint);
    }

    public MemberPoint update(MemberPoint memberPoint){
        if(memberPoints.containsKey(memberPoint.getMemberId())){
            return memberPoints.replace(memberPoint.getMemberId(), memberPoint);
        }

        return this.save(memberPoint);
    }

    public MemberPoint save(MemberPoint memberPoint){
        if(memberPoints.containsKey(memberPoint.getMemberId())){
            throw new IllegalArgumentException(memberPoint.getMemberId() + " 키가 존재합니다.");
        }

        return memberPoints.put(memberPoint.getMemberId(), memberPoint);
    }

}
