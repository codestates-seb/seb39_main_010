package com.team10.preproject.answer.dto;

import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.entity.DeleteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private Long answerId;
    private String comment;
    private Long memberId;
    private String nickname;
    private List<Answer> children = new ArrayList<>();

    public AnswerDto(Long answerId, String comment, Long memberId, String nickname){

        this.answerId = answerId;
        this.comment = comment;
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public static AnswerDto convertAnswerToDto(Answer answer){

        return answer.getIsDeleted() == DeleteStatus.Y ?
                new AnswerDto(answer.getAnswerId(), "삭제된 댓글입니다.", null, null) :
                new AnswerDto(answer.getAnswerId(), answer.getComment(), answer.getMember().getMemberId(), answer.getMember().getNickname());
    }

}
