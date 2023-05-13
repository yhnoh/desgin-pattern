package com.example.facadepattern.member;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository {

    private Map<String, Member> members = new HashMap<>() {{
        put("id1", new Member("id1", "password1"));
        put("id2", new Member("id2", "password2"));
    }};

    public Optional<Member> findById(String id){
        Member member = members.get(id);
        if(member == null){
            return Optional.empty();
        }
        return Optional.of(members.get(id));
    }

    public Member save(Member member){
        if(members.containsKey(member.getId())){
            throw new IllegalArgumentException(member.getId() + " 키가 존재합니다.");
        }

        return members.put(member.getId(), member);
    }
}
